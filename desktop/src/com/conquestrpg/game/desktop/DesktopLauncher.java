package com.conquestrpg.game.desktop;

import com.badlogic.gdx.Application; // Set up window and logging
import com.badlogic.gdx.Gdx; // Graphics, Audio, Input, Files and Net modules
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.conquestrpg.game.RPGame;
import com.conquestrpg.game.Screens.TitleScreen;

/**
 * This DesktopLauncher launches the RPGame. See RPGame for documentation
 * on the RPGame class. The DesktopLauncher makes a light weight game
 * library application and sets the window name and window width and height.
 * @author James Lund, Zachary Thomas, Matthew Doan, Aaron Brinkerhoff
 * @version Dec-4-2018
 */

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		/** Setup Configuration */
		config.title = "Conquest of Alengor"; // Title on window
		config.useGL30 = false; // Use OpenGL 2.0
		config.width = 1000;
		/** */
		config.height = 800;

		/** Setup application, first we are creating a new game object*/
		// Setup application
		RPGame game = new RPGame();
		/** Now we are creating an application to run on our desktop */
		Application app = new LwjglApplication((game), config);
		/** Logging */
		Gdx.app = app;


	}
}
