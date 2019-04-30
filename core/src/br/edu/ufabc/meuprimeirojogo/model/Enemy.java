package br.edu.ufabc.meuprimeirojogo.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.UBJsonReader;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;


public class Enemy {
	public int state;
	public int angle;
	public static final int IDLE = 0;
	public static final int WALK = 1;
	public static final int SHOT = 2;
	public static final int DYING = 3;
	public static final int DEAD = 4;
	private GameObject enemies[];

 	public Enemy(){
		state = IDLE;
		ModelLoader<ModelLoader.ModelParameters> modelLoader = new G3dModelLoader(new UBJsonReader());
		Model mdlIdle = modelLoader.loadModel(Gdx.files.internal("enemy/Idle.g3db"));
		Model mdlWalk = modelLoader.loadModel(Gdx.files.internal("enemy/Running.g3db"));
		Model mdlShot = modelLoader.loadModel(Gdx.files.internal("enemy/Punching.g3db"));
		Model mdlDying = modelLoader.loadModel(Gdx.files.internal("enemy/Dying.g3db"));

 		enemies = new GameObject[4];
		enemies[IDLE] = new GameObject(mdlIdle);
		enemies[WALK] = new GameObject(mdlWalk);
		enemies[SHOT] = new GameObject(mdlShot);
		enemies[DYING] = new GameObject(mdlDying);
	}

 	public void setInitialPosition(float x, float y, float z){
		for (int i = 0; i < enemies.length; i++){
			enemies[i].transform.translate(x, y, z);
		}
	}

 	public void moveFront() {
		state = WALK;
		for (int i = 0; i < 4; i++) {
			enemies[i].transform.translate(0, 0, 0.05f);
		}
	}

 	public void moveBack() {
		state = WALK;
		for (int i = 0; i < 4; i++) {
			enemies[i].transform.translate(0, 0, -0.05f);
		}
	}

 	public void moveLeft() {
		state = WALK;
		for (int i = 0; i < 4; i++) {
			enemies[i].transform.translate(-0.5f, 0, 0);
		}
	}

 	public void moveRight() {
		state = WALK;
		for (int i = 0; i < 4; i++) {
			enemies[i].transform.translate(0.5f, 0, 0);
		}
	}

 	public void rotate(int angle) {
		this.angle += angle;
		for (int i = 0; i < 4; i++) {
			enemies[i].transform.rotate(Vector3.Y, angle);
		}
	}

 	public void shot() {
		state = SHOT;
	}

 	public void idle() {
		state = IDLE;
	}

 	public GameObject getCurrent() {
		return enemies[state];
	}

 	public void update(float delta) {
		enemies[state].update(delta);
	}

 }