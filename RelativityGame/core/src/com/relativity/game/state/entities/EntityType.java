package com.relativity.game.state.entities;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.relativity.game.state.GameMapLoader;
import com.relativity.game.state.entities.types.Player;
import com.relativity.game.state.entities.types.PlayerToo;

@SuppressWarnings("rawtypes")
public enum EntityType {
	
	PLAYER("player", Player.class, 14, 32, 40),
	PLAYERTOO("playerToo", PlayerToo.class, 14, 32, 0);
	
	private String id;
	private Class loaderClass;
	private int width, height;
	private float weight;
	private static HashMap<String, EntityType> entityTypes;
	
	private EntityType(String id, Class loaderClass, int width, int height, float weight) {
		this.id = id;
		this.loaderClass = loaderClass;
		this.width = width;
		this.height = height;
		this.weight = weight;
	}

	static {
		entityTypes = new HashMap<String, EntityType>();
		for (EntityType type : EntityType.values()) {
			entityTypes.put(type.id.toUpperCase(), type);
		}
	}
	
	public String getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float getWeight() {
		return weight;
	}
	
	public static Entity createEntityUsingSnapshot (EntitySnapshot entitySnapshot, GameMapLoader gameMapLoader) {
		EntityType type = entityTypes.get(entitySnapshot.type);
		try {
			@SuppressWarnings("unchecked")
			Entity entity = ClassReflection.newInstance(type.loaderClass);
			entity.create(entitySnapshot, type, gameMapLoader);
			System.out.println("Entity is this: " + entity.getType());
			return entity;
		} catch (ReflectionException e) {
			Gdx.app.error("Entity Loader", "Could not load entity of type " + type.id);
			return null;
		}
	}
	
	
	
	
}