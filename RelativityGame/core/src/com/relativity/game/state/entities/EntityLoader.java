package com.relativity.game.state.entities;

import java.util.ArrayList;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.relativity.game.state.GameMapLoader;

public class EntityLoader {

	private static Json json = new Json();
	private static String id;
	
	public static ArrayList<Entity> loadEntities (GameMapLoader gameMapLoader, ArrayList<Object> requestedEntitys) {
		
		ArrayList<Entity> currentEntities = new ArrayList<Entity>();

		for(String entity : requestedEntitys) {
			currentEntities.add(EntityType.createEntityUsingSnapshot(loadPlayer(1500, 2000), gameMapLoader));

		}
		
		System.out.println("This currentEntities contains this: " + currentEntities.toString());
		return currentEntities;
		
	}
	
	public static void clearEntities() {
	}
	
	public static void buildEntityFile (String id, ArrayList<Entity> entities) {
		ArrayList<EntitySnapshot> snapshots = new ArrayList<EntitySnapshot>();
		for (Entity entity : entities)
			snapshots.add(entity.getSaveSnapshot());
		
		Gdx.files.local("entitiylist/").file().mkdirs();
		FileHandle file = Gdx.files.local("entitiylist/" + id + ".entities");
		file.writeString(json.prettyPrint(snapshots), false);
	}
	

/*	if (file.exists()) {
		EntitySnapshot[] snapshots = json.fromJson(EntitySnapshot[].class, file.readString());
		ArrayList<Entity> entities = new ArrayList<Entity>();
		for (EntitySnapshot snapshot : snapshots) {
			entities.add(EntityType.createEntityUsingSnapshot(snapshot, gameMapLoader));
		}
		return entities;
	} else {
		buildEntityFile(id, currentEntities);
		return currentEntities;
	}*/
	
	public static EntitySnapshot loadPlayer(int x, int y) {
		System.out.println("loadPlayer() : Running");
		return new EntitySnapshot("PLAYER", x, y);
	}
	
}
