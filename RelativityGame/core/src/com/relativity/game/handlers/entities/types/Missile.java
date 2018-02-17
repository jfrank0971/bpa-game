package com.relativity.game.handlers.entities.types;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.relativity.game.handlers.entities.SpriteHandler;

/**
 * Creates the methods necessary for the missile to interact with the world
 * 
 * @version BPA SET 2018
 * @author Jacob Frank, Jerry Zeng, and Eddie Tang
 */

public class Missile extends SpriteHandler {

	Texture image;
	TextureRegion[] states;
	static Body body;
	float initialX, initialY;

	public Missile(Body body) {
		super(body);
		this.body = body;
		initialX = body.getPosition().x;
		initialY = body.getPosition().y;

		image = new Texture(Gdx.files.internal("Missile.png"));

		TextureRegion[] tReg = TextureRegion.split(image, 100, 100)[0];

		setAnimation(tReg, 0f);
	}
}
