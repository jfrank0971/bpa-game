package com.relativity.game.configs.entities;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.relativity.game.configs.GameMapLoader;
import com.relativity.game.configs.entities.types.Missile;
import com.relativity.game.configs.entities.types.Orb;
import com.relativity.game.configs.entities.types.Player;

@SuppressWarnings("rawtypes")
public enum EntityType {
	
	PLAYER("Player", Player.class, 32, 32, 40),
	ORB("Orb", Orb.class, 50, 50, 0),
	BULLET("Bullet", Missile.class, 15, 15, 5);
	
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
		}
		return null;
	}
	
	
	
	
}