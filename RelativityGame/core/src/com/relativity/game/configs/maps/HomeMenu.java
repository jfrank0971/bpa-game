package com.relativity.game.configs.maps;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.relativity.game.RelativityGame;

public class HomeMenu implements Screen {

	private final RelativityGame rg;
	private OrthographicCamera txtCam;
	private HashMap<String, Texture> txList;
	int startButtonY, levelSelectButtonY, exitButtonY, buttonX;
	private final float TEXTURE_SCALE = 0.5f;
	private int texWidth, texHeight;

	public HomeMenu(RelativityGame rg, OrthographicCamera txtCam) {

		this.txtCam = txtCam;
		this.rg = rg;
		/*
		 * [3] = Exit Off [0] = Exit On
		 * 
		 * [2] = Start On [5] = Start Off
		 * 
		 * 
		 */

		txList = new HashMap<>();

		txList.put("StartOn", new Texture(Gdx.files.internal("StartOn.png")));
		txList.put("StartOff", new Texture(Gdx.files.internal("StartOff.png")));
		txList.put("LevelSelectOn", new Texture(Gdx.files.internal("LevelSelectOn.png")));
		txList.put("LevelSelectOff", new Texture(Gdx.files.internal("LevelSelectOff.png")));
		txList.put("ExitOn", new Texture(Gdx.files.internal("ExitOn.png")));
		txList.put("ExitOff", new Texture(Gdx.files.internal("ExitOff.png")));
		txList.put("Background", new Texture("TitleScreen.png"));

		buttonX = (RelativityGame.WIDTH - (int) (txList.get("StartOn").getWidth() * TEXTURE_SCALE)) / 2;
		startButtonY = RelativityGame.HEIGHT / 3;
		levelSelectButtonY = startButtonY - (int) (txList.get("LevelSelectOff").getHeight() * TEXTURE_SCALE) - 20;
		exitButtonY = levelSelectButtonY - (int) (txList.get("ExitOff").getHeight() * TEXTURE_SCALE) - 20;

		texWidth = (int) (txList.get("StartOn").getWidth() * TEXTURE_SCALE);
		texHeight = (int) (txList.get("StartOn").getHeight() * TEXTURE_SCALE);
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
		Vector3 pos = txtCam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

		rg.getBatch().begin();
		rg.getBatch().draw(txList.get("Background"), 0, 0, RelativityGame.WIDTH, RelativityGame.HEIGHT);
		rg.getBatch().end();

		if (findButtonContact(pos, buttonX, startButtonY)) {

			System.out.println("h");
			drawButton("StartOn", buttonX, startButtonY, texWidth, texHeight);
			drawButton("LevelSelectOff", buttonX, levelSelectButtonY, texWidth, texHeight);
			drawButton("ExitOff", buttonX, exitButtonY, texWidth, texHeight);

		} else if (findButtonContact(pos, buttonX, levelSelectButtonY)) {

			drawButton("StartOff", buttonX, startButtonY, texWidth, texHeight);
			drawButton("LevelSelectOn", buttonX, levelSelectButtonY, texWidth, texHeight);
			drawButton("ExitOff", buttonX, exitButtonY, texWidth, texHeight);

		} else if (findButtonContact(pos, buttonX, exitButtonY)) {

			drawButton("StartOff", buttonX, startButtonY, texWidth, texHeight);
			drawButton("LevelSelectOff", buttonX, levelSelectButtonY, texWidth, texHeight);
			drawButton("ExitOn", buttonX, exitButtonY, texWidth, texHeight);

		} else {

			drawButton("StartOff", buttonX, startButtonY, texWidth, texHeight);
			drawButton("LevelSelectOff", buttonX, levelSelectButtonY, texWidth, texHeight);
			drawButton("ExitOff", buttonX, exitButtonY, texWidth, texHeight);
		}

		if (Gdx.input.isTouched()) {
			if (findButtonContact(pos, buttonX, startButtonY)) {
				rg.setScreen(new LevelOne(rg, txtCam));
			} else if (findButtonContact(pos, buttonX, levelSelectButtonY)) {

			} else if (findButtonContact(pos, buttonX, exitButtonY)) {
				Gdx.app.exit();
			}
		}
		txtCam.update();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	private void drawButton(String texName, int buttonX, int buttonY, int texWidth, int texHeight) {
		rg.getBatch().begin();
		rg.getBatch().draw(txList.get(texName), buttonX, buttonY, texWidth, texHeight);
		rg.getBatch().end();
	}

	private boolean findButtonContact(Vector3 pos, int buttonX, int buttonY) {

		if (pos.x < (buttonX + texWidth) && pos.x > buttonX && pos.y < (texHeight + buttonY) && pos.y > (buttonY)) {
			return true;
		} else {
			return false;
		}
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

	}

}
