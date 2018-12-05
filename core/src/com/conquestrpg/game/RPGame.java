package com.conquestrpg.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import java.util.HashMap;
import static java.lang.System.exit;

/**
 *
 *
 *
 */

public class  RPGame extends ApplicationAdapter implements InputProcessor {



	private SpriteBatch batch;

	private float width;
	private float height;

	// Maps
	private OrthographicCamera camera;

	private MapObject npcObject;
	private MapObject monsterObject;


	// Loaded maps in ArrayList
	private HashMap<String, MapLoader> maps;

	// String to hold door if player overlaps
    private String stepOnDoor;

    // Rectangle placeholder
	private Rectangle recPlaceHolder;

	// Hold last map name
	private String lastMapName;


	// Player
	private Player player;

	private Music music;

	private NPC npcTest;
	private Monster monster;
	private String[] monsters = {"Lumberjack3"};

	// Movement
    float characterSpeed = 5.0f;
    float maxSpeed = 1.0f;
    float characterX;
    float characterY;

    float saveCharX;
    float saveCharY;

    private int offset = 20;

    // Tilemap rendering
    int[] background = {0,1,2,3,4,6,7};
    int[] overlay = {5};

    // Tilemaps to load
	private final String ConquestOfAlengor = "ConquestOfAlengor.tmx";
	private final String Maintownwarehouse = "Maintownwarehouse.tmx";
	private final String MaintownSWhome = "MaintownSWhome.tmx";
	private final String MaintownNWhome = "MaintownNWhome.tmx";
	private final String starthomeCOA = "starthomeCOA.tmx";
	private final String Maintownhall = "Maintownhall.tmx";
	private final String Mansion = "Mansion.tmx";
	private final String MainTitle = "MainTitle.tmx";


	@Override
	public void create () {

		batch = new SpriteBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();

		//Scale
		camera.setToOrtho(false, (width * 1.5f), (height * 1.5f));
		camera.update();

		// MapLoader class
		maps = new HashMap<String, MapLoader>();
		maps.put(ConquestOfAlengor, new MapLoader(ConquestOfAlengor));
		maps.put(Maintownwarehouse, new MapLoader(Maintownwarehouse));
		maps.put(MaintownSWhome, new MapLoader(MaintownSWhome));
		maps.put(MaintownNWhome, new MapLoader(MaintownNWhome));
		maps.put(starthomeCOA, new MapLoader(starthomeCOA));
		maps.put(Maintownhall, new MapLoader(Maintownhall));
		maps.put(Mansion, new MapLoader(Mansion));
		maps.put(MainTitle, new MapLoader(MainTitle));

		// Set current map to main map
		maps.get(MainTitle).setCurrent(true);

		// Music
		music = Gdx.audio.newMusic(Gdx.files.internal("NiGiD_-_Speculation_Sheet.mp3"));
		//Set this below audio to maybe a boss fight or while talking to an NPC
		//music = Gdx.audio.newMusic(Gdx.files.internal("Lounge Game2.wav"));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();

		Gdx.input.setInputProcessor(this);

		// Create NPC
		npcObject = maps.get(ConquestOfAlengor).getNpcLayer().getObjects().get("Fisherman6");
		npcTest = new NPC(npcObject, "TrueAlengor.png");

		// Create monster
		monsterObject = maps.get(ConquestOfAlengor).getNpcLayer().getObjects().get("Lumberjack3");
		monster = new Monster(monsterObject, "Witch2.png", 200, 1);

		// Create player and start at spawn
		player = new Player();
		Rectangle starter = ((RectangleMapObject)getCurrentMap().getPlayerSpawnLayer().getObjects().get("spawn")).getRectangle();
		float startX = starter.getX();
		float startY = starter.getY();
		player.setPosition(startX, startY);
		player.getPlayerBox().set(startX, startY,16.0f, 0.5f);
		camera.position.set(player.getSprite().getX(), player.getSprite().getY(), 0);

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);




