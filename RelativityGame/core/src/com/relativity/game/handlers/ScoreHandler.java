package com.relativity.game.handlers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

/**
 * 
 * This class uses the Score class to keep a flat file updated with a high game score and low game score
 * 
 * @version BPA SET 2018
 * @author Jacob Frank, Jerry Zeng, and Eddie Tang
 */

public class ScoreHandler {

	
	private static ArrayList<Score> allScores = new ArrayList<Score>();
	private static final int MAX_SCORE_AMOUNT = 5;
	private static Json json = new Json();

	public static ArrayList<Score> getScores() {
		
		FileHandle file = Gdx.files.local("save.data");
		Score[] allScoresTemp = json.fromJson(Score[].class, file.readString());
				
		for (int i = 0; i <= MAX_SCORE_AMOUNT; i++) {
			try {
				Score score = allScoresTemp[i];
				System.out.println(score.getHighScore());
				System.out.println(score.getName());
				System.out.println(score.getLowScore());
			} catch (Exception e) {
				break;
			}
		}
		
		for (Score score : allScoresTemp) {
			allScores.add(score);
		}
		
		return allScores;

	}

	public static void writeNewScores(ArrayList<Score> score) {

		FileHandle file = Gdx.files.local("save.data");
		allScores.addAll(score);
		
		
		if (allScores.size() > MAX_SCORE_AMOUNT) {
			while (allScores.size() > MAX_SCORE_AMOUNT) {
				allScores.remove(0);
			}
		}

		file.writeString(json.prettyPrint(allScores), false);
	}

}
