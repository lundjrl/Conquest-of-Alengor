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
 * This class is the heart of COA, it has the main features that makes the game run, including: maps, player,
 * titlescreen, NPC and monster implementation, and screens.
 * @author: James Lund, Zachary Thomas, Matthew Doan, Aaron Brinkerhoff
 * @version: 12/04/18
 * Purpose: Final Project for Software Engineering, CIS 350-01, GVSU
 */

public class  RPGame extends ApplicationAdapter implements InputProcessor {


	/** Draws batched quads using indices */
	private SpriteBatch batch;

	/** Floats for width of camera */
	private float width;
	/** Floats for height of camera */
	private float height;

	/** Camera of player */
	private OrthographicCamera camera;

	/** NPC object within COA game */
	private MapObject npcObject;
	/** Monster object within COA game */
	private MapObject monsterObject;


	/** Loaded maps in ArrayList */
	private HashMap<String, MapLoader> maps;

	/** String to hold door if player overlaps */
    private String stepOnDoor;

    /** Rectangle placeholder */
	private Rectangle recPlaceHolder;

	/** Hold last map name */
	private String lastMapName;


	/** Player */
	private Player player;

	/** Music for the game */
	private Music music;

	/** Old man seen in starting town */
	private NPC npcTest;

	/** Witch, temporary mob */
	private Monster monster;

	/** Array of monster names */
	private String[] monsters = {"Lumberjack3"};

	/** Initial movement speed */
    float characterSpeed = 5.0f;
    /** Maximum speed */
    float maxSpeed = 1.0f;
    /** Variable to hold X coordinate of the character */
    float characterX;
	/** Variable to hold Y coordinate of the character */
    float characterY;

    /** Save character coordinates X */
    float saveCharX;
	/** Save character coordinates Y */
    float saveCharY;

    /** For loading doorways. Fix from weird map loading issues */
    private int offset = 20;


    /** Tilemap to load ConquestOfAlengor.tmx map */
	private final String ConquestOfAlengor = "ConquestOfAlengor.tmx";
	/** Tilemap to load Maintownwarehouse.tmx map */
	private final String Maintownwarehouse = "Maintownwarehouse.tmx";
	/** Tilemap to load MaintownSWhome.tmx map */
	private final String MaintownSWhome = "MaintownSWhome.tmx";
	/** Tilemap to load MaintownNWhome.tmx */
	private final String MaintownNWhome = "MaintownNWhome.tmx";
	/** Tilemap to load starthomeCOA.tmx map */
	private final String starthomeCOA = "starthomeCOA.tmx";
	/** Tilemap to load Maintownhall.tmx map */
	private final String Maintownhall = "Maintownhall.tmx";
	/** Tilemap to load Mansion.tmx map */
	private final String Mansion = "Mansion.tmx";
	/** Tilemap to load MainTitle.tmx map */
	private final String MainTitle = "MainTitle.tmx";

