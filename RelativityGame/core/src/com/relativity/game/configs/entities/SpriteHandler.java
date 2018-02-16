package com.relativity.game.configs.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.relativity.game.RelativityGame;
import static com.relativity.game.configs.GameMapLoader.PPM;

public class SpriteHandler {

	protected Body body;
	protected Animation animation;
	protected float width;
	protected float height;

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
		//System.out.println(((body.getPosition().x * PPM) - (width / 2)) + " " + ((body.getPosition().y * PPM) - (height / 2)));
		batch.begin();
		batch.draw(
				animation.getFrame(),
				body.getPosition().x * PPM - width / 2,
				body.getPosition().y * PPM - height / 2
			);
		batch.end();
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
