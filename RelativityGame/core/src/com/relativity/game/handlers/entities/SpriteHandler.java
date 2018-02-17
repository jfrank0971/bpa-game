package com.relativity.game.handlers.entities;

import static com.relativity.game.handlers.GameMapLoader.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.relativity.game.RelativityGame;
import com.relativity.game.handlers.entities.types.Orb;

/**
 * 	<p>
 * 	This class mostly handles the rendering of Sprites (Orb, Player, Missile) that extend it
 * 	</p>
 * @version BPA SET 2018 
 * @author Jacob Frank, Jerry Zeng, and Eddie Tang
 */


public class SpriteHandler {

	protected Body body;
	protected Animation animation;
	protected float width;
	protected float height;
	protected boolean alive = true;

	public SpriteHandler(Body body) {
		this.body = body;
		animation = new Animation();
	}

	public void setAnimation(TextureRegion reg, float delay) {
		setAnimation(new TextureRegion[] { reg }, delay);
	}

	public void setAnimation(TextureRegion[] reg, float delay) {
		animation.setFrame(reg, delay);
		width = reg[0].getRegionWidth();
		height = reg[0].getRegionHeight();
	}

	public void update(float dt) {
		animation.update(dt);
	}

	public void render(SpriteBatch batch) {
		if (alive == true) {
			batch.begin();
			batch.draw(
					animation.getFrame(),
					body.getPosition().x * PPM - width / 2,
					body.getPosition().y * PPM - height / 2
					);
			batch.end();	
		}
	}

	public Body getBody() {
		return body;
	}

	public Vector2 getPosition() {
		return body.getPosition();
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

}
