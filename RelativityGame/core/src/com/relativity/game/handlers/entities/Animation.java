package com.relativity.game.handlers.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Handles animated sprites using an array of TextureRegions.
 */
public class Animation {

	private TextureRegion[] allFrames;
	private float deltaTimeIncrement;
	private float switchDelay;
	private int currentFrame;

	private int timesPlayed;

	public Animation() {
	}

	public Animation(TextureRegion[] frames) {
		this(frames, 1 / 64f);
	}

	public Animation(TextureRegion[] frames, float delay) {
		this.allFrames = frames;
		this.switchDelay = delay;
		deltaTimeIncrement = 0;
		currentFrame = 0;
	}
	
	public void setCurrentFrame(int i) {
		if (i < allFrames.length)
			currentFrame = i;
	}

	public void setFrame(TextureRegion[] frames) {
		setFrame(frames, 1 / 64f);
	}

	public void setFrame(TextureRegion[] frames, float delay) {
		this.allFrames = frames;
		deltaTimeIncrement = 0;
		currentFrame = 0;
		timesPlayed = 0;
		this.switchDelay = delay;
	}

	public void update(float dt) {
		if (switchDelay <= 0) {
			return;
		} else {
			deltaTimeIncrement += dt;
			while (deltaTimeIncrement >= switchDelay) {
				increment();
			}
		}
	}

	private void increment() {
		deltaTimeIncrement -= switchDelay;
		currentFrame++;
		if (currentFrame == allFrames.length) {
			currentFrame = 0;
			timesPlayed++;
		}
	}

	public TextureRegion getFrame() {
		return allFrames[currentFrame];
	}

	public int getTimesPlayed() {
		return timesPlayed;
	}

	public boolean hasPlayedOnce() {
		return timesPlayed > 0;
	}

}
