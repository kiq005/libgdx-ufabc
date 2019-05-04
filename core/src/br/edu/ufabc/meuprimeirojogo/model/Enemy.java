package br.edu.ufabc.meuprimeirojogo.model;

import java.util.HashMap;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class Enemy extends AbstractCharacter {
	
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
	
	private static final float speed = 5f;
	
	public static HashMap<Integer, String> enemyMap;
	static {
		enemyMap = new HashMap<Integer, String>();
		enemyMap.put(MUTANT, "mutant");
		enemyMap.put(NIGHTSHADE, "nightshade");
		enemyMap.put(SKELETONZOMBIE, "skeletonzombie");
	}
	
	HashMap<String, String> enemyTypes = new HashMap<String, String>();
	
 	public Enemy(float visionBigRadius, float visionSmallRadius, float strength, float healthPoints, int enemyType, Hero hero) {
 		super(moveable, strength, healthPoints);
		state = IDLE;
		this.visionBigRadius = visionBigRadius;
		this.visionSmallRadius = visionSmallRadius;
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
 	
 	private void lookAtHero() {
 		
 		Vector3 enemyPosition = this.getPosition();
 		Vector3 heroPosition = hero.getPosition();
 		
 		float angle = (float)Math.toDegrees(Math.atan2( heroPosition.z - enemyPosition.z, heroPosition.x - enemyPosition.x));
 		float curAngle = (float)Math.toDegrees(this.getGameObject().transform.getRotation(new Quaternion()).getYaw());
 		
 		
 		this.setRotation( (angle - curAngle) );
 		
 	}

 	public void update(float delta) {
		float heroDistance = this.getHeroDistance();
		characters[state].update(delta);
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
				// lookAtHero();
				if (heroDistance > this.visionBigRadius) 
					idle();
				else if (heroDistance <= this.visionSmallRadius) 
					attack();			
				else {
					this.setPosition( getPosition().sub(hero.getPosition()).nor().scl(-speed) );
					
//					Vector3 enemyPosition = this.getPosition();
//					enemyPosition.interpolate(hero.getPosition(), 0.1f, Interpolation.linear);
//					this.setPosition(enemyPosition);
				}
				break;
			case ATTACKING:
				// lookAtHero();
				if (this.getGameObject().isAnimationFinished()) {
					if (heroDistance <= this.visionSmallRadius) {
						hero.applyDamage(this.getStrength());
						
					}
					this.getGameObject().resetAnimation();
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
		
	}
 	
	public float getHeroDistance() {
		return hero.getPosition().dst(this.getPosition());
	}

	
}