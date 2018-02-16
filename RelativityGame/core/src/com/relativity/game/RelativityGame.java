package com.relativity.game;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.relativity.game.configs.entities.InputHandler;
import com.relativity.game.configs.maps.HomeMenu;

public class RelativityGame extends Game {

	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int WIDTH = (int) screenSize.getWidth();
	public static final int HEIGHT = (int) screenSize.getHeight();
	public static final int MWIDTH = (int) (screenSize.getWidth() / 2);
	public static final int MHEIGHT = (int) (screenSize.getHeight() / 2);
	public static final String TITLE = "Relativity";
	private SpriteBatch batch; // SPRITE BATCH should not be created more than once to improve performance
	private OrthographicCamera txtCam;

	@Override
	public void create() {
		this.batch = new SpriteBatch();
		this.txtCam = new OrthographicCamera();
		this.txtCam.setToOrtho(false, WIDTH, HEIGHT);
		Gdx.input.setInputProcessor(new InputHandler());
		this.setScreen(new HomeMenu(this, txtCam));
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
	
	public void resize(int width, int height) {

	}
	
	public SpriteBatch getBatch() {
		return batch;
	}
}
