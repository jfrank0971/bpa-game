package com.relativity.game.handlers;

import java.util.HashMap;
import java.util.Set;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;

/**
 * <p>
 * Manages the key bindings. Easily expandable by adding new keyMap entries
 * </p>
 * 
 * @version BPA SET 2018
 * @author Jacob Frank, Jerry Zeng, and Eddie Tang
 */

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
		if (keyMap.containsValue(true)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean getValueAt(int key) {
		return keyMap.get(key);
	}

	public static boolean findButtonContact(Vector3 pos, int buttonX, int buttonY, int texWidth, int texHeight) {

		if (pos.x < (buttonX + texWidth) && pos.x > buttonX && pos.y < (texHeight + buttonY) && pos.y > (buttonY)) {
			return true;
		} else {
			return false;
		}
	}

}
