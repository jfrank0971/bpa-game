package com.relativity.game.state.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.relativity.game.RelativityGame;
import com.relativity.game.state.GameMapLoader;

public class LevelOne implements Screen {
	
	private RelativityGame rg;
	GameMapLoader gmLoader;
	
	/*private World world;
	private Box2DDebugRenderer box2dRenderer;*/
	
	
	public LevelOne (RelativityGame rg) {
		
		this.rg = rg;
		gmLoader = new GameMapLoader();
		gmLoader.load("map.tmx", rg);
		
		/*box2dRenderer = new Box2DDebugRenderer();
		world = new World(new Vector2(0, 0), true);
		
		PolygonShape polyShape = new PolygonShape();
		FixtureDef fixtureDef = new FixtureDef();
		BodyDef bodyDef = new BodyDef();
		Body body = world.createBody(bodyDef);
		
		for (MapObject obj : tMap.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rectangle = ((RectangleMapObject) obj).getRectangle();
			
			bodyDef.type = BodyType.StaticBody;
			bodyDef.position.set(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);
			
			body = world.createBody(bodyDef);
			
			polyShape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
			fixtureDef.shape = polyShape;
			body.createFixture(fixtureDef);
		}*/
		
		
		
		
		/*box2dRenderer.SHAPE_STATIC.set(1,0,0,1);*/
		
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gmLoader.render(gmLoader.getCam(), rg.batch);
		
		
	}
	
	public void update(float dt) {
	
	}

	
	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		gmLoader.dispose();
		
	}

}