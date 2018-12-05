package com.conquestrpg.game;

import java.util.UUID;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * This class is used as the Player main class where health, textures,
 * sprites and rectangle are held for user input in the RPGame class.
 *
 * @author James Lund, Zachary Thomas, Matthew Doan, Aaron Brinkerhoff
 * @version Dec-4-2018
 */

public class Player {

    /** SpriteBatch that holds the character*/
    private SpriteBatch character;

    /** Texture to hold image file*/
    private Texture texture;

    /** Sprite to render to screen*/
    private Sprite sprite;

    /** Player box used for collisions, door handling and monsters*/
    private Rectangle playerBox;

    /** Holds the health of the player*/
    private int health;

    /** Holds the base damage of the player*/
    private int baseDamage;

    /**
     * The Player constructor sets up with a main .png to load
     * and to render later on.
     */
    public Player(){
        // Character
        character = new SpriteBatch();
        // Load .png file
        texture = new Texture(Gdx.files.internal("Main.png"));
        sprite = new Sprite(texture);
        playerBox = new Rectangle();

        // Set baseDamage and health
        baseDamage = 15;
        health = 200;

    }

    /**
     * Render method used in RPGame class to render player
     * @return none
     */
    public void render () {
        // Render character
        character.begin();
        sprite.draw(character);
        character.end();
        playerBox.setCenter(sprite.getX(), sprite.getY());
    }

    /**
     * Get the sprite
     * @return the sprite
     */
    public Sprite getSprite(){
        return  this.sprite;
    }

    /**
     * Get the character batch
     * @return the character batch
     */
    public SpriteBatch getCharacter(){
        return this.character;
    }

    /**
     * Get the player box
     * @return the player box
     */
    public Rectangle getPlayerBox(){
        return this.playerBox;
    }

    /**
     * Set the position of x and y coordinates
     * @param x corresponds to X coordinate
     * @param y corresponds to Y coordinate
     */
    public void setPosition(float x, float y){
        this.sprite.setPosition(x,y);
    }

    /**
     * Set the player health
     * @param health the player health
     */
    public void setPlayerHealth(int health){
        this.health = health;
    }

    /**
     * Get the player health
     * @return the player health
     */
    public int getPlayerHealth(){
        return this.health;
    }



}
