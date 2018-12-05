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

    Monster(MapObject npcObject,String image, int health, int damage) {
        super(npcObject,image);
        this.health = health;
        this.damage = damage;
    }

    public void render(){
        super.render();
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
        return super.character;
    }

    public Rectangle getPlayerBox(){
        return this.playerBox;
    }

}
