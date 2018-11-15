package com.conquestrpg.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.conquestrpg.game.RPGame;


public class MenuState extends State{
    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm){
        super(gsm);
        background = new Texture("bg.jpg");
        //playBtn = new Texture("play.png");
    }

    @Override
    public void handleInput(){

    }

    @Override
    public void update(float dt){

    }

    @Override
    public void render(SpriteBatch sb){
        sb.begin();
        sb.draw(background, 0,0, 1000, 800);
        sb.draw(playBtn, (1000/2) - (playBtn.getWidth()/2), (800/2));
        sb.end();
    }
}