	/**
	 * Create method in RPGgame class. Used to initialy create the game in which the player is released in.
	 */
	@Override
	public void create () {

		// Instantiating the batch object we created earlier
		batch = new SpriteBatch();

		// Clearing the screen
		Gdx.gl.glClearColor(1, 0, 0, 1);

		// Setting the X,Y coordinates of the player
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		// Focusing the camera to the center of the field
		camera = new OrthographicCamera();

		// Scale
		camera.setToOrtho(false, (width * 1.5f), (height * 1.5f));
		camera.update();

		// MapLoader class. Adding all maps to a hashmap for dynamic implementation
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
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();

		// Enables movement of keyboard
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

	/**
	 * This method renders the screen for visualization of the game.
	 */
	@Override
	public void render () {

		// Clearing screen for initialization
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		// Save position before collision occurs
		if(!isCollision(player.getPlayerBox())) {
			saveCharX = player.getSprite().getX();
			saveCharY = player.getSprite().getY();
		}

		// Enabling player to move
		movePlayer();

		// The player is triggered by the assumption that they will be attacked by a monster
		isAttacked();

		// Method-Call for dynamic map implementation
		checkForDoor();

		//
//		monster.getPlayerBox().setPosition((monster.getSprite().getX()), monster.getSprite().getY());

		// Move character and camera at the same time.
        player.getSprite().translate(Math.round(characterX), Math.round(characterY));
        camera.position.set(Math.round(player.getSprite().getX()), Math.round(player.getSprite().getY()), 0);

        // Updating camera and getting another map
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		getCurrentMap().getTiledMapRenderer().setView(camera);

        // Render below character
		getCurrentMap().getTiledMapRenderer().render();

		// Render character
		player.getCharacter().setProjectionMatrix(camera.combined);
		npcTest.getCharacter().setProjectionMatrix(camera.combined);
		monster.getCharacter().setProjectionMatrix(camera.combined);

		// Rendering the player and NPC's into the game screen
		player.render();
		npcTest.render();
		monster.render();


	}

	/**
	 * Getting rid of what we don't need anymore. Used for termination of the program.
	 */
	@Override
	public void dispose(){

		// Disposing application
		super.dispose();

		// Disposing music
		music.dispose();

	}

	/**
	 * Method for collision detection. Player will bounce back if hit into a collision layer.
	 * @param playerBox
	 */
	protected boolean isCollision(Rectangle playerBox){
		MapLayer testCollision = null;
		// Error handling for player walking into a collision layer
		try {
			testCollision = getCurrentMap().getCollisionLayer();
		}
		catch (NullPointerException e){
			System.out.println("Collision layer can't load: " + e);
		}

		// Setting the player's collision box to null
		Rectangle rec = null;

		// Getting callback if the player hit a collision wall or not
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

	/**
	 * Method controls player movement with U/D/L/R arrow keys implemented. Arrow keys can be held down for
	 * continuous movement.
	 */
	public void movePlayer(){
		// Input for Left key being pressed
		if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) && !isCollision(player.getPlayerBox())){
			characterX -= Gdx.graphics.getDeltaTime() * characterSpeed;
		}
		// Input for Right key being pressed
		if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) && !isCollision(player.getPlayerBox())){
			characterX += Gdx.graphics.getDeltaTime() * characterSpeed;
		}
		// Input for Up key being pressed
		if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP) && !isCollision(player.getPlayerBox())){
			characterY += Gdx.graphics.getDeltaTime() * characterSpeed;
		}
		// Input for Down key being pressed
		if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN) && !isCollision(player.getPlayerBox())){
			characterY -= Gdx.graphics.getDeltaTime() * characterSpeed;
		}
		// Implement max speed for X coordinates
		if(characterX >= maxSpeed)
			characterX = maxSpeed;
		else if(characterX <= -maxSpeed)
			characterX = -maxSpeed;

		// Implement max speed for Y coordinates
		if(characterY >= maxSpeed)
			characterY = maxSpeed;
		else if(characterY <= -maxSpeed)
			characterY = -maxSpeed;

		// If there is a collision, move the player where they were, aka bounce back
		if(isCollision(player.getPlayerBox())){
			player.getSprite().setPosition(saveCharX, saveCharY);
		}
	}

	/**
	 * Checks for a possible portal to another tilemap
	 */
	public void checkForDoor(){

		// Comparing if a door exists on the tile to which the player has just walked upon.
		if(getCurrentMap().isDoorOverlap(player.getPlayerBox())){
			lastMapName = getCurrentMap().getCurrentMapName();
			stepOnDoor = getCurrentMap().getDoorRectangleObject().getName();

			// Error handling for the portal implementation
			try {
				if (!stepOnDoor.equals(MainTitle)) {
					// Makes all other maps false
					setFalseMaps();
					// Sets the map that the player stepped on 'true'
					maps.get(stepOnDoor).setCurrent(true);
					// Loading new map from the door the player had entered
					getCurrentMap().loadDoor(lastMapName);

					// Setting the map back to the overworld after leaving a building
					if (lastMapName.equals(MainTitle)) {
						// Change camera scaling after main screen
						camera.setToOrtho(false, (width / 3), (height / 3));

					}

					// Setting door-triggered map to the current map on screen.
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
				// Print statement for if the player enters a building that does not have a map associated with it.
				System.out.println("Game closed. Map does not exist yet");
			}
		}
	}

	/**
	 * For a monster attacking the player
	 * @return false
	 */
	private boolean attacking(){
		// Getting the layer which the NPC exists
		MapLayer npcs = maps.get(ConquestOfAlengor).getNpcLayer();

		Rectangle rec = null;

		// Determines if a monster is on or nearby the player
		for(MapObject object: npcs.getObjects()){
			if(object instanceof RectangleMapObject){
				rec = ((RectangleMapObject)object).getRectangle();
				if(Math.abs(player.getPlayerBox().getX() - rec.getX()) < 20 &&
				Math.abs(player.getPlayerBox().getY() -rec.getY()) < 20){
					for(String s: this.monsters){
						if(s.equals(object.getName())){
							System.out.println(player.getPlayerHealth());
						}
						return true;
					}

				}
			}
		}
		return false;
	}

	/**
	 * Implements the player being attacked. If the monster is attacking, the player will take damage and die.
	 */
	public void isAttacked(){
		// Calling the attacking method to determine if the player will lose health.
		if(attacking()){
			if(monster.getDamage() < player.getPlayerHealth()){
				player.setPlayerHealth(player.getPlayerHealth()-monster.getDamage());
			}
			// If the player did take damage, the player will respawn in the starting town.
			else{
				player.setPosition(992,336);
				player.setPlayerHealth(200);
			}
		}
	}

	/**
	 * Accessor for the current map that the player is on.
	 * @return null
	 */
	protected MapLoader getCurrentMap(){
		// Loops through all the maps and determines which one the player is located on.
		for(HashMap.Entry<String, MapLoader> entry : maps.entrySet()){
			String key = entry.getKey();
			// Compares map key to current key
			if(maps.get(key).isCurrent()){
				return maps.get(key);
			};
		}
		return null;
	}

	/**
	 * Helper method that sets to false all maps when loading
 	 */
	private void setFalseMaps(){
		// For each loop that compares each map and sets them to false.
		for(HashMap.Entry<String, MapLoader> entry : maps.entrySet()){
			String key = entry.getKey();
			maps.get(key).setCurrent(false);
		}
	}

	/**
	 * Helper method that determines if a key is being held down or not.
	 * @param keycode
	 * @return false
	 */
	@Override
	public boolean keyDown(int keycode) {
	    return false;
	}

	/**
	 * Checks whether an arrow key is being pressed down. If not then the character will not be moving.
	 * @param keycode
	 */
	@Override
	public boolean keyUp(int keycode) {
		// Determining if Left arrow key is up
		if(keycode == Input.Keys.LEFT) {
			characterX = 0.0f;
			characterY = 0.0f;
		}
		// Determining if Right arrow key is up
		if(keycode == Input.Keys.RIGHT){
			characterX = 0.0f;
			characterY = 0.0f;
		}
		// Determining if Up arrow key is up
		if(keycode == Input.Keys.UP) {
			characterX = 0.0f;
			characterY = 0.0f;
		}
		// Determining if Down arrow key is up
		if(keycode == Input.Keys.DOWN) {
			characterX = 0.0f;
			characterY = 0.0f;
		}

		return false;
	}

	/**
	 * Event listener to see if there is a key typed or not.
	 * @param character
	 * @return false
	 */
	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	/**
	 * Event listener to see if there is a key being touched down.
	 * @param screenX
	 * @param screenY
	 * @param pointer
	 * @param button
	 * @return true
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return true;
	}

	/**
	 * Event listener to see if a key pressed down has been released.
	 * @param screenX
	 * @param screenY
	 * @param pointer
	 * @param button
	 * @return false
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	/**
	 * Event listener for mouse draggage.
	 * @param screenX
	 * @param screenY
	 * @param pointer
	 * @return false
	 */
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	/**
	 * Event listener for a mouse being moved.
	 * @param screenX
	 * @param screenY
	 * @return false
	 */
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	/**
	 * Event listener for scrollage of a mouse.
	 * @param amount
	 * @return false
	 */
	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
