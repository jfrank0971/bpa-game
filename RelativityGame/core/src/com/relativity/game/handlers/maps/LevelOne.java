package com.relativity.game.handlers.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.relativity.game.RelativityGame;
import com.relativity.game.handlers.GameMapLoader;
import com.relativity.game.handlers.InputHandler;
import com.relativity.game.handlers.entities.types.Player;

/**
 * Creates a GameMapLoader to load the game objects, as well as loads up sounds and other assets
 * 
 * @version BPA SET 2018
 * @author Jacob Frank, Jerry Zeng, and Eddie Tang
 */

public class LevelOne implements Screen {

	private RelativityGame rg;
	private static GameMapLoader gmLoader;
	private static Music song;
	private static Sound hitSound;

	public LevelOne(RelativityGame rg, OrthographicCamera orthoCam) {
		this.rg = rg;
		int[] levels = { 0, 1 };
		gmLoader = new GameMapLoader("map.tmx", levels, rg, orthoCam);

		song = Gdx.audio.newMusic(Gdx.files.internal("level1.mp3"));
		song.setVolume(0.5f);
		song.setLooping(true);
		song.play();

		hitSound = Gdx.audio.newSound(Gdx.files.internal("damage.mp3"));

	}

	@Override
	public void show() {

	}

	// Because this class is the current screen, this method needs to be called in
	// order to get back to the RelativityGame (Game) render method
	@Override
	public void render(float delta) {
		update(delta);
		gmLoader.render(rg.getBatch());
	}

	public void update(float delta) {
		handleInput();
		gmLoader.update(delta);
	}

	public static void hit() {
		hitSound.play(0.5f);
	}

	public void handleInput() {

		if (InputHandler.hasKeyPressed()) {

			if (InputHandler.getValueAt(Keys.SPACE)) {
				Player.jump();
			}

			if (InputHandler.getValueAt(Keys.A)) {
				Player.setFacingRight(false);
				Player.walk();
			} else if (InputHandler.getValueAt(Keys.D)) {
				Player.setFacingRight(true);
				Player.walk();
			}

		} else {
		}

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