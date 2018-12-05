package com.conquestrpg.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.conquestrpg.game.RPGame;

public class TitleScreen extends Game implements Screen{

    private static final int PLAY_BUTTON_W = 200;
    private static final int PLAY_BUTTON_H = 150;
    private static final int PLAY_BUTTON_Y = 230;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;


    final RPGame game;

    Texture playButton;
    Texture playButtonNot;


    public TitleScreen(final RPGame game){
        this.game = game;
        playButton = new Texture("play.png");
        final TitleScreen mainMenuScreen = this;
//        Gdx.input.setInputProcessor(inputAdapter()){




    Gdx.input.setInputProcessor(new InputAdapter() {

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            int x = 1000 / 2 - PLAY_BUTTON_W / 2;
//            if (game.camera.getInputInGameWorld().x < x + PLAY_BUTTON_W && game.camera.getInputInGameWorld().x > x && 800 - game.camera.getInputInGameWorld().y < PLAY_BUTTON_Y + PLAY_BUTTON_H  && 800- game.camera.getInputInGameWorld().y > PLAY_BUTTON_Y) {
                mainMenuScreen.dispose();
                System.out.println("XDDDD");

//            }

            return super.touchUp(screenX, screenY, pointer, button);
        }

    });
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
    public void create() {

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
        Gdx.input.setInputProcessor(null);
    }


}
