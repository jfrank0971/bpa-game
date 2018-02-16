package com.relativity.game.configs;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.relativity.game.RelativityGame;
import com.relativity.game.configs.entities.CollisionHandler;
import com.relativity.game.configs.entities.HudHandler;
import com.relativity.game.configs.entities.SpriteHandler;
import com.relativity.game.configs.entities.types.Missile;
import com.relativity.game.configs.entities.types.Orb;
import com.relativity.game.configs.entities.types.Player;
import com.relativity.game.configs.maps.HomeMenu;

public class GameMapLoader {

	public static final float PPM = 100;
	public static final float GRAVITY = -4.8f;

	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer tiledMapRenderer;

	private RelativityGame rg;
	private CollisionHandler ch;

	private BodyDef bDef;
	private PolygonShape polyShape;
	private FixtureDef fDef;
	private Body body;
	private World world;
	private Box2DDebugRenderer box2dDebugRenderer;
	private BackgroundHandler backgroundHandler;

	private OrthographicCamera orthoCam, hudCam, box2dCam;
	private HudHandler hudStuff;

	private String id, name;
	private int mapWidth, mapHeight, tileSize;
	private ArrayList<TiledMapTileLayer> layerBuild;
	private ArrayList<SpriteHandler> spriteRenderList;
	private Player player;

	public GameMapLoader(String map, int[] mapTileLayers, RelativityGame rg, OrthographicCamera orthoCam) {

		this.orthoCam = orthoCam;
		this.rg = rg;
		world = new World(new Vector2(0, GRAVITY), true);
		world.setContactListener(ch = new CollisionHandler());
		bDef = new BodyDef();
		fDef = new FixtureDef();
		polyShape = new PolygonShape();
		spriteRenderList = new ArrayList<SpriteHandler>();
		layerBuild = new ArrayList<TiledMapTileLayer>();

		box2dDebugRenderer = new Box2DDebugRenderer();
		box2dDebugRenderer.setDrawContacts(true);
		box2dDebugRenderer.setDrawAABBs(true);
		tiledMap = new TmxMapLoader().load(map);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		MapProperties prop = tiledMap.getProperties();
		this.mapWidth = prop.get("width", Integer.class);
		this.mapHeight = prop.get("height", Integer.class);
		this.tileSize = prop.get("tileheight", Integer.class);

		loadMapTiles(mapTileLayers);

		
		orthoCam.setToOrtho(false, RelativityGame.WIDTH, RelativityGame.HEIGHT);

		/*
		 * bCam = new BoundedCamera(); bCam.setToOrtho(false, RelativityGame.WIDTH,
		 * RelativityGame.HEIGHT);
		 */

		box2dCam = new OrthographicCamera();
		box2dCam.setToOrtho(false, RelativityGame.WIDTH / PPM, RelativityGame.HEIGHT / PPM);

		
		hudStuff = new HudHandler(player);
		
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, RelativityGame.WIDTH, RelativityGame.HEIGHT);
		
		backgroundHandler = new BackgroundHandler(hudCam);
		
		
		//LOOOOOOOOP THIS
		makePlayer(150 /PPM, 600/PPM);
		
		makeOrb(200 / PPM, 250 / PPM);
		makeOrb(600 / PPM, 250 / PPM);

	}

	public void render(SpriteBatch batch) {
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

		try {
			box2dCam.position.set(player.getPosition().x, player.getPosition().y, 0);
			orthoCam.position.set(player.getPosition().x * PPM, player.getPosition().y * PPM, 0);
			orthoCam.update();
			box2dCam.update();

			
			batch.setProjectionMatrix(hudCam.combined);
			backgroundHandler.render(batch);
			
			tiledMapRenderer.setView(orthoCam);
			tiledMapRenderer.render();

			batch.setProjectionMatrix(orthoCam.combined);
			player.render(batch);
			for (SpriteHandler sprite : spriteRenderList) {
				sprite.render(batch);
			}
			

			box2dDebugRenderer.render(world, box2dCam.combined);
			
			batch.setProjectionMatrix(hudCam.combined);
			hudStuff.render(batch);
			
		} catch (Exception e) {
			e.printStackTrace();
			rg.setScreen(new HomeMenu(rg, orthoCam));
		}

	}

	public void update(float delta) {
		world.step(delta, 8, 2);
		player.update(delta);
		for (SpriteHandler sprite : spriteRenderList) {
			sprite.update(delta);
		}
		
	}

	public void dispose() {
		rg.dispose();
	}

	public int getWidth() {
		return mapWidth;
	}

	public int getHeight() {
		return mapHeight;
	}

	public int getLayers() {
		return tiledMap.getLayers().getCount();
	}

	private void makePlayer(float x, float y) {

		bDef.type = BodyType.DynamicBody;
		bDef.position.set(x, y);
		bDef.fixedRotation = false;
		bDef.linearVelocity.set(0f, 0f);

		// create body from bodydef
		body = world.createBody(bDef);

		// create box shape for player collision box
		PolygonShape polyShape = new PolygonShape();
		Vector2[] playerBounds = new Vector2[4];
		playerBounds[0] = new Vector2(4 / PPM, 45 / PPM);
		playerBounds[1] = new Vector2(50 / PPM, -35 / PPM);
		playerBounds[2] = new Vector2(-50 / PPM, -35 / PPM);
		playerBounds[3] = new Vector2(4 / PPM, 45 / PPM);

		polyShape.set(playerBounds);

		// create fixturedef for player collision box
		fDef.shape = polyShape;
		fDef.friction = 1f;
		fDef.filter.categoryBits = CollisionHandler.PLAYER;
		fDef.filter.maskBits = CollisionHandler.GROUND | CollisionHandler.ORB;

		// create player collision box fixture
		body.createFixture(fDef).setUserData("player");

		// create box shape for player foot
		polyShape.setAsBox(45 / PPM, 8 / PPM, new Vector2(0, -40 / PPM), 0);

		// create fixturedef for player foot
		fDef.shape = polyShape;
		fDef.isSensor = true;
		fDef.filter.categoryBits = CollisionHandler.PLAYER;
		fDef.filter.maskBits = CollisionHandler.GROUND | CollisionHandler.ORB;
		
		// create player foot fixture
		body.createFixture(fDef).setUserData("foot");
		// create new player
		player = new Player(body);
		body.setUserData(player);
	}

	private void makeOrb(float x, float y) {

		bDef.type = BodyType.KinematicBody;
		bDef.position.set(x, y);
		bDef.fixedRotation = true;
		bDef.linearVelocity.set(0f, 0f);

		body = world.createBody(bDef);

		polyShape.setAsBox(50 / PPM, 50 / PPM);
		fDef.shape = polyShape;
		fDef.friction = 1f;
		fDef.filter.categoryBits = CollisionHandler.ORB;
		fDef.filter.maskBits = CollisionHandler.PLAYER;

		body.createFixture(fDef).setUserData("orb");
		Orb orb = new Orb(body);
		body.setUserData(orb);
		
		spriteRenderList.add(orb);
		
		makeMissile(orb, x - 50/PPM, y);
	}
	
	private void makeMissile(SpriteHandler sprite, float x, float y) {
		
		bDef.type = BodyType.KinematicBody;
		bDef.position.set(x, y);
		bDef.fixedRotation = false;
		bDef.linearVelocity.set(-1f, 0f);
		
		body = world.createBody(bDef);
		
		polyShape.setAsBox(50 / PPM, 50/PPM);
		fDef.shape = polyShape;
		fDef.friction = 0;
		fDef.filter.categoryBits = CollisionHandler.ORB;
		fDef.filter.maskBits = CollisionHandler.PLAYER;
		
		body.createFixture(fDef).setUserData("missile");
		Missile missile = new Missile(body);
		body.setUserData(missile);

		spriteRenderList.add(missile);
	}
	
	

	private void loadMapTiles(int[] tileLayers) {
		layerBuild.clear();

		// PLEASE ADD IN A METHOD TO GET MULTIPLE TILEDMAPLAYERS
		for (int x : tileLayers) {
			layerBuild.add((TiledMapTileLayer) tiledMap.getLayers().get(x));
		}

		polyShape.setAsBox((tileSize / 2) / PPM, (tileSize / 2) / PPM);

		for (int i = 0; i < layerBuild.size(); i++) {

			TiledMapTileLayer layer = (TiledMapTileLayer) layerBuild.get(i);

			for (int row = 0; row < layer.getHeight(); row++) {
				for (int col = 0; col < layer.getWidth(); col++) {

					Cell cell = layer.getCell(col, row);
					if (cell != null && cell.getTile() != null) {
						bDef.type = BodyType.StaticBody;
						bDef.position.set((col + 0.5f) * tileSize / PPM, (row + 0.5f) * tileSize / PPM);
						ChainShape cs = new ChainShape();
						Vector2[] v = new Vector2[5];
						v[0] = new Vector2(-(tileSize / 2 / PPM), tileSize / 2 / PPM);
						v[1] = new Vector2((tileSize / 2) / PPM, tileSize / 2 / PPM);
						v[2] = new Vector2((tileSize / 2) / PPM, -(tileSize / 2) / PPM);
						v[3] = new Vector2(-(tileSize / 2 / PPM), -tileSize / 2 / PPM);
						v[4] = new Vector2(-(tileSize / 2 / PPM), tileSize / 2 / PPM);
						cs.createChain(v);

						fDef.shape = cs;
						fDef.friction = 0.8f;
						fDef.filter.maskBits = CollisionHandler.PLAYER;
						fDef.filter.categoryBits = CollisionHandler.GROUND;
						fDef.isSensor = false;
						world.createBody(bDef).createFixture(fDef).setUserData("tile");

					}
				}
			}
		}

	}
}
