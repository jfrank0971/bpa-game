package com.relativity.game.handlers;

/**
 * 
 * This class is needed for the ScoreHandler json file loading
 * 
 * @version BPA SET 2018
 * @author Jacob Frank, Jerry Zeng, and Eddie Tang
 */

public class Score {

	private String name;
	private int highScore;
	private int lowScore;

	public Score() {
		
	}
	
	public Score(String name, int highScore, int lowScore) {
		this.name = name;
		this.highScore = highScore;
		this.lowScore = lowScore;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	public int getLowScore() {
		return lowScore;
	}

	public void setLowScore(int lowScore) {
		this.lowScore = lowScore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
