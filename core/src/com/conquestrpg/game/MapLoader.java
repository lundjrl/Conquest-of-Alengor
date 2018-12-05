package com.conquestrpg.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.HashMap;
import java.util.Map;

/**
 * The class MapLoader is used in the RPGame class to load into the game.
 * All TMX files that are loaded must have object layers that include the
 * names: COLLISION_LAYER, NPC_LAYER, ITEM_LAYER, PLAYER_SPAWN_LAYER, and
 * DOORWAY_LAYER. This class also is used to find door overlap if a player
 * steps on a door.
 *
 * @author James Lund, Zachary Thomas, Matthew Doan, Aaron Brinkerhoff
 * @version Dec-4-2018
 */

public class MapLoader {

    /** Tiled map to load */
    private TiledMap tiledMap;

    /** Tiled map to render */
    private TiledMapRenderer tiledMapRenderer;

    /** Collision layer for player */
    private MapLayer collisionLayer;

    /** Shows the game where to spawn the player */
    private MapLayer playerSpawnLayer;

    /** Shows the game where to spawn NPCs */
    private MapLayer npcLayer;

    /** Shows the game where doors are to load */
    private MapLayer doorwayLayer;

    /** Shows the game where items are */
    private MapLayer itemLayer;

    /** Tells the game if the current map is being rendered */
    private boolean isCurrent = false;

    /** Final string for collisions */
    private final String COLLISION_LAYER = "COLLISION_LAYER";

    /** Final string for npc layer */
    private final String NPC_LAYER = "NPC_LAYER";

    /** Final string for item layer */
    private final String ITEM_LAYER = "ITEM_LAYER";

    /** Final string for player spawn layer */
    private final String PLAYER_SPAWN_LAYER = "PLAYER_SPAWN_LAYER";

    /** Final string for doorway layer */
    private final String DOORWAY_LAYER = "DOORWAY_LAYER";

    /** String that holds the .tmx file name */
    private String currentMapName;

    /** Rectangle that holds a door */
    private Rectangle door;

    /** Rectangle object to get name of door */
    private RectangleMapObject doorRectangleObject;

    /**
     * This is the MapLoader constructor that takes in the .tmx filename of map
     * and loads all corresponding layers to memory.
     *
     * @param map string that holds .tmx filename
     */
    public MapLoader(String map){
        // Load tile map from assets folder
        this.currentMapName = map;
        this.tiledMap = new TmxMapLoader().load(map);
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        // Get object layers from tile map
        this.collisionLayer = tiledMap.getLayers().get(COLLISION_LAYER);
        this.npcLayer = tiledMap.getLayers().get(NPC_LAYER);
        this.doorwayLayer = tiledMap.getLayers().get(DOORWAY_LAYER);
        this.playerSpawnLayer = tiledMap.getLayers().get(PLAYER_SPAWN_LAYER);
        this.itemLayer = tiledMap.getLayers().get(ITEM_LAYER);
    }

    /**
     * This method loads the door rectangle when called for the corresponding
     * name on the current map.
     *
     * @param doorString the string associated with the door to load on the map
     */
    public  void loadDoor(String doorString){
        this.door = ((RectangleMapObject)(doorwayLayer.getObjects().get(doorString))).getRectangle();
    }

    /**
     * This method is crucial in determining if the object of a rectangle has been
     * overlapped by the player.
     *
     * @param playerBox the box the player moves around with
     * @return boolean to see if the player has overlapped a door
     */

    public boolean isDoorOverlap(Rectangle playerBox){
        Rectangle rec = null;

        // Loop through all door rectangle objects in map
        for(MapObject object: doorwayLayer.getObjects()){
            if(object instanceof RectangleMapObject){
                rec = ((RectangleMapObject)object).getRectangle();
                if(playerBox.overlaps(rec)){
                    this.door = rec;
                    this.doorRectangleObject = (RectangleMapObject)object;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * See if this map is the current one being rendered.
     *
     * @return boolean to see if it is currently on display.
     */
    public boolean isCurrent() {
        return isCurrent;
    }

    /**
     * Set the current map true or false for being currently
     * rendered.
     * @param is a boolean to set to true or false
     */
    public void setCurrent(boolean is) {
        this.isCurrent = is;
    }

    /**
     * Gets the door object.
     * @return a Rectangle object
     */
    public Rectangle getDoor() {
        return this.door;
    }

    /**
     * Set the door object
     * @param door a door to set
     */
    public void setDoor(Rectangle door) {
        this.door = door;
    }

    /**
     * Get the tiled map
     * @return a TiledMap that has been loaded
     */
    public TiledMap getTiledMap() {
        return tiledMap;
    }

    /**
     * Set the current tiled map
     * @param tiledMap the tilemap to set
     */
    public void setTiledMap(TiledMap tiledMap) {
        this.tiledMap = tiledMap;
    }

    /**
     * Get the tile map renderer
     * @return the tile map renderer
     */
    public TiledMapRenderer getTiledMapRenderer() {
        return tiledMapRenderer;
    }

    /**
     * Set the tile map renderer
     * @param tiledMapRenderer the tile map renderer
     */
    public void setTiledMapRenderer(TiledMapRenderer tiledMapRenderer) {
        this.tiledMapRenderer = tiledMapRenderer;
    }

    /**
     * Get the collision layer
     * @return the collision layer
     */
    public MapLayer getCollisionLayer() {
        return collisionLayer;
    }

    /**
     * Set the collision layer
     * @param collisionLayer the collision layer
     */
    public void setCollisionLayer(MapLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    /**
     * Get the spawn layer
     * @return the spawn layer
     */
    public MapLayer getPlayerSpawnLayer() {
        return playerSpawnLayer;
    }

    /**
     * Sets the spawn layer
     * @param playerSpawnLayer the spawn layer
     */
    public void setPlayerSpawnLayer(MapLayer playerSpawnLayer) {
        this.playerSpawnLayer = playerSpawnLayer;
    }

    /**
     * Get the npc layer
     * @return the npc layer
     */
    public MapLayer getNpcLayer() {
        return npcLayer;
    }

    /**
     * Set the npc layer
     * @param npcLayer the npc layer
     */
    public void setNpcLayer(MapLayer npcLayer) {
        this.npcLayer = npcLayer;
    }

    /**
     * Get the doorway layer
     * @return set the doorway layer
     */
    public MapLayer getDoorwayLayer() {
        return doorwayLayer;
    }

    /**
     * Set the doorway layer
     * @param doorwayLayer the doorway layer
     */
    public void setDoorwayLayer(MapLayer doorwayLayer) {
        this.doorwayLayer = doorwayLayer;
    }

    /**
     * Get the item layer
     * @return the item layer
     */
    public MapLayer getItemLayer() {
        return itemLayer;
    }

    /**
     * Set the item layer
     * @param itemLayer the item layer
     */
    public void setItemLayer(MapLayer itemLayer) {
        this.itemLayer = itemLayer;
    }

    /**
     * Get the current map name
     * @return the string of the current map name
     */
    public String getCurrentMapName() {
        return currentMapName;
    }

    /**
     * Set the current map name
     * @param currentMapName the current map name
     */
    public void setCurrentMapName(String currentMapName) {
        this.currentMapName = currentMapName;
    }

    /**
     * Gets the door rectangle object
     * @return the door rectangle object
     */
    public RectangleMapObject getDoorRectangleObject() {
        return doorRectangleObject;
    }

    /**
     * Set the door rectangle object
     * @param doorRectangleObject the door rectangle object
     */
    public void setDoorRectangleObject(RectangleMapObject doorRectangleObject) {
        this.doorRectangleObject = doorRectangleObject;
    }

}
