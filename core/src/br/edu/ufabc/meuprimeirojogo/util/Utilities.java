package br.edu.ufabc.meuprimeirojogo.util;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

public class Utilities {
	public static final int GAME_WIDTH  = 1024;
	public static final int GAME_HEIGHT = 768;
	public static final Random random = new Random();
	
	public static Vector3 convertCoordinates(float x, float y) {
		Vector3 pos = new Vector3();
		pos.x = ((x / (float)Gdx.graphics.getWidth())*GAME_WIDTH);
		pos.y = GAME_HEIGHT - ((y / (float)Gdx.graphics.getHeight())*GAME_HEIGHT);
		
		return pos;
	}
	
	public static float getAngle(Vector3 pos1, Vector3 pos2) {
		return (float)Math.toDegrees(Math.atan((pos1.x-pos2.x)/(pos1.z-pos2.z)));
	}

	public static int getRandomNumberInRange(int min, int max) {
		return random.nextInt((max - min) + 1) + min;
	}
}
