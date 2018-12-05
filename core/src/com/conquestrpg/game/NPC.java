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

/**
 * This class is used as the NPC main class that mainly loads the
 * object from the tilemap to the corresponding location along with
 * the image for the NPC.
 *
 * @author James Lund, Zachary Thomas, Matthew Doan, Aaron Brinkerhoff
 * @version Dec-4-2018
 */

public class NPC {

    /** SpriteBatch that holds the character*/
    protected SpriteBatch character;

    /** Texture to hold image file*/
    private Texture texture;

    /** Sprite to render to screen*/
    private Sprite sprite;

    /** Player box used for collisions, door handling and monsters*/
    private Rectangle playerBox;

    /** Holds the location of where to load NPC*/
    protected MapObject npcObject;

    /** Holds the location of the npc X*/
    protected float npcX;

    /** Holds the location of the npc Y*/
    protected float npcY;

    /** Holds the amount of movement for the player*/
    protected float move;

    /** Holds iterations of i*/
    protected float i;

    /**
     * The constructor for the NPC object tha makes the NPC
     * from the coordinates of the MapObject and loads
     * the image from a file in the assets folder.
     *
     * @param npcObject
     * @param image
     */
    public NPC(MapObject npcObject, String image){
        // Character
        this.npcObject = npcObject;
        character = new SpriteBatch();
        // Load file
        texture = new Texture(Gdx.files.internal(image));
        sprite = new Sprite(texture);
        playerBox = new Rectangle();


        // Get coordinates from tile map
        npcX = ((RectangleMapObject) npcObject).getRectangle().getX();
        npcY = ((RectangleMapObject) npcObject).getRectangle().getY();


        // Set position of NPC
        sprite.setPosition(npcX, npcY );
        playerBox.setPosition(sprite.getX(), sprite.getY());

         // For if enemy
         //move = -0.3f;
    }

    /**
     * Renders the NPC to the game with movement.
     */
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
        playerBox.setCenter(sprite.getX(), sprite.getY());



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
        return this.character;
    }

    /**
     * Accessor method which grabs the collision box around the player
     * @return playerBox
     */
    public Rectangle getPlayerBox(){
        return this.playerBox;
    }


}
