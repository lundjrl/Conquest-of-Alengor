package com.conquestrpg.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.conquestrpg.game.Screens.TitleScreen;
import com.conquestrpg.game.States.GameStateManager;
import com.conquestrpg.game.States.MenuState;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class  RPGame extends ApplicationAdapter implements InputProcessor {

	//public static final int WIDTH = 800;
	//public static final int HEIGHT = 800;
	//public static final String TITLE = "Conquest Of Alengor";
	private GameStateManager gsm;
	public SpriteBatch batch;

	// Maps
	TiledMap tiledMap;
	public OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	TiledMapRenderer tiledMapRendererMain;
	TiledMap tiledMap2;
	TiledMapRenderer tiledMapRendererWarehouse;
	MapLayer collisionLayer;
	MapLayer npcLayer;
	MapLayer playerSpawn;
	MapLayer doorwayLayer;
	MapObjects npcObjects;
	MapObject npcObject;
	MapObject loadWarehouse;
	Rectangle doorOutWarehouse, doorInWarehouse;
	int i = 0;
	int frame = 0;

	// Player
	private Player player;
//	SpriteBatch character;
//	Texture texture;
//	Sprite sprite;
	Music music;
//	Rectangle playerBox;
//	float offset = 8.0f; // pixel offset for player collision
	private NPC npcTest;

	// Movement
    float characterSpeed = 5.0f;
    float maxSpeed = 1.0f;
    float characterX;
    float characterY;

    float saveCharX;
    float saveCharY;

    // Tilemap rendering
    int[] background = {0,1,2,3,4,6,7};
    int[] overlay = {5};


	@Override
	public void create () {

		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));

//		((com.badlogic.gdx.Game) Gdx.app.getApplicationListener()).setScreen(new TitleScreen(this));


		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();

		//Scale
		camera.setToOrtho(false, (w/3.0f), (h/3.0f));
		camera.update();





		// Maps
		tiledMap = new TmxMapLoader().load("ConquestOfAlengor.tmx");
		tiledMapRendererMain = new OrthogonalTiledMapRenderer(tiledMap);
		// Get collision layer
		collisionLayer = tiledMap.getLayers().get("COLLISION_LAYER");
		npcLayer = tiledMap.getLayers().get("NPC_LAYER");
		doorwayLayer = tiledMap.getLayers().get("DOORWAY_LAYER");
		doorOutWarehouse = ((RectangleMapObject)(doorwayLayer.getObjects().get("Maintownwarehouse.tmx"))).getRectangle();


		// Doorway
		tiledMap2 = new TmxMapLoader().load("Maintownwarehouse.tmx");
		tiledMapRendererWarehouse = new OrthogonalTiledMapRenderer(tiledMap2);

		playerSpawn = tiledMap2.getLayers().get("PLAYER_SPAWN_LAYER");
		loadWarehouse = playerSpawn.getObjects().get("ConquestOfAlengor.tmx");
		// Get rectangle
		System.out.println(playerSpawn.getObjects().getCount());

		doorInWarehouse = ((RectangleMapObject)loadWarehouse).getRectangle();

		// Render main first
		tiledMapRenderer = tiledMapRendererMain;





		// Music
		music = Gdx.audio.newMusic(Gdx.files.internal("NiGiD_-_Speculation_Sheet.mp3"));
		//Set this below audio to maybe a boss fight or while talking to an NPC
		//music = Gdx.audio.newMusic(Gdx.files.internal("Lounge Game2.wav"));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();

		// Camera Start
		camera.translate(800, 270);


		Gdx.input.setInputProcessor(this);


//		// Character
//		character = new SpriteBatch();
//		texture = new Texture(Gdx.files.internal("Main.png"));
//		sprite = new Sprite(texture);
//
//
//		// Move in multiples of 16
//		sprite.translate(992, 336);
//		playerBox = new Rectangle(sprite.getX() + offset, sprite.getY(), 16.0f, 0.5f); // For collisions
//
		// Set Camera position the same as the character


		npcObject = npcLayer.getObjects().get("Fisherman6");
		npcTest = new NPC(npcObject);

		player = new Player();

		camera.position.set(player.getSprite().getX(), player.getSprite().getY(), 0);



	}

	@Override
	public void render () {




		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

		// Save position before collision occurs
		if(!isCollision(player.getPlayerBox())) {
			saveCharX = player.getSprite().getX();
			saveCharY = player.getSprite().getY();
		}





		// Doors

		if(player.getPlayerBox().overlaps(doorOutWarehouse)){
			tiledMapRenderer = tiledMapRendererWarehouse;
			player.getSprite().setPosition(doorInWarehouse.getX(), doorInWarehouse.getY() + 20);
			System.out.println("I'm here");
		}

		if(player.getPlayerBox().overlaps(doorInWarehouse)){
			tiledMapRenderer = tiledMapRendererMain;
			player.getSprite().setPosition(doorOutWarehouse.getX(), doorOutWarehouse.getY() - 20);
			System.out.println("I'm here2");
		}













		// Input
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) && !isCollision(player.getPlayerBox())){
			characterX -= Gdx.graphics.getDeltaTime() * characterSpeed;
		}


        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) && !isCollision(player.getPlayerBox())){
            characterX += Gdx.graphics.getDeltaTime() * characterSpeed;
        }


        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP) && !isCollision(player.getPlayerBox())){
            characterY += Gdx.graphics.getDeltaTime() * characterSpeed;
        }


        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN) && !isCollision(player.getPlayerBox())){
            characterY -= Gdx.graphics.getDeltaTime() * characterSpeed;
        }


        // Implement max speed
        if(characterX >= maxSpeed)
        	characterX = maxSpeed;
        else if(characterX <= -maxSpeed)
			characterX = -maxSpeed;

        if(characterY >= maxSpeed)
        	characterY = maxSpeed;
        else if(characterY <= -maxSpeed)
			characterY = -maxSpeed;

        // If there is a collision, move the player where they were
		if(isCollision(player.getPlayerBox())){
			player.getSprite().setPosition(saveCharX, saveCharY);
		}

        // Move character and camera at the same time.
        player.getSprite().translate(Math.round(characterX), Math.round(characterY));
        camera.position.set(Math.round(player.getSprite().getX()), Math.round(player.getSprite().getY()), 0);


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update(); //This changes camera position
		tiledMapRenderer.setView(camera); //type something or whatever

        // Render below character
		//tiledMapRenderer.render(background);
		tiledMapRenderer.render();


//		// Render character
		player.getCharacter().setProjectionMatrix(camera.combined);
		npcTest.getCharacter().setProjectionMatrix(camera.combined);
//		character.begin();
//		sprite.draw(character);
//		character.end();
//		playerBox.setCenter(sprite.getX() + offset, sprite.getY());

		player.render();
		npcTest.render();
		if (screen != null) screen.render(Gdx.graphics.getDeltaTime());
		this.setScreen(new TitleScreen(this));


		// Render over character
        //tiledMapRenderer.render(overlay);
		//camera.update();

	}
	
	@Override
	public void dispose(){
		super.dispose();
		music.dispose();
		if (screen != null) screen.hide();
		//batch.dispose();
		//img.dispose();

		//mainGameScreen.dispose();

		//batch.dispose();
		//img.dispose();
	}

	// Collision method
	private boolean isCollision(Rectangle playerBox){
		MapLayer testCollision = collisionLayer;
		Rectangle rec = null;

		for(MapObject object: testCollision.getObjects()){
			if(object instanceof RectangleMapObject){
				rec = ((RectangleMapObject)object).getRectangle();
				if(playerBox.overlaps(rec)){
					characterX = 0.0f;
					characterY = 0.0f;
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
	    return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Input.Keys.LEFT) {
			characterX = 0.0f;
			characterY = 0.0f;
		}
		if(keycode == Input.Keys.RIGHT){
			characterX = 0.0f;
			characterY = 0.0f;
		}
		if(keycode == Input.Keys.UP) {
			characterX = 0.0f;
			characterY = 0.0f;
		}
		if(keycode == Input.Keys.DOWN) {
			characterX = 0.0f;
			characterY = 0.0f;
		}
		//if(keycode == Input.Keys.DPAD_LEFT){
		//	characterX = 0.0f;
		//	characterY = 0.0f;
		//}

		// Move camera into position
		//camera.position.set(sprite.getX(), sprite.getY(), 0);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//Vector3 clickCoordinates = new Vector3(screenX,screenY,0);
		//Vector3 position = camera.unproject(clickCoordinates);
		//sprite.setPosition(position.x, position.y);
		return true;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	protected Screen screen;


	public void setScreen (Screen screen) {
		if (this.screen != null) this.screen.hide();
		this.screen = screen;
		if (this.screen != null) {
			this.screen.show();
			this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}


}
