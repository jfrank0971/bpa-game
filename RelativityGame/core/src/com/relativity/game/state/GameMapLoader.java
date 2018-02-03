package com.relativity.game.state;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.relativity.game.RelativityGame;
import com.relativity.game.state.entities.Entity;
import com.relativity.game.state.entities.EntityLoader;
import com.relativity.game.state.entities.Player;

public class GameMapLoader {

	TiledMap tiledMap;
	OrthogonalTiledMapRenderer tiledMapRenderer;

	public ArrayList<Entity> entities;
	RelativityGame rg;
	OrthographicCamera camera;

	String id;
	String name;
	int[][][] map;
	private TextureRegion[][] tiles;
	int mapWidth, mapHeight, tilePixelWidth, tilePixelHeight;
	Entity entity;
	
	public void loader(String map, RelativityGame rg) {

		this.rg = rg;
		tiledMap = new TmxMapLoader().load(map);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		MapProperties prop = tiledMap.getProperties();
		mapWidth = prop.get("width", Integer.class) - 1;
		mapHeight = prop.get("height", Integer.class) - 1;
		tilePixelWidth = prop.get("tilewidth", Integer.class);
		tilePixelHeight = prop.get("tileheight", Integer.class);

		entities = new ArrayList<Entity>();
		entities.addAll(EntityLoader.loadEntities("basic", this, entities));


		camera = new OrthographicCamera();
		camera.setToOrtho(false, RelativityGame.WIDTH, RelativityGame.HEIGHT);
		
		
		
	}

	public void render(OrthographicCamera camera, SpriteBatch batch) {

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		rg.batch.begin();
		batch.setProjectionMatrix(camera.combined);
		
		for (Entity entity : entities) {
			entity.render(rg.batch);
		}

		rg.batch.end();

		if (Gdx.input.isTouched()) {
			camera.translate(-Gdx.input.getDeltaX() * 2, Gdx.input.getDeltaY() * 2);
		}
		if (Gdx.input.justTouched()) {
			Vector3 pos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			TileConfig config = getTileConfigByLocation(0, pos.x, pos.y);
			if (config != null)
				System.out.println(pos.x + " : " + pos.y);
				//System.out.println(config.getName());
		}
		update(Gdx.graphics.getDeltaTime());
		camera.update();
	}

	public boolean doesRectCollideWithMap(float x, float y, int width, int height) {
		if (x < 0 || y < 0 || x + width > getUnitWidth() || y + height > getUnitHeight())
			return true;

		for (int row = (int) (y / TileConfig.TILE_SIZE); row < Math.ceil((y + height) / TileConfig.TILE_SIZE); row++) {
			for (int col = (int) (x / TileConfig.TILE_SIZE); col < Math
					.ceil((x + width) / TileConfig.TILE_SIZE); col++) {
				for (int layer = 0; layer < getLayers(); layer++) {
					TileConfig type = getTileConfigByCoordinate(layer, col, row);
					if (type != null && type.isSolid())
						return true;
				}
			}
		}

		return false;
	}

	public TileConfig getTileConfigByCoordinate(int layer, int col, int row) {
		
		Cell cell = ((TiledMapTileLayer) tiledMap.getLayers().get(layer)).getCell(col, row);

		if (cell != null) {
			TiledMapTile tile = cell.getTile();

			if (tile != null) {
				int id = tile.getId();
				return TileConfig.getTileConfigById(id);
			}
		}
		return null;

	}

	public TileConfig getTileConfigByLocation(int layer, float x, float y) {
		return this.getTileConfigByCoordinate(layer, (int) (x / TileConfig.TILE_SIZE),
				(int) (y / TileConfig.TILE_SIZE) - 1);
	}

	public void update(float dt) {
		for (Entity entity : entities) {
			entity.update(dt, -9.8f);
		}

		if (Gdx.input.isKeyJustPressed(Keys.S)) {
			EntityLoader.saveEntities("basic", entities);
		}
	}

	public void dispose() {
		tiledMap.dispose();
	}

	public int getWidth() {
		return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
	}

	public int getHeight() {
		return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
	}

	public int getLayers() {
		return tiledMap.getLayers().getCount();
	}

	public int getUnitWidth() {
		return this.getWidth() * TileConfig.TILE_SIZE;
	}

	public int getUnitHeight() {
		return this.getHeight() * TileConfig.TILE_SIZE;
	}

	public TiledMapRenderer getTMapRenderer() {
		return tiledMapRenderer;
	}

	public OrthographicCamera getCam() {
		return camera;
	}

}
