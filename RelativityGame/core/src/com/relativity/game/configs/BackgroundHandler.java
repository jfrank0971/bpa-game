package com.relativity.game.configs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.relativity.game.RelativityGame;

public class BackgroundHandler {

	private Texture bg;
	private OrthographicCamera cam;
	private int xMin, yMin, xMax, yMax;

	public BackgroundHandler(OrthographicCamera cam) {
		bg = new Texture(Gdx.files.internal("Background.png"));
		this.cam = cam;
	}

	public void render(SpriteBatch batch) {

		batch.begin();

		batch.draw(bg, 0, 0);

		batch.end();

	}

}
