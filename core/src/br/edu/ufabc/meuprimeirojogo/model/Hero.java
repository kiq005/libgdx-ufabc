package br.edu.ufabc.meuprimeirojogo.model;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class Hero extends AbstractCharacter {

	public int angle;
	
	private Enemy enemy;

	public static final int IDLE = 0;
	public static final int WALKING = 1;
	public static final int ATTACKING = 2;
	public static final int SLASH_ATTACKING = 3;
	public static final int DYING = 4;
	public static final int RUNNING = 5;
	public static final int BLOCKING = 6;
	 
	public static final int HERO = 0;
	
	private static boolean collidable = true;
	private static boolean moveable = true;
	

	
	public Hero(float strength, float healthPoints) 
	{
 		super(collidable, moveable, strength, healthPoints);
		state = IDLE;	
		
		Model modelIdle = MeuJogo.modelManager.getModel("heroIdle");
		Model modelWalk = MeuJogo.modelManager.getModel("heroWalk");
		Model modelNAttack = MeuJogo.modelManager.getModel("heroNAttack");
		Model modelSAttack = MeuJogo.modelManager.getModel("heroSAttack");
		Model modelDeath = MeuJogo.modelManager.getModel("heroDeath");
		Model modelRunning = MeuJogo.modelManager.getModel("heroRunning");
		Model modelBlock = MeuJogo.modelManager.getModel("heroBlock");

		characters = new GameObject[7];
		characters[IDLE] = new GameObject(modelIdle, true, true, true, 1.0f);
		characters[WALKING] = new GameObject(modelWalk, true, true, true, 1.0f);
		characters[DYING] = new GameObject(modelDeath, true, true, true, 1.0f);
		characters[SLASH_ATTACKING] = new GameObject(modelSAttack, true, true, true, 1.0f);
		characters[ATTACKING] = new GameObject(modelNAttack, true, true, false, 1.0f);
		characters[RUNNING] = new GameObject(modelRunning, true, true, false, 1.0f);
		characters[BLOCKING] = new GameObject(modelBlock, true, true, false, 1.0f);
		
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
		characters[state].update(delta);
	}

	

}
