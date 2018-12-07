package com.conquestrpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import static org.junit.Assert.assertTrue;

public class RPGameTest {

    private final String Maintownwarehouse = "Maintownwarehouse.tmx";
    private final String MaintownSWhome = "MaintownSWhome.tmx";
    private final String MaintownNWhome = "MaintownNWhome.tmx";
    private final String starthomeCOA = "starthomeCOA.tmx";
    private final String Maintownhall = "Maintownhall.tmx";
    private final String Mansion = "Mansion.tmx";

    private HashMap<String, MapLoader> maps;
    private  String MainTitle = "MainTitle.tmx";
    private  String ConquestOfAlengor = "ConquestOfAlengor.tmx";
    private MapObject npcObject;
    private Player player;
    private Rectangle rectangle;
    private RPGame test;
    private NPC npcTest;
    private OrthographicCamera camera;


    /** Test loads a .tmx file to the Hashmap */
    /**
     * Test loads a .tmx file to the Hashmap
     */
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
    /** Test that creates a new Monster object */
    public void testMonster(){
        npcObject =maps.get(ConquestOfAlengor).getNpcLayer().getObjects().get("Fisherman6");

        npcTest  =new Monster(npcObject, "TrueAlengor.png", 100, 5);
    }
    /** Test collision rectangles*/
    @Test
    public void testCollision(){
        test = new RPGame();
        assertTrue(test.isCollision(rectangle));
    }
    /** Test tests damage on player*/
    @Test
    public void testDamage(){
        test = new RPGame();
        player = new Player();
        test.isAttacked();
    }
    /** Test that moves player*/
    @Test
    public void moveTest(){
        test = new RPGame();
        player = new Player();
        test.movePlayer();
    }
    @Test
    /** Test that checks key pad right if it is false*/
    public void keyRightFalse(){
        test = new RPGame();
        assertFalse(test.keyUp(Input.Keys.RIGHT));
        test.movePlayer();

    }
    /** Test that checks key pad left if it is false*/
    @Test
    public void keyLeftPressFalse(){
        test = new RPGame();
        assertFalse((Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) && !test.isCollision(player.getPlayerBox()));
        test.movePlayer();

    }
    /** Test that checks key pad up if it is false*/
    @Test
    public void keyUPPressFalse(){
        test = new RPGame();
        assertFalse((Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) && !test.isCollision(player.getPlayerBox()));
        test.movePlayer();

    }
    /** Test that checks key pad down if it is false*/
    @Test
    public void keyDownPressFalse(){
        test = new RPGame();
        assertFalse((Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN) && !test.isCollision(player.getPlayerBox())));
        test.movePlayer();

    }
    /** Test that checks key pad right if it is false*/
    @Test
    public void keyRightPressFalse(){
        test = new RPGame();
        assertFalse((Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) && !test.isCollision(player.getPlayerBox())));
        test.movePlayer();

    }
    @Test
    /** Test that checks key pad left if it is true*/
    public void keyUpFalse(){
        test = new RPGame();
        assertFalse(test.keyUp(Input.Keys.UP));
        test.movePlayer();
    }
    /** Test that checks key pad down if it is false*/
    @Test
    public void keyDownFalse(){
        test = new RPGame();
        assertFalse(test.keyUp(Input.Keys.DOWN));
        test.movePlayer();

    }
    /** Test that checks key pad left if it is false*/
    @Test
    public void keyLeftFalse(){
        test = new RPGame();
        assertFalse(test.keyUp(Input.Keys.LEFT));
        test.movePlayer();

    }
    /** Test that checks key pad right movement*/
    @Test
    public void keyRight(){
        test = new RPGame();
        assertTrue(test.keyUp(Input.Keys.RIGHT));
        test.movePlayer();

    }
    /** Test that checks key pad left if it is true*/
    @Test
    public void keyLeftPress(){
        test = new RPGame();
        assertTrue((Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) && !test.isCollision(player.getPlayerBox()));
        test.movePlayer();

    }
    /** Test that checks key pad up is true*/
    @Test
    public void keyUPPress(){
        test = new RPGame();
        assertTrue((Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) && !test.isCollision(player.getPlayerBox()));
        test.movePlayer();

    }
    /** Test that checks key pad down press is true*/
    @Test
    public void keyDownPress(){
        test = new RPGame();
        assertTrue((Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN) && !test.isCollision(player.getPlayerBox())));
        test.movePlayer();

    }
    /** Test that checks key pad right press is true*/
    @Test
    public void keyRightPress(){
        test = new RPGame();
        assertTrue((Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) && !test.isCollision(player.getPlayerBox())));
        test.movePlayer();

    }
    @Test
    /** Test that checks key pad up press is true*/
    public void keyUp(){
        test = new RPGame();
        assertTrue(test.keyUp(Input.Keys.UP));
        test.movePlayer();
    }
    /** Test that checks key pad down press is true*/
    @Test
    public void keyDown(){
        test = new RPGame();
        assertTrue(test.keyUp(Input.Keys.DOWN));
        test.movePlayer();

    }
    /** Test that checks key pad left press is true*/
    @Test
    public void keyLeft(){
        test = new RPGame();
        assertTrue(test.keyUp(Input.Keys.LEFT));
        test.movePlayer();

    }
    /** Tests player death*/
    @Test
    public void testplayerDeath(){
        test = new RPGame();
        player = new Player();
        player.setPlayerHealth(0);
    }
    /** Test to see check door overlap*/
    @Test
    public void testcheckforDoor(){
        test = new RPGame();
        assertTrue(test.getCurrentMap().isDoorOverlap(player.getPlayerBox()));
        test.checkForDoor();
    }
    /** Test to see if player is not touching door*/
    @Test
    public void testcheckforDoorFalse(){
        test = new RPGame();
        assertFalse(test.getCurrentMap().isDoorOverlap(player.getPlayerBox()));
        test.checkForDoor();
    }
    /** Tests title screen*/
    @Test
    public void testTitleLoader() {
        test = new RPGame();
        test.stepOnDoor = test.getCurrentMap().getDoorRectangleObject().getName();
        assertTrue(test.getCurrentMap().isDoorOverlap(player.getPlayerBox()));
        assertTrue(!test.stepOnDoor.equals(MainTitle));
        test.checkForDoor();
    }
    /** Test to current map render camera*/
    @Test
    public void testCurrMapview() {
        test = new RPGame();
        test.getCurrentMap().getTiledMapRenderer().setView(camera);
    }
    /** Test that create RPGgame's create method*/
    @Test
    public void testCreate(){
        test = new RPGame();
        test.create();
    }
    /** Tests RPGgame render*/
    @Test
    public void testRender(){
        test = new RPGame();
        test.render();
    }

    /** Test checks if map is not available*/
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
    /** Test maploader if true*/
    @Test
    public void testMapLoader(){
        test = new RPGame();
        assertTrue(test.getCurrentMap().isDoorOverlap(rectangle));
    }
    /** Test maploader if false*/
    @Test
    public void testMapLoaderfail(){
        test = new RPGame();
        assertTrue(test.getCurrentMap() != null);
    }
    /** Tests attack where player gets hit*/
    @Test
    public void testAttacking(){
        test = new RPGame();
        test.attacking();
        assertTrue(test.attacking());
        test.attacking();
    }
    @Test
    public void create() {
    }

    @Test
    public void render() {
    }

    @Test
    public void dispose() {
    }

    @Test
    public void isCollision() {
    }

    @Test
    public void movePlayer() {
    }

    @Test
    public void checkForDoor() {
    }

    @Test
    public void attacking() {
    }

    @Test
    public void isAttacked() {
    }

    @Test
    public void getCurrentMap() {
    }

    @Test
    public void keyDown1() {
    }

    @Test
    public void keyUp1() {
    }

    @Test
    public void keyTyped() {
    }

    @Test
    public void touchDown() {
    }

    @Test
    public void touchUp() {
    }

    @Test
    public void touchDragged() {
    }

    @Test
    public void mouseMoved() {
    }

    @Test
    public void scrolled() {
    }
    /** Test map files*/
    @Test
    public void testmapFiles(){
        test = new RPGame();
        assertTrue(ConquestOfAlengor == "ConquestOfAlengor.tmx" );
        assertTrue (Maintownwarehouse == "Maintownwarehouse.tmx");
        assertTrue(MaintownSWhome == "MaintownSWhome.tmx");
        assertTrue(MaintownNWhome == "MaintownNWhome.tmx");
        assertTrue(starthomeCOA == "starthomeCOA.tmx");
        assertTrue(Maintownhall == "Maintownhall.tmx");
        assertTrue( Mansion == "Mansion.tmx");


    }


//    @Test
//    public void
}
