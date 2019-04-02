package br.edu.ufabc.meuprimeirojogo.model;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class Robot extends AbstractModel {
	private GameObject estados[];
	public static final int IDLE = 0;
	public static final int RUN = 1;
	public static final int SHOT = 2;
	public static final int DIE = 3;
	private int estado;
	

	public Robot() {
		super(true, true);
		estado = IDLE;
		estados = new GameObject[4];
		estados[IDLE] = new GameObject((Model) MeuJogo.assetManager.get("robot/Robot_idle.g3db"), true, true, true, 1);
		estados[RUN] = new GameObject((Model) MeuJogo.assetManager.get("robot/Robot_run.g3db"), true, true, true, 1);
		estados[SHOT] = new GameObject((Model) MeuJogo.assetManager.get("robot/Robot_OneShoot.g3db"), true, true, true,
				1);
		estados[DIE] = new GameObject((Model) MeuJogo.assetManager.get("robot/Robot_death.g3db"), true, true, false,
				0.5f);
		for (GameObject o : estados) {
			o.transform.scale(2, 2, 2);
		}
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		if (estado == SHOT && estados[estado].isAnimationFinished()) {
			estados[estado].resetAnimation();
			estado = IDLE;
		}

		estados[estado].update(delta);
		if (isMoveable()) {
			for (GameObject o : estados) {
				o.updateBoundingBox();
			}
		}
	}

	public void noAction() {
		if (estado != SHOT)
		   estado = IDLE;
	}

	public void step(float delta) {
		if (estado != SHOT) {
			estado = RUN;
		}
		for (GameObject o : estados) {
			o.transform.translate(0, 0, 1.5f * delta);

		}
	}

	public void stepBack(float delta) {
		if (estado != SHOT) {
			estado = RUN;
		}
		for (GameObject o : estados) {
			o.transform.translate(0, 0, -1.5f * delta);
		}
	}

	public void rotateLeft(float delta) {
		if (estado != SHOT) {
			estado = RUN;
		}
		for (GameObject o : estados) {
			o.transform.rotate(Vector3.Y, 30 * delta);
			o.setAngle(30 * delta);
		}
	}

	public void rotateRight(float delta) {
		if (estado != SHOT) {
			estado = RUN;
		}
		for (GameObject o : estados) {
			o.transform.rotate(Vector3.Y, -30 * delta);
			o.setAngle(-30 * delta);
		}
	}

	public void shoot() {
		estado = SHOT;
	}

	public void die() {
		estado = DIE;
	}

	public boolean isDead() {
		return (estado == DIE && estados[estado].isAnimationFinished());
	}

	@Override
	public GameObject getGameObject() {
		// TODO Auto-generated method stub
		return estados[estado];
	}

}
