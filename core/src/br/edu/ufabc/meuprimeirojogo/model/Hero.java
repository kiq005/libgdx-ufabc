package br.edu.ufabc.meuprimeirojogo.model;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class Hero extends AbstractCharacter {

public int angle;
	
	public static final int BLOCKING = 0;
	public static final int WALKING = 1;
	public static final int ATTACKING = 2;
	public static final int SLASH_ATTACKING = 3;
	public static final int DYING = 4;
	public static final int RUNNING = 5;
	public static final int IDLE = 6;
	 
	public static final int HERO = 0;
	
	private float visionBigRadius;
	private float visionSmallRadius;
	
	private static boolean collidable = true;
	private static boolean moveable = true;
	
	public static HashMap<Integer, String> enemyMap;
	static {
		enemyMap = new HashMap<Integer, String>();
		enemyMap.put(HERO, "hero");
	}
	
	HashMap<String, String> heroTypes = new HashMap<String, String>();
	
	public Hero(float visionBigRadius, float visionSmallRadius, float strength, float healthPoints, int enemyType) 
	{
 		super(collidable, moveable, strength, healthPoints);
		state = IDLE;
		this.visionBigRadius = visionBigRadius;
		this.visionSmallRadius = visionSmallRadius;
		this.setStrength(strength);
		this.setHealthPoints(healthPoints);
		
		
		
		
		Model modelIdle = MeuJogo.modelManager.getModel(enemyMap.get(enemyType) + "Idle");
		Model modelWalk = MeuJogo.modelManager.getModel(enemyMap.get(enemyType) + "Running");
		//Model modelRun = MeuJogo.modelManager.getModel(enemyMap.get(enemyType) + "Running");
		Model modelShot = MeuJogo.modelManager.getModel(enemyMap.get(enemyType) + "Attacking");
		//Model modelShot2 = MeuJogo.modelManager.getModel(enemyMap.get(enemyType) + "Attacking");
		Model modelDying = MeuJogo.modelManager.getModel(enemyMap.get(enemyType) + "Dying");
		//Model modelBlocking = MeuJogo.modelManager.getModel(enemyMap.get(enemyType) + "Blocking");
		
		characters = new GameObject[7];
		characters[IDLE] = new GameObject(modelIdle, true, true, true, 1.0f);
		characters[RUNNING] = new GameObject(modelWalk, true, true, true, 1.0f);
		characters[DYING] = new GameObject(modelWalk, true, true, true, 1.0f);
		characters[SLASH_ATTACKING] = new GameObject(modelShot, true, true, true, 1.0f);
		characters[ATTACKING] = new GameObject(modelDying, true, true, false, 1.0f);
		characters[WALKING] = new GameObject(modelDying, true, true, false, 1.0f);
		characters[BLOCKING] = new GameObject(modelDying, true, true, false, 1.0f);
	}
	
	private void block() {
 		state = BLOCKING;
 	}
	
	private void walk() {
 		state = WALKING;
 	}
 	
	private void attack() {
	state = ATTACKING;
	}
	
	private void run() {
 		state = RUNNING;
	}

 	private void idle() {
		state = IDLE;
	}
 	
 	private void die() {
		state = DYING;
	}
 	
 	private void slash_attack() {
		state = SLASH_ATTACKING;
	}

 	public void rotate(int angle) {
		this.angle += angle;
		for (int i = 0; i < 4; i++) {
			characters[i].transform.rotate(Vector3.Y, angle);
		}
	}


	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public GameObject getGameObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
