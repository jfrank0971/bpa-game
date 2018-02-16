package com.relativity.game.handlers.entities.types;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.relativity.game.handlers.entities.SpriteHandler;

public class Player extends SpriteHandler {

	private static final float SPEED = 200f;
	private static final float SPEED_BONUS = 50f;
	private static final int JUMP_VELOCITY = 250;
	private static int footContact = 0;
	private static boolean facingRight = true;

	private static Texture image;
	private static TextureRegion[] states;
	private static boolean onGround;
	private static int jumps;
	private static Body body;

	public Player(Body body) {
		super(body);
		this.body = body;
		image = new Texture(Gdx.files.internal("playerAnimation.png"));

		TextureRegion[] playerTexture = TextureRegion.split(image, 100, 100)[0];
		setAnimation(playerTexture, 1 / 64f);
	}

	public static void jump(boolean forceJump) {

		if (forceJump) {
			body.setLinearVelocity(body.getLinearVelocity().x, 0);
			body.applyForceToCenter(0, JUMP_VELOCITY, true);
		} else if (footContact > 1) {
			body.setLinearVelocity(body.getLinearVelocity().x, 0);
			body.applyForceToCenter(0, JUMP_VELOCITY, true);
		}
	}

	public static void walk() {

		if (!onGround) {
			if (facingRight) {
				body.setLinearVelocity(0, body.getLinearVelocity().y);
				body.applyForceToCenter(SPEED + SPEED_BONUS, 0, true);
			} else {
				body.setLinearVelocity(0, body.getLinearVelocity().y);
				body.applyForceToCenter(-SPEED - SPEED_BONUS, 0, true);
			}
		} else {
			if (facingRight) {
				body.setLinearVelocity(0, body.getLinearVelocity().y);
				body.applyForceToCenter(SPEED, 0, true);
			} else {
				body.setLinearVelocity(0, body.getLinearVelocity().y);
				body.applyForceToCenter(-SPEED, 0, true);
			}
		}
	}

	public static int getFootContact() {
		return footContact;
	}

	public static void incrementFootContact(int i) {
		if (i > 0) {
			footContact++;
		} else {
			footContact--;
		}

		if (footContact > 0) {
			onGround = true;
		} else {
			onGround = false;
		}

	}

	public static void setFacingRight(boolean fr) {
		facingRight = fr;
	}

	public static boolean getFacingRight() {
		return facingRight;
	}

	public static void setJumps(int jumps) {
		Player.jumps = jumps;
	}

}