package com.conquestrpg.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;

import java.util.Map;

public class MapLoader {

    // Maps
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    MapLayer collisionLayer;
    MapLayer playerSpawnLayer;
    MapLayer npcLayer;
    MapLayer doorwayLayer;
    MapLayer itemLayer;


    //Map layers
    private final String COLLISION_LAYER = "COLLISION_LAYER";
    private final String NPC_LAYER = "NPC_LAYER";




    // Java hashmap to store maps
    Map saveMaps;

    public MapLoader(){

    }

}
