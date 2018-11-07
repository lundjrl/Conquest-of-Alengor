package com.conquestrpg.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.conquestrpg.screens.MainGameScreen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

public class  RPGame extends ApplicationAdapter implements InputProcessor {
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;

	SpriteBatch character;
	Texture texture;
	Sprite sprite;


	@Override
	public void create () {

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		//camera.setToOrtho(false, (w), (h));
		//Scale
		camera.setToOrtho(false, (w/3), (h/3));
		camera.update();
		tiledMap = new TmxMapLoader().load("ConquestOfAlengor.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		// Camera Start
		camera.translate(800, 270);


		Gdx.input.setInputProcessor(this);


		// Character
		character = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("Main.png"));
		sprite = new Sprite(texture);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update(); //This changes camera position
		tiledMapRenderer.setView(camera); //type something or whatever
		tiledMapRenderer.render();

		// Render character
		character.setProjectionMatrix(camera.combined);
		character.begin();;
		sprite.draw(character);
		character.end();

	}
	
	@Override
	public void dispose(){
		//batch.dispose();
		//img.dispose();

		//mainGameScreen.dispose();

		//batch.dispose();
		//img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Input.Keys.LEFT)
			camera.translate(-32,0);
		if(keycode == Input.Keys.RIGHT)
			camera.translate(32,0);
		if(keycode == Input.Keys.UP)
			camera.translate(0,32);
		if(keycode == Input.Keys.DOWN)
			camera.translate(0,-32);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 clickCoordinates = new Vector3(screenX,screenY,0);
		Vector3 position = camera.unproject(clickCoordinates);
		sprite.setPosition(position.x, position.y);
		return true;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
