package com.relativity.game.handlers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.relativity.game.RelativityGame;
import com.relativity.game.handlers.entities.types.Player;

/**
 * 
 * This class controls all Hud Elements (Hearts)
 * 
 * @version BPA SET 2018
 * @author Jacob Frank, Jerry Zeng, and Eddie Tang
 */

public class HudHandler {

	private static Player player;
	private static TextureRegion[] hudItems;
	private static HashMap<String, Texture> hudElements;

	public HudHandler(Player player) {
		this.player = player;
		hudElements = new HashMap<>();

		hudElements.put("Flask", new Texture(Gdx.files.internal("HudItemFlask.png")));
		hudElements.put("Flask2", new Texture(Gdx.files.internal("HudItemFlask2.png")));
		hudElements.put("Heart", new Texture(Gdx.files.internal("HudItemHeart.png")));

	}

	public void render(SpriteBatch batch) {
		int health = Player.getHealth();

		batch.begin();

		for (int i = 1; i <= health; i++) {
			batch.draw(hudElements.get("Heart"), 32 + hudElements.get("Heart").getWidth() * i,
					RelativityGame.HEIGHT - hudElements.get("Heart").getHeight() - 50);
		}

		batch.end();
	}
}
