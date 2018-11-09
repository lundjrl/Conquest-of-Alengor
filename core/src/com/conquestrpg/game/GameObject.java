package com.conquestrpg.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

/**
 * Defining aspects of a "Game Object".
 */
public abstract class GameObject {

    //Coordinate position for Object.
    protected int x,y;

    //Type of Object.
    protected ID id;

    //Graphics for object.
    SpriteBatch objectGraphic;


    public GameObject(int x, int y , ID id, SpriteBatch graphic){
        this.x = x;
        this.y = y;
        this.id = id;
        this.objectGraphic = graphic;
    }

    // This method would update a movable object's position.
    public abstract void tick();

    // This method would update an Objects Graphics (EX: Player or Enemy, walking, attacking, defending, etc).
    public abstract void render();

    // This method would be used to return a rectangle representation of an Objects bounds for collision purposes.
    public abstract Rectangle getBounds();



}
