package com.conquestrpg.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;

public class Monster extends NPC {

    private SpriteBatch character;
    private Texture texture;
    private Sprite sprite;
    private Rectangle playerBox;
    private MapObject npcObject;
    private float npcX;
    private float npcY;
    private float offset = 8.0f; // pixel offset for player collision

    private float move;
    private float i;
    private int damage;
    private int health;
    private boolean isAlive;

    Monster(MapObject npcObject) {
        super(npcObject);
        isAlive = true;
        health = 200;
        tempHealth = health;
        damage = 20;
    }

    public void render(){
        if(playerBox.getX() - getPlayerBox().getX() < 30 && playerBox.getY() - getPlayerBox().getY() < 40 && isAlive ){
            attack();
        }
        // Move NPC
//        move = move -.001f;
//
//        if(move < -0.3f){
//            move = .03f;
//        }
//
//        sprite.translateX(move);
//        i = i - .001f;

//        if(i > -0.3f){
//            i = 0.3f;
//        }

        sprite.translateX(move);

        i++;

        // Good for enemy movement
        if( i == 100){
            move = 0.3f;
            i = -100;
        } else if (i == 0){
            move = -0.3f;
        }

        // Organic movement
        move = move - .001f;

        if(move < -0.3f){
            move = .3f;
        }

        //System.out.println(move);



        // Render character
        //character.setProjectionMatrix(camera.combined);
        character.begin();
        sprite.draw(character);
        character.end();
        //playerBox.setCenter(sprite.getX() + offset, sprite.getY());

    }
    public int getDamage(){
        return this.damage;
    }

    public float getNPCx(){
        return this.npcX;
    }

    public float getNPCy(){
        return this.npcY;
    }

    public Sprite getSprite(){
        return  this.sprite;
    }

    public SpriteBatch getCharacter(){
        return this.character;
    }

    public Rectangle getPlayerBox(){
        return this.playerBox;
    }

}
