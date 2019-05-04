package br.edu.ufabc.meuprimeirojogo.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class Hero extends AbstractCharacter {

	private static float turn_speed = 100f;
	private static float move_speed = 250f;
	
	private Enemy enemy;
	private int enemyCounter = 0;

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
	
	private PointLight light;
	
	public Hero(float strength, float healthPoints) 
	{
 		super(moveable, strength, healthPoints);
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
		characters[WALKING] = new GameObject(modelWalk, true, true, true, -1.0f);
		characters[DYING] = new GameObject(modelDeath, true, true, false, 1.0f);
		characters[SLASH_ATTACKING] = new GameObject(modelSAttack, true, true, true, 1.0f);
		characters[ATTACKING] = new GameObject(modelNAttack, true, true, true, 1.0f);
		characters[RUNNING] = new GameObject(modelRunning, true, true, true, 1.0f);
		characters[BLOCKING] = new GameObject(modelBlock, true, true, true, 1.0f);
		
		light = new PointLight().set(Color.WHITE, this.getPosition(), 50f);
	}

	@Override
	public void update(float delta) {
		updateInput(delta);
		updateState();
		characters[state].update(delta);
		light.setPosition(this.getPosition().add(3f, 13f, 3f));
	}
	
	public void updateInput(float delta) {
		float angle = getGameObject().getAngle();
		float r = MeuJogo.inputHandler.y_axis * move_speed;
		
		Vector3 dir = new Vector3();
		dir.x = (float) (r * Math.cos(Math.toRadians(angle + 90f)) );
		dir.z = (float) (r * Math.sin(Math.toRadians(angle + 90f)) );
		
		if(state == WALKING || state == RUNNING) {
			this.setPosition(dir.scl(delta));
			this.setRotation( MeuJogo.inputHandler.x_axis * turn_speed * delta);
		}
	}
	
	public void updateState() {
		if (this.getHealthPoints() <= 0)
			die();
		
		if (state == DYING) {
			if(characters[state].isAnimationFinished())
				MeuJogo.currentScreen.setDone(true);
		}
		else if(state == ATTACKING || state == SLASH_ATTACKING) {
			if(characters[state].isAnimationFinished()) {
				characters[state].resetAnimation();
				if(this.enemy != null) {
					this.enemy.applyDamage(this.getStrength());
				}
				idle();
			}
		}
		else if (state == BLOCKING) {
			if(characters[state].isAnimationFinished() && !MeuJogo.inputHandler.deffend) {
				characters[state].resetAnimation();
				idle();
			}
		}
		else {
			if(MeuJogo.inputHandler.attack)
				slash_attack();
			else if(MeuJogo.inputHandler.deffend)
				block();
			else if(Math.abs(MeuJogo.inputHandler.y_axis) > 0 || Math.abs(MeuJogo.inputHandler.x_axis) > 0) {
				if (MeuJogo.inputHandler.run)
					run();
				else
					walk();
			}
			else
				idle();
		}
	}
	
	private void block() {
 		state = BLOCKING;
 	}
	
	private void walk() {
		move_speed = 250f;
		turn_speed = 150f;
 		state = WALKING;
 	}
 	
	private void attack() {
		state = ATTACKING;
	}
	
	private void run() {
		move_speed = 500f;
		turn_speed = 200f;
 		state = RUNNING;
	}

 	private void idle() {
 		move_speed = 0f;
 		turn_speed = 200f;
		state = IDLE;
	}
 	
 	private void die() {
		state = DYING;
	}
 	
 	private void slash_attack() {
		state = SLASH_ATTACKING;
	}

	@Override
	public GameObject getGameObject() {
		return characters[state];
	}
	
	public PointLight GetLight() {
		return light;
	}
	
	public void SetEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	
	public Enemy GetEnemy() {
		return this.enemy;
	}
	
	public void SetCounter(int n) {
		enemyCounter = n;
	}
	
	public int GetCounter() {
		return enemyCounter;
	}
	
	@Override
	public void applyDamage(float damageAmount) {
		if(state != BLOCKING)
			this.healthPoints -= damageAmount; 
		if (this.healthPoints <= 0) this.healthPoints = 0;
		if (this.healthPoints >= this.getMaxHealthPoints()) this.healthPoints = this.getMaxHealthPoints();
	}
	
}
