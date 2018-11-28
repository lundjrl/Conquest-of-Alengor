package com.conquestrpg.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.conquestrpg.game.RPGame;

public class TitleScreen implements Screen{

    private static final int PLAY_BUTTON_W = 200;
    private static final int PLAY_BUTTON_H = 150;

    RPGame game;

    Texture playButton;
    Texture playButtonNot;


    public TitleScreen(RPGame game){
        this.game = game;
        playButton = new Texture("play.png");

    }

    @Override
    public void show(){

    }

    @Override
    public void render(float delta){

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(playButton, 100, 100, PLAY_BUTTON_W, PLAY_BUTTON_H);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height){

    }

    @Override
    public void pause(){

    }

    @Override
    public void resume(){

    }

    @Override
    public void hide(){

    }

    @Override
    public void dispose(){

    }

}
