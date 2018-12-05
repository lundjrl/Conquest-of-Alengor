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
    protected SpriteBatch character;
    protected Texture texture;
    protected Sprite sprite;
    protected Rectangle playerBox;
    protected MapObject npcObject;
    protected float npcX;
    protected float npcY;
    protected float offset = 8.0f; // pixel offset for player collision

    protected float move;
    protected float i;

    NPC(MapObject npcObject, String image){
        // Character
        this.npcObject = npcObject;
        character = new SpriteBatch();
        texture = new Texture(Gdx.files.internal(image));
        sprite = new Sprite(texture);
        npcX = ((RectangleMapObject) npcObject).getRectangle().getX();
        npcY = ((RectangleMapObject) npcObject).getRectangle().getY();
        sprite.setPosition(npcX, npcY );

         // For if enemy
         //move = -0.3f;
    }

    public void render () {

        sprite.translateX(move);

        // Good for enemy movement
//        i++;
//        if( i == 100){
//            move = 0.3f;
//            i = -100;
//        } else if (i == 0){
//            move = -0.3f;
//        }

        // Organic movement
        move = move - .001f;

        if(move < -0.3f){
            move = .3f;
        }

        // Render character
        character.begin();
        sprite.draw(character);
        character.end();

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
