package com.relativity.game.configs.maps;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.relativity.game.RelativityGame;
import com.relativity.game.configs.GameMapLoader;
import com.relativity.game.configs.entities.InputHandler;
import com.relativity.game.configs.entities.types.Player;

public class LevelOne implements Screen {
	
	private RelativityGame rg;
	private static GameMapLoader gmLoader;

	public LevelOne(RelativityGame rg, OrthographicCamera orthoCam) {
		this.rg = rg;
		int[] levels = {0, 1, 2};
		gmLoader = new GameMapLoader("map.tmx", levels, rg, orthoCam);
	}
		

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		update(delta);
		gmLoader.render(rg.getBatch());
	}
	
	public void update(float delta) {
		handleInput();
		gmLoader.update(delta);
	}

	

	public void handleInput() {
		
		if(InputHandler.hasKeyPressed()) {
			
			if(InputHandler.getValueAt(Keys.SPACE)) {
				Player.jump(true);
			}
			
			if (InputHandler.getValueAt(Keys.A)) {
				Player.setFacingRight(false);
				Player.walk();
			} else if (InputHandler.getValueAt(Keys.D)) {
				Player.setFacingRight(true);
				Player.walk();
			}
			
		}
		else {
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