package com.conquestrpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Enemy extends GameObject {

    // How much health the enemy has
    protected int health;

    // How much damage the enemy will do per hit
    protected int damage;

    // spawn coordinates for the enemy
    protected int x,y;

    // The enemy's graphics
    SpriteBatch graphic;
    Sprite sprite;

    // Enemy
    ID id;

    // Creating the enemy
    public Enemy(int x, int y, int hp, int damage, SpriteBatch graphic, ID id){
        super(x,y,id,graphic);
        this.graphic = graphic;
        this.health = hp;
        this.damage = damage;
    }


    @Override
    public void tick() {

    }

    @Override
    public void render() {
        graphic.begin();
        sprite.draw(graphic);
        graphic.end();

    }

    @Override
    public Rectangle getBounds() {
        // Not sure if Gdx.graphics.getWidth() returns the value we want
        return new Rectangle(this.x,this.y,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }
}
