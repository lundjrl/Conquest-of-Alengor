package com.conquestrpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Assert;
import org.junit.Test;

import java.nio.channels.FileLock;
import java.util.HashMap;

import static org.junit.Assert.*;

public class RPGameTest {

    private HashMap<String, MapLoader> maps;
    private final String ConquestOfAlengor = "ConquestOfAlengor.tmx";
    private MapObject npcObject;
    private NPC npcTest;
    private Music music;
    private FileLock playerBox;
    private RPGame test;
    private Rectangle rectangle;
    private Player player;
    private OrthographicCamera camera;

    /**
     * Test loads a .tmx file to the Hashmap
     */
    @Test
    public void testmaploader() {
        final String ConquestOfAlengor = "ConquestOfAlengor.tmx";
        maps = new HashMap<String, MapLoader>();
        maps.put(ConquestOfAlengor, new MapLoader(ConquestOfAlengor));

    }

    /**
     * Test that creates a new NPC object
     */
    @Test
    public void testNPC() {
        npcObject = maps.get(ConquestOfAlengor).getNpcLayer().getObjects().get("Fisherman6");

        npcTest = new NPC(npcObject, "TrueAlengor.png");
    }

    /**
     * Test that implements the music in the game
     */
    @Test
    public void testMusic() {
        music = Gdx.audio.newMusic(Gdx.files.internal("NiGiD_-_Speculation_Sheet.mp3"));
    }

    /**
     * Test that tests if the music was disposed at the end of the program. Calls the LibGdx super dispose method.
     */
    @Test
    public void testDispose() {
        music.dispose();

    }

    @Test
    public void testMonster() {
        npcObject = maps.get(ConquestOfAlengor).getNpcLayer().getObjects().get("Fisherman6");

        npcTest = new Monster(npcObject, "TrueAlengor.png", 100, 5);
    }

    @Test
    public void testCollision() {
        test = new RPGame();
        assertTrue(test.isCollision(rectangle));
    }

    @Test
    public void testDamage() {
        test = new RPGame();
        player = new Player();
        test.isAttacked();
    }

    @Test
    public void moveTest() {
        test = new RPGame();
        player = new Player();
        test.movePlayer();
    }

    @Test
    public void testCurrMapview() {
        test = new RPGame();
        test.getCurrentMap().getTiledMapRenderer().setView(camera);
    }

    @Test
    public void setFalseMaps() {
        for (HashMap.Entry<String, MapLoader> entry : maps.entrySet()) {
            String key = entry.getKey();
            maps.get(key).setCurrent(false);
        }
    }

    @Test
    public void testTouchDragged() {
        int screenX, screenY, pointer;
        screenX = 0;
        screenY = 0;
        pointer = 1;

    }

    @Test
    public void testMouseMoved() {
        int screenX, screenY;
        screenX = 0;
        screenY = 0;
    }

    @Test
    public void testScrolled() {
        int amount;
        amount = 10;
    }
}