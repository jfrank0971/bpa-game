package com.relativity.game.state.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.relativity.game.RelativityGame;

public class HomeMenu implements Screen {

	final RelativityGame rg;
	final HomeMenu hm;
	private Texture exitButtonOn, exitButtonOff;
	private Texture enterButtonOn; //enterButtonOff;
	private int exitButtonX, exitButtonY, enterButtonX, enterButtonY;
	private OrthographicCamera camera;

	public HomeMenu(final RelativityGame rg) {

		this.rg = rg;
		hm = this;
		exitButtonOn = new Texture("exit.png");
		exitButtonOff = new Texture("exit.png");
		enterButtonOn = new Texture("enter.png");
		//enterButtonOff = new Texture("enter.png");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, RelativityGame.WIDTH, RelativityGame.HEIGHT);
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClearColor(1, 1, 1, 1);

		exitButtonX = RelativityGame.MWIDTH - (exitButtonOn.getWidth() / 2);
		exitButtonY = (RelativityGame.MHEIGHT - (exitButtonOn.getHeight() / 2)) / 3;
		enterButtonX = RelativityGame.MWIDTH - (enterButtonOn.getWidth() / 2);
		enterButtonY = (RelativityGame.MHEIGHT - (enterButtonOn.getHeight() / 2));

		
		rg.batch.begin();

		rg.batch.draw(exitButtonOff, exitButtonX, exitButtonY);
		rg.batch.draw(enterButtonOn, enterButtonX, enterButtonY);

		rg.batch.end();
		
		if (Gdx.input.justTouched()) {
			Vector3 pos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			if (pos.x < exitButtonX + exitButtonOn.getWidth() && pos.x > exitButtonX
					&& pos.y < RelativityGame.HEIGHT - exitButtonY
					&& pos.y > RelativityGame.HEIGHT - (exitButtonOn.getHeight() + exitButtonY)) {
				Gdx.app.exit();
			}

			if (pos.x < enterButtonX + enterButtonOn.getWidth() && pos.x > enterButtonX
					&& pos.y < RelativityGame.HEIGHT - enterButtonY
					&& pos.y > RelativityGame.HEIGHT - (enterButtonOn.getHeight() + enterButtonY)) {
				rg.setScreen(new LevelOne(rg));

			}
		}
		camera.update();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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
		hm.dispose();
	}

}