		// Save position before collision occurs
		if(!isCollision(player.getPlayerBox())) {
			saveCharX = player.getSprite().getX();
			saveCharY = player.getSprite().getY();
		}

		movePlayer();

		isAttacked();

		checkForDoor();

        // Move character and camera at the same time.
        player.getSprite().translate(Math.round(characterX), Math.round(characterY));
        camera.position.set(Math.round(player.getSprite().getX()), Math.round(player.getSprite().getY()), 0);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		getCurrentMap().getTiledMapRenderer().setView(camera);

        // Render below character
		getCurrentMap().getTiledMapRenderer().render();

		// Render character
		player.getCharacter().setProjectionMatrix(camera.combined);
		npcTest.getCharacter().setProjectionMatrix(camera.combined);
		monster.getCharacter().setProjectionMatrix(camera.combined);

		player.render();
		npcTest.render();
		monster.render();
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
		MapLayer testCollision = null;
		try {
			testCollision = getCurrentMap().getCollisionLayer();
		}
		catch (NullPointerException e){
			System.out.println("Collision layer can't load: " + e);
		}
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

	public void movePlayer(){
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
	}

	public void checkForDoor(){
		if(getCurrentMap().isDoorOverlap(player.getPlayerBox())){
			lastMapName = getCurrentMap().getCurrentMapName();
			stepOnDoor = getCurrentMap().getDoorRectangleObject().getName();
			try {
				if (!stepOnDoor.equals(MainTitle)) {
					//recPlaceHolder = getCurrentMap().getDoor();
					setFalseMaps();
					maps.get(stepOnDoor).setCurrent(true);
					getCurrentMap().loadDoor(lastMapName);

					if (lastMapName.equals(MainTitle)) {
						// Change camera scaling after main screen
						camera.setToOrtho(false, (width / 3), (height / 3));

					}

					if (getCurrentMap().getCurrentMapName().equals(ConquestOfAlengor)) {
						player.getSprite().setPosition(getCurrentMap().getDoor().getX(),
								getCurrentMap().getDoor().getY() - offset);

					} else
						player.getSprite().setPosition(getCurrentMap().getDoor().getX(),
								getCurrentMap().getDoor().getY() + offset);
				}
			} catch (NullPointerException e){
				if (getCurrentMap().getCurrentMapName().equals(MainTitle)) {
					exit(0);
				}
				System.out.println("Game closed. Map does not exist yet");
			}
		}
	}

	private boolean attacking(){
		MapLayer npcs = maps.get(ConquestOfAlengor).getNpcLayer();

		Rectangle rec = null;

		for(MapObject object: npcs.getObjects()){
			if(object instanceof RectangleMapObject){
				rec = ((RectangleMapObject)object).getRectangle();
				if(Math.abs(player.getPlayerBox().getX() - rec.getX()) < 20 &&
				Math.abs(player.getPlayerBox().getY() -rec.getY()) < 20){
					for(String s: this.monsters){
						if(s.equals(object.getName()))
						return true;
					}

				}
			}
		}
		return false;
	}

	public void isAttacked(){
		if(attacking()){
			if(monster.getDamage() < player.getPlayerHealth()){
				player.setPlayerHealth(player.getPlayerHealth()-monster.getDamage());
			}
			else{
				player = new Player();
			}
		}
	}

	// Get current map that player is on
	private MapLoader getCurrentMap(){
		for(HashMap.Entry<String, MapLoader> entry : maps.entrySet()){
			String key = entry.getKey();
			if(maps.get(key).isCurrent()){
				return maps.get(key);
			};
		}
		return null;
	}

	// Set to false all maps when loading
	private void setFalseMaps(){
		for(HashMap.Entry<String, MapLoader> entry : maps.entrySet()){
			String key = entry.getKey();
			maps.get(key).setCurrent(false);
		}
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

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

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
