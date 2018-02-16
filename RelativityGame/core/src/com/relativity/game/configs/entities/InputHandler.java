package com.relativity.game.configs.entities;

import java.util.HashMap;
import java.util.Set;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class InputHandler extends InputAdapter {

	private static HashMap<Integer, Boolean> keyMap;

	static {
		keyMap = new HashMap<Integer, Boolean>();
		keyMap.put(Keys.SPACE, false);
		keyMap.put(Keys.A, false);
		keyMap.put(Keys.D, false);
		keyMap.put(Keys.SPACE, false);
	}

	public boolean keyDown(int key) {
		if (keyMap.containsKey(key)) {
			keyMap.replace(key, false, true);
			return true;
		}
		return false;
		
	}
	
	public boolean keyUp(int key) {
		if (keyMap.containsKey(key)) {
			keyMap.replace(key, true, false);
			return true;
		}
		return false;
	}
	
	public static boolean hasKeyPressed() {
		if(keyMap.containsValue(true)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean getValueAt(int key) {
		return keyMap.get(key);
	}

}
