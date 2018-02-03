package com.relativity.game.state.entities.types;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.relativity.game.state.GameMapLoader;
import com.relativity.game.state.entities.Entity;
import com.relativity.game.state.entities.EntitySnapshot;
import com.relativity.game.state.entities.EntityType;

public class PlayerToo extends Entity {
	
	private static final int SPEED = 80;
	private static final int JUMP_VELOCITY = 5;
	
	Texture image;
	
	@Override
	public void create(EntitySnapshot snapshot, EntityType type, GameMapLoader map) {
		super.create(snapshot, type, map);
		image = new Texture("player.png");
		//spawnRadius = snapshot.getFloat("spawnRadius", 50);
	}
	
	@Override
	public void update(float deltaTime, float gravity) {
		if (Gdx.input.isKeyPressed(Keys.SPACE) && grounded)
			this.velocityY += JUMP_VELOCITY * getWeight();
		else if (Gdx.input.isKeyPressed(Keys.SPACE) && !grounded && this.velocityY > 0)
			this.velocityY += JUMP_VELOCITY * getWeight() * deltaTime;
		
		super.update(deltaTime, gravity);//Apply gravity
		
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			moveX(-SPEED * deltaTime);
		
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			moveX(SPEED * deltaTime);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
		System.out.println("h");
	}
	
	@Override
	public EntitySnapshot getSaveSnapshot() {
		EntitySnapshot snapshot = super.getSaveSnapshot();
		//snapshot.putFloat("spawnRadius", spawnRadius);
		return snapshot;
	}

}