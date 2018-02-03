package com.relativity.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.relativity.game.RelativityGame;

public class DesktopLauncher {
	
	public static void main (String[] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		// The main menu configuration screen, 60 FPS as a target, while it should not be rendered while in the background to save energy
		config.width = RelativityGame.WIDTH;
		config.height = RelativityGame.HEIGHT;
		config.title = RelativityGame.TITLE;
		config.backgroundFPS = -1;
		config.foregroundFPS = 60;
		config.fullscreen = false;
		config.forceExit = true;
		config.addIcon("icon.png", FileType.Internal);
		
		new LwjglApplication(new RelativityGame(), config);
	}
}
