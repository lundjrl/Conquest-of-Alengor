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



public class Player {

    // Player
    private SpriteBatch character;
    private Texture texture;
    private Sprite sprite;
    private Rectangle playerBox;
    private float offset = 8.0f; // pixel offset for player collision
    private float x, y;

    Player(){
        // Character
        character = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("Main.png"));
        sprite = new Sprite(texture);


        // Move in multiples of 16
        sprite.translate(992, 336);
        playerBox = new Rectangle(sprite.getX() + offset, sprite.getY(), 16.0f, 0.5f); // For collisions


    }

    public void render () {
        // Render character
        //character.setProjectionMatrix(camera.combined);
        character.begin();
        sprite.draw(character);
        character.end();
        playerBox.setCenter(sprite.getX() + offset, sprite.getY());
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

    public void setPosition(float x, float y){

    }

}
