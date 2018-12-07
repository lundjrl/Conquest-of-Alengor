package com.conquestrpg.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;

/**
 * Class that holds all the information of the monster entity in the COA game.
 * @author: James Lund, Zachary Thomas, Matthew Doan, Aaron Brinkerhoff
 * @version: Dec-4-2018
 * Purpose: Final Project for Software Engineering, CIS 350-01, GVSU
 */
public class Monster extends NPC {

    /** Sprite to render to screen*/
    private Sprite sprite;

    /** Player box used for collisions, door handling and monsters*/
    private Rectangle playerBox;

    /** Holds the location of the npc X*/
    protected float npcX;

    /** Holds the location of the npc Y*/
    protected float npcY;

    /** Holds iterations of i*/
    protected float i;
    /** Variable to keep track of the amount of damage a monster does */
    private int damage;
    /** Variable to keep track of the amount of health a monster has */
    private int health;

    /**
     * Constructor for the monster class. Calls the super constructor of an NPC object to imitate the facilities
     * withheld in there.
     * @param npcObject
     * @param image
     * @param health
     * @param damage
     */
    public Monster(MapObject npcObject,String image, int health, int damage) {
        super(npcObject,image);
        this.health = health;
        this.damage = damage;
    }

    /**
     * Renders the monster object to the screen
     */
    public void render(){
        // Calls the super class to render a monster object
        super.render();
    }

    /**
     * Accessor method to grab the amount of damage the player has endured.
     * @return damage
     */
    public int getDamage(){
        return this.damage;
    }

    /**
     * Accessor method to grab the NPC X-coordinate
     * @return npcX
     */
    public float getNPCx(){
        return this.npcX;
    }

    /**
     * Accessor method to grab the NPC Y-coordinate
     * @return npcY
     */
    public float getNPCy(){
        return this.npcY;
    }

    /**
     * Accessor method that grabs the monster sprite
     * @return sprite
     */
    public Sprite getSprite(){
        return  this.sprite;
    }

    /**
     * Accessor method that grabs the character super
     * @return super.character
     */
    public SpriteBatch getCharacter(){
        return super.character;
    }

    /**
     * Accessor method which grabs the collision box around the player
     * @return playerBox
     */
    public Rectangle getPlayerBox(){
        return this.playerBox;
    }

}
