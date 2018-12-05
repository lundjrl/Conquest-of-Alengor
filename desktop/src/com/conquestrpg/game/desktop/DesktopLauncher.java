package com.conquestrpg.game.desktop;

import com.badlogic.gdx.Application; // Set up window and logging
import com.badlogic.gdx.Gdx; // Graphics, Audio, Input, Files and Net modules
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.conquestrpg.game.RPGame;
import com.conquestrpg.game.Screens.TitleScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		// Setup Configuration
		config.title = "Conquest of Alengor"; // Title on window
		config.useGL30 = false; // Use OpenGL 2.0
		config.width = 1000;
		config.height = 800;

		// Setup application
//		game.setScreen(TitleScreen(game);
		RPGame game = new RPGame();
		Application app = new LwjglApplication(new TitleScreen(game), config);
//		this.setScreen(new TitleScreen(this));



		// Logging
		Gdx.app = app;
		//Gdx.app.setLogLevel(Application.LOG_INFO);
		//Gdx.app.setLogLevel(Application.LOG_DEBUG);
		//Gdx.app.setLogLevel(Application.LOG_ERROR);
		//Gdx.app.setLogLevel(Application.LOG_NONE)

	}
}
