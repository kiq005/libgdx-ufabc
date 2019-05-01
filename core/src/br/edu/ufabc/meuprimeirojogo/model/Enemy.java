package br.edu.ufabc.meuprimeirojogo.model;

import java.util.HashMap;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class Enemy extends AbstractCharacter {
	
	public int angle;
	
	public static final int IDLE = 0;
	public static final int RUNNING = 1;
	public static final int ATTACKING = 2;
	public static final int DYING = 3;
	public static final int DEAD = 4;
	
	public static final int MUTANT = 0;
	public static final int NIGHTSHADE = 1;
	public static final int SKELETONZOMBIE = 2;
	
	private static boolean collidable = true;
	private static boolean moveable = true;
	private Hero hero;
	private float visionBigRadius;
	private float visionSmallRadius;
	
	public static HashMap<Integer, String> enemyMap;
	static {
		enemyMap = new HashMap<Integer, String>();
		enemyMap.put(MUTANT, "mutant");
		enemyMap.put(NIGHTSHADE, "nightshade");
		enemyMap.put(SKELETONZOMBIE, "skeletonzombie");
	}
	
	HashMap<String, String> enemyTypes = new HashMap<String, String>();
	
 	public Enemy(float visionBigRadius, float visionSmallRadius, float strength, float healthPoints, int enemyType, Hero hero) {
 		super(collidable, moveable, strength, healthPoints);
		state = IDLE;
		this.visionBigRadius = visionBigRadius;
		this.visionSmallRadius = visionSmallRadius;
		this.setStrength(strength);
		this.setHealthPoints(healthPoints);
		this.hero = hero;
		
		// just making sure that the user won't screw the game by providing an invalid enemy type
		if (enemyType < 0 || enemyType > 2) {
			enemyType = 0;
		}
		
		Model modelIdle = MeuJogo.modelManager.getModel(enemyMap.get(enemyType) + "Idle");
		Model modelWalk = MeuJogo.modelManager.getModel(enemyMap.get(enemyType) + "Running");
		Model modelShot = MeuJogo.modelManager.getModel(enemyMap.get(enemyType) + "Attacking");
		Model modelDying = MeuJogo.modelManager.getModel(enemyMap.get(enemyType) + "Dying");
		
		characters = new GameObject[4];
		characters[IDLE] = new GameObject(modelIdle, true, true, true, 1.0f);
		characters[RUNNING] = new GameObject(modelWalk, true, true, true, 1.0f);
		characters[ATTACKING] = new GameObject(modelShot, true, true, true, 1.0f);
		characters[DYING] = new GameObject(modelDying, true, true, false, 1.0f);
	}
 	
 	private void run() {
 		state = RUNNING;
 	}
 	
 	private void attack() {
		state = ATTACKING;
	}

 	private void idle() {
		state = IDLE;
	}
 	
 	private void die() {
		state = DYING;
	}
 	
 	private void dead() {
		state = DEAD;
	}

 	public void update(float delta) {
		float heroDistance = this.getHeroDistance();
		
		if (this.getHealthPoints() <= 0) {
			die();
		}
		else {
			switch (state) {
			case IDLE:
				if (heroDistance <= this.visionBigRadius) 
					run();
				break;
			case RUNNING:
				if (heroDistance > this.visionBigRadius) 
					idle();
				else if (heroDistance <= this.visionSmallRadius) 
					attack();			
				else {
					Vector3 enemyPosition = this.getPosition();
					enemyPosition.interpolate(hero.getPosition(), 0.1f, Interpolation.linear);
					this.getGameObject().transform.setTranslation(enemyPosition);
				}
				break;
			case ATTACKING:
				if (this.getGameObject().isAnimationFinished()) {
					if (heroDistance <= this.visionSmallRadius)
						hero.applyDamage(this.getStrength());
					idle();
				}
				break;
			case DYING:
				if (this.getGameObject().isAnimationFinished()) 
					dead();
				break;
			case DEAD:
				break;
			}
		}
		
		characters[state].update(delta);
	}
 	
	public float getHeroDistance() {
		return hero.getPosition().dst(this.getPosition());
	}
	
 	public void rotate(int angle) {
		this.angle += angle;
		for (int i = 0; i < 4; i++) {
			characters[i].transform.rotate(Vector3.Y, angle);
		}
	}

	
}