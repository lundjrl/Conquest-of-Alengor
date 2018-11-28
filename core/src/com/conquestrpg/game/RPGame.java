package com.conquestrpg.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.conquestrpg.screens.MainGameScreen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.conquestrpg.game.States.GameStateManager;
import com.conquestrpg.game.States.MenuState;

public class  RPGame extends ApplicationAdapter implements InputProcessor {

	//public static final int WIDTH = 800;
	//public static final int HEIGHT = 800;
	//public static final String TITLE = "Conquest Of Alengor";
	private GameStateManager gsm;
	private SpriteBatch batch;

	// Maps
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	MapLayer collisionLayer;
	int i = 0;
	int frame = 0;

	// Player
	SpriteBatch character;
	Texture texture;
	Sprite sprite;
	Music music;
	Rectangle playerBox;
	float offset = 8.0f; // pixel offset for player collision

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

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		//camera.setToOrtho(false, (w), (h));
		//Scale
		camera.setToOrtho(false, (w/3), (h/3));
		camera.update();

		// Map
		tiledMap = new TmxMapLoader().load("ConquestOfAlengor.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		// Get collision layer
		collisionLayer = tiledMap.getLayers().get("COLLISION_LAYER");

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


		// Character
		character = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("Main.png"));
		sprite = new Sprite(texture);


		// Move in multiples of 16
		sprite.translate(992, 336);
		playerBox = new Rectangle(sprite.getX() + offset, sprite.getY(), 16.0f, 0.5f); // For collisions

		// Set Camera position the same as the character
		camera.position.set(sprite.getX(), sprite.getY(), 0);

	}

	@Override
	public void render () {




		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

		// Save position before collision occurs
		if(!isCollision(playerBox)) {
			saveCharX = sprite.getX();
			saveCharY = sprite.getY();
		}



		// Input
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) && !isCollision(playerBox)){
			characterX -= Gdx.graphics.getDeltaTime() * characterSpeed;
		}


        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) && !isCollision(playerBox)){
            characterX += Gdx.graphics.getDeltaTime() * characterSpeed;
        }


        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP) && !isCollision(playerBox)){
            characterY += Gdx.graphics.getDeltaTime() * characterSpeed;
        }


        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN) && !isCollision(playerBox)){
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
		if(isCollision(playerBox)){
			sprite.setPosition(saveCharX, saveCharY);
		}

        // Move character and camera at the same time.
        sprite.translate(characterX, characterY);
        camera.position.set(sprite.getX(), sprite.getY(), 0);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update(); //This changes camera position
		tiledMapRenderer.setView(camera); //type something or whatever

        // Render below character
		tiledMapRenderer.render(background);

		// Render character
		character.setProjectionMatrix(camera.combined);
		character.begin();
		sprite.draw(character);
		character.end();
		playerBox.setCenter(sprite.getX() + offset, sprite.getY());


        // Render over character
        tiledMapRenderer.render(overlay);

	}
	
	@Override
	public void dispose(){
		super.dispose();
		music.dispose();
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

}
