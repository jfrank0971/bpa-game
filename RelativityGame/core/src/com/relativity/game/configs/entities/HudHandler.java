package com.relativity.game.configs.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.relativity.game.configs.entities.types.Player;

/**
 * OrthographicCamera that cannot go beyond specified rectangle.
 */
public class HudHandler {

	private Player player;
	private Texture image;
	TextureRegion hudElements;
	
	public HudHandler(Player player) {
		this.player = player;
		image = new Texture(Gdx.files.internal("bullet.png"));

	}
	
	public void render(SpriteBatch batch) {
		batch.begin();
		
		batch.draw(image, 32, 32);
		
		batch.end();
	}
}
