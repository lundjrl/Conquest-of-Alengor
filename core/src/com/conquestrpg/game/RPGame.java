package com.conquestrpg.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
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
	Music music;

	// Movement
    float characterSpeed = 5.0f;
    float characterX;
    float characterY;

    // Tilemap rendering
    int[] background = {0,3};
    int[] overlay = {1,2,4,5,6};


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
		music = Gdx.audio.newMusic(Gdx.files.internal("NiGiD_-_Speculation_Sheet.mp3"));
		//Set this below audio to maybe a boss fight or while talking to an NPC
		//music = Gdx.audio.newMusic(Gdx.files.internal("Lounge Game2.wav"));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
		// Camera Start
		camera.translate(800, 270);


		Gdx.input.setInputProcessor(this);


		// Character
		character = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("Main.png"));
		sprite = new Sprite(texture);

		// Move in multiples of 16
		sprite.translate(992, 336);

		// Set Camera position the same as the character
		camera.position.set(sprite.getX(), sprite.getY(), 0);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		// Input
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
            characterX -= Gdx.graphics.getDeltaTime() * characterSpeed;
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
            characterX += Gdx.graphics.getDeltaTime() * characterSpeed;
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))
            characterY += Gdx.graphics.getDeltaTime() * characterSpeed;
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))
            characterY -= Gdx.graphics.getDeltaTime() * characterSpeed;



        sprite.translate(characterX, characterY);
        camera.position.set(sprite.getX(), sprite.getY(), 0);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update(); //This changes camera position
		tiledMapRenderer.setView(camera); //type something or whatever

        // Render below character
		tiledMapRenderer.render(background);

		// Render character
		character.setProjectionMatrix(camera.combined);
		character.begin();
		sprite.draw(character);
		character.end();

        // Render over character
        tiledMapRenderer.render(overlay);

	}
	
	@Override
	public void dispose(){
		super.dispose();
		music.dispose();
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
	//	if(keycode == Input.Keys.LEFT)
	//		sprite.translate(-16, 0);
	//	if(keycode == Input.Keys.RIGHT)
	//		sprite.translate(16, 0);;
	//	if(keycode == Input.Keys.UP)
	//		sprite.translate(0, 16);
	//	if(keycode == Input.Keys.DOWN)
	//		sprite.translate(0, -16);

		// Move camera into position
		camera.position.set(sprite.getX(), sprite.getY(), 0);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//Vector3 clickCoordinates = new Vector3(screenX,screenY,0);
		//Vector3 position = camera.unproject(clickCoordinates);
		//sprite.setPosition(position.x, position.y);
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
