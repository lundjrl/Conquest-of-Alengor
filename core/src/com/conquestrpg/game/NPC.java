package com.conquestrpg.game;

import java.util.UUID;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;



public class NPC {

    // Player
    private SpriteBatch character;
    private Texture texture;
    private Sprite sprite;
    private Rectangle playerBox;
    private MapObject npcObject;
    private float npcX;
    private float npcY;
    private float offset = 8.0f; // pixel offset for player collision

    NPC(MapObject npcObject){
        // Character
        this.npcObject = npcObject;
        character = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("Main.png"));
        sprite = new Sprite(texture);


        // Move in multiples of 16
        //sprite.translate(992, 336);
        //playerBox = new Rectangle(sprite.getX() + offset, sprite.getY(), 16.0f, 0.5f); // For collisions
         npcX = ((RectangleMapObject) npcObject).getRectangle().getX();
         npcY = ((RectangleMapObject) npcObject).getRectangle().getY();


    }

    public void render () {


        sprite.translate(npcX, npcY);
        // Render character
        //character.setProjectionMatrix(camera.combined);
        character.begin();
        sprite.draw(character);
        character.end();
        //playerBox.setCenter(sprite.getX() + offset, sprite.getY());




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
