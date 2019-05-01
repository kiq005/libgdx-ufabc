package br.edu.ufabc.meuprimeirojogo.model;

import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.UBJsonReader;
import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;


public class Enemy extends AbstractModel {
	public int state;
	public int angle;
	public static final int IDLE = 0;
	public static final int RUNNING = 1;
	public static final int PUNCHING = 2;
	public static final int DYING = 3;
	public static final int DEAD = 4;
	private GameObject enemies[];
	private static boolean collidable = true;
	private static boolean moveable = true;
	private AbstractModel hero;
	private float damageDealt = 20.0f;
	
 	public Enemy() {
 		super(collidable, moveable);
		state = IDLE;
		this.setHealthPoints(100.0f);
		ModelLoader<ModelLoader.ModelParameters> modelLoader = new G3dModelLoader(new UBJsonReader());
		Model mdlIdle = MeuJogo.modelManager.getModel("idle");
		Model mdlWalk = MeuJogo.modelManager.getModel("running");
		Model mdlShot = MeuJogo.modelManager.getModel("punching");
		Model mdlDying = MeuJogo.modelManager.getModel("dying");

 		enemies = new GameObject[4];
		enemies[IDLE] = new GameObject(mdlIdle, true, true, true, 1.0f);
		enemies[RUNNING] = new GameObject(mdlWalk, true, true, true, 1.0f);
		enemies[PUNCHING] = new GameObject(mdlShot, true, true, true, 1.0f);
		enemies[DYING] = new GameObject(mdlDying, true, true, false, 1.0f);
	}

 	public void setInitialPosition(float x, float y, float z){
		for (int i = 0; i < enemies.length; i++) {
			enemies[i].transform.translate(x, y, z);
		}
	}
 	
 	private void run() {
 		state = RUNNING;
 	}
 	
 	private void punch() {
		state = PUNCHING;
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
 	 

 	public void rotate(int angle) {
		this.angle += angle;
		for (int i = 0; i < 4; i++) {
			enemies[i].transform.rotate(Vector3.Y, angle);
		}
	}

 	public void update(float delta) {
		enemies[state].update(delta);
		float heroDistance = this.getHeroDistance();
		float bigRadius = 100.0f;
		float smallRadius = 100.0f;
		
		if (this.getHealthPoints() <= 0) {
			die();
		}
		else {
			switch (state) {
			case IDLE:
				if (heroDistance <= bigRadius) 
					run();
				break;
			case RUNNING:
				if (heroDistance > bigRadius) 
					idle();
				else if (heroDistance <= smallRadius) 
					punch();			
				else {
					Vector3 heroPosition = new Vector3();
					Vector3 enemyPosition = new Vector3();
					
					hero.getGameObject().transform.getTranslation(heroPosition);
					this.getGameObject().transform.getTranslation(enemyPosition);
					
					enemyPosition.interpolate(heroPosition, 0.1f, Interpolation.linear);
					this.getGameObject().transform.setTranslation(enemyPosition);
				}
				break;
			case PUNCHING:
				if (this.getGameObject().isAnimationFinished()) {
					if (heroDistance <= smallRadius)
						hero.applyDamage(this.damageDealt);
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

	@Override
	public GameObject getGameObject() {
		return enemies[state];
	}

	public float getHeroDistance() {
		Vector3 heroPosition = new Vector3();
		Vector3 enemyPosition = new Vector3();
		
		hero.getGameObject().transform.getTranslation(heroPosition);
		this.getGameObject().transform.getTranslation(enemyPosition);
		
		return heroPosition.dst(enemyPosition);
	}
	
 }