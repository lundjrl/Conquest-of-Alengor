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

public class MapLoader {

    // Maps
    private TiledMap tiledMap;
    //OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private MapLayer collisionLayer;
    private MapLayer playerSpawnLayer;
    private MapLayer npcLayer;
    private MapLayer doorwayLayer;
    private MapLayer itemLayer;

    private boolean isCurrent = false;


    //Map layers
    private final String COLLISION_LAYER = "COLLISION_LAYER";
    private final String NPC_LAYER = "NPC_LAYER";
    private final String ITEM_LAYER = "ITEM_LAYER";
    private final String PLAYER_SPAWN_LAYER = "PLAYER_SPAWN_LAYER";
    private final String DOORWAY_LAYER = "DOORWAY_LAYER";

    //Maps
    //private final String ConquestOfAlengor = "ConquestOfAlengor.tmx";

    private Rectangle door;



    // Java hashmap to store maps
 //   HashMap<String, TiledMapRenderer> saveMaps;



    public MapLoader(String map){
        this.tiledMap = new TmxMapLoader().load(map);
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        // Get collision layer
        this.collisionLayer = tiledMap.getLayers().get("COLLISION_LAYER");
        this.npcLayer = tiledMap.getLayers().get("NPC_LAYER");
        this.doorwayLayer = tiledMap.getLayers().get("DOORWAY_LAYER");


//        saveMaps = new HashMap<String, TiledMapRenderer>();
//
//        saveMaps.put(ConquestOfAlengor, tiledMapRendererMain);



    }

    public  void loadDoor(String doorString){
        this.door = ((RectangleMapObject)(doorwayLayer.getObjects().get("Maintownwarehouse.tmx"))).getRectangle();
    }

    public String isDoor(Rectangle playerBox){
        Rectangle rec = null;

        for(MapObject object: collisionLayer.getObjects()){
            if(object instanceof RectangleMapObject){
                rec = ((RectangleMapObject)object).getRectangle();
                if(playerBox.overlaps(rec)){
                    return object.getName();
                }
            }
        }
        return null;
    }


    public void getMap(){

    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean is) {
        this.isCurrent = is;
    }

    public Rectangle getDoor() {
        return door;
    }

    public void setDoor(Rectangle door) {
        this.door = door;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public void setTiledMap(TiledMap tiledMap) {
        this.tiledMap = tiledMap;
    }

    public TiledMapRenderer getTiledMapRenderer() {
        return tiledMapRenderer;
    }

    public void setTiledMapRenderer(TiledMapRenderer tiledMapRenderer) {
        this.tiledMapRenderer = tiledMapRenderer;
    }

    public MapLayer getCollisionLayer() {
        return collisionLayer;
    }

    public void setCollisionLayer(MapLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    public MapLayer getPlayerSpawnLayer() {
        return playerSpawnLayer;
    }

    public void setPlayerSpawnLayer(MapLayer playerSpawnLayer) {
        this.playerSpawnLayer = playerSpawnLayer;
    }

    public MapLayer getNpcLayer() {
        return npcLayer;
    }

    public void setNpcLayer(MapLayer npcLayer) {
        this.npcLayer = npcLayer;
    }

    public MapLayer getDoorwayLayer() {
        return doorwayLayer;
    }

    public void setDoorwayLayer(MapLayer doorwayLayer) {
        this.doorwayLayer = doorwayLayer;
    }

    public MapLayer getItemLayer() {
        return itemLayer;
    }

    public void setItemLayer(MapLayer itemLayer) {
        this.itemLayer = itemLayer;
    }
}
