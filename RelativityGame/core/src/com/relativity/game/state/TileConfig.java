package com.relativity.game.state;

import java.util.HashMap;

public enum TileConfig {

	GRASS(1 , true, "Grass"),
	DIRT(2 , true, "Dirt"),
	SKY(3 , false, "Sky"),
	LAVA(4 , false, "Lava"),
	CLOUD(5 , true, "Cloud"),
	STONE(6 , true, "Stone");
	
	private int id;
	private boolean solid;
	private String name;
	private float dmg;
	public static final int TILE_SIZE = 64;
	private static HashMap<Integer, TileConfig> tileHMap;
	
	static {
		tileHMap = new HashMap<Integer, TileConfig>();
		for (TileConfig tileConfig : TileConfig.values()) {
			tileHMap.put(tileConfig.getId(), tileConfig);
			System.out.println("Placed: " + tileConfig.getId());
		}
	}

	private TileConfig (int id, boolean solid, String name) {
		this(id, solid, name, 0);
	}
	
	private TileConfig (int id, boolean solid, String name, float dmg) {
		this.id = id;
		this.solid = solid;
		this.name = name;
		this.dmg = dmg;
	}
	
	public static TileConfig getTileConfigById(int id) {
		return tileHMap.get(id);
	}

	public int getId() {
		return id;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public String getName() {
		return name;
	}
	
	public float getDmg() {
		return dmg;
	}
	

}
