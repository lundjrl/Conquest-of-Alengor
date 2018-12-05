package com.conquestrpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RPGameTest {

    private HashMap<String, MapLoader> maps;
    private final String ConquestOfAlengor = "ConquestOfAlengor.tmx";
    private MapObject npcObject;
    private Player player;
    private Rectangle rectangle;
    private RPGame test;
    private NPC npcTest;
    private OrthographicCamera camera;


    /** Test loads a .tmx file to the Hashmap */
    @Test
    public void testmaploader() {
        final String ConquestOfAlengor = "ConquestOfAlengor.tmx";
        maps = new HashMap<String, MapLoader>();
        maps.put(ConquestOfAlengor, new MapLoader(ConquestOfAlengor));

    }

    /** Test that creates a new NPC object */
    @Test
    public void testNPC(){
        npcObject =maps.get(ConquestOfAlengor).getNpcLayer().getObjects().get("Fisherman6");

        npcTest =new NPC(npcObject, "TrueAlengor.png");
    }
    @Test
    public void testMonster(){
        npcObject =maps.get(ConquestOfAlengor).getNpcLayer().getObjects().get("Fisherman6");

        npcTest  =new Monster(npcObject, "TrueAlengor.png", 100, 5);
    }
    @Test
    public void testCollision(){
        test = new RPGame();
        assertTrue(test.isCollision(rectangle));
    }
    @Test
    public void testDamage(){
        test = new RPGame();
        player = new Player();
        test.isAttacked();
    }
    @Test
    public void moveTest(){
        test = new RPGame();
        player = new Player();
        test.movePlayer();
    }
    @Test
    public void keyRight(){
        test = new RPGame();
        assertTrue(test.keyUp(Input.Keys.RIGHT));
        test.movePlayer();

    }
    @Test
    public void keyLeftPress(){
        test = new RPGame();
        assertTrue((Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) && !test.isCollision(player.getPlayerBox()));
        test.movePlayer();

    }
    @Test
    public void keyUPPress(){
        test = new RPGame();
        assertTrue((Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) && !test.isCollision(player.getPlayerBox()));
        test.movePlayer();

    }
    @Test
    public void keyDownPress(){
        test = new RPGame();
        assertTrue((Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN) && !test.isCollision(player.getPlayerBox())));
        test.movePlayer();

    }
    @Test
    public void keyRightPress(){
        test = new RPGame();
        assertTrue((Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) && !test.isCollision(player.getPlayerBox())));
        test.movePlayer();

    }
    @Test
    public void keyUp(){
        test = new RPGame();
        assertTrue(test.keyUp(Input.Keys.UP));
        test.movePlayer();
    }
    @Test
    public void keyDown(){
        test = new RPGame();
        assertTrue(test.keyUp(Input.Keys.DOWN));
        test.movePlayer();

    }

    @Test
    public void keyLeft(){
        test = new RPGame();
        assertTrue(test.keyUp(Input.Keys.LEFT));
        test.movePlayer();

    }
    @Test
    public void testplayerDeath(){
        test = new RPGame();
        player = new Player();
        player.setPlayerHealth(0);
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
//    @Test
//    public void
}