package com.relativity.game.configs.entities.types;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.relativity.game.configs.entities.SpriteHandler;

public class Orb extends SpriteHandler {

	Texture image;
	TextureRegion[] states;
	static Body body;

	public Orb(Body body) {
		super(body);
		this.body = body;
		image = new Texture(Gdx.files.internal("orb.png"));

		TextureRegion[] tReg = TextureRegion.split(image, 100, 100)[0];

		setAnimation(tReg, 1 / 64f);
	}

	public static void generateExplosion() {

		// sendMissile()

	}

}
