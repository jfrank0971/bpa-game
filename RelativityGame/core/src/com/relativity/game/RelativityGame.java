package com.relativity.game;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.bullet.collision.btCollisionAlgorithmConstructionInfo;
import com.relativity.game.state.maps.HomeMenu;

public class RelativityGame extends Game {
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int WIDTH = (int) screenSize.getWidth();
	public static final int HEIGHT = (int) screenSize.getHeight();
	public static final int MWIDTH = (int) (screenSize.getWidth() / 2);
	public static final int MHEIGHT = (int) (screenSize.getHeight() / 2);
	public static final String TITLE = "RelativityDemo";
	public SpriteBatch batch; // SPRITE BATCH should not be created more than once to improve performance
	public Camera camera;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new HomeMenu(this));
	}

	@Override
	public void render () {;
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
	public void resize(int width, int height) {

	}
}
