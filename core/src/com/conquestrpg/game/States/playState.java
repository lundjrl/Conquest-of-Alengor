package com.conquestrpg.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class playState extends State{

    private Texture character;

    public playState(GameStateManager gsm) {
        super(gsm);
        character = new Texture("Main.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(character, 50, 50);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
