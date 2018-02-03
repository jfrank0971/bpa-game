package com.relativity.game.state.entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.relativity.game.state.GameMapLoader;

public class EntityLoader {

	private static Json json = new Json();
	
	public static ArrayList<Entity> loadEntities (String id, GameMapLoader gameMapLoader, ArrayList<Entity> currentEntities) {
		Gdx.files.local("assets/").file().mkdirs();
		FileHandle file = Gdx.files.local("assets/" + id + ".entities");
		
		if (file.exists()) {
			EntitySnapshot[] snapshots = json.fromJson(EntitySnapshot[].class, file.readString());
			ArrayList<Entity> entities = new ArrayList<Entity>();
			for (EntitySnapshot snapshot : snapshots) {
				entities.add(EntityType.createEntityUsingSnapshot(snapshot, gameMapLoader));
			}
			return entities;
		} else {
			saveEntities(id, currentEntities);
			return currentEntities;
		}
	}
	
	public static void saveEntities (String id, ArrayList<Entity> entities) {
		ArrayList<EntitySnapshot> snapshots = new ArrayList<EntitySnapshot>();
		for (Entity entity : entities)
			snapshots.add(entity.getSaveSnapshot());
		
		Gdx.files.local("assets/").file().mkdirs();
		FileHandle file = Gdx.files.local("assets/" + id + ".entities");
		file.writeString(json.prettyPrint(snapshots), false);
	}
	
}
