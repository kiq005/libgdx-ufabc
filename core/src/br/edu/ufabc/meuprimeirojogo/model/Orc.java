package br.edu.ufabc.meuprimeirojogo.model;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class Orc extends AbstractModel {

	private GameObject estados[];
	public static final int IDLE = 0;
	public static final int RUN = 1;
	public static final int SHOT = 2;
	public static final int DIE = 3;
	public boolean  enableShot = false;
	private int estado;
	private float angleDestination;
	private float time=0;

	public Orc() {
		super(true, true);
		estado = IDLE;
		estados = new GameObject[4];
		estados[IDLE] = new GameObject((Model) MeuJogo.assetManager.get("orc/Orc_idle.g3db"), true, true, true, 1);
		estados[RUN] = new GameObject((Model) MeuJogo.assetManager.get("orc/Orc_run.g3db"), true, true, true, 1);
		estados[SHOT] = new GameObject((Model) MeuJogo.assetManager.get("orc/Orc_shoot.g3db"), true, true, true, 1);
		estados[DIE] = new GameObject((Model) MeuJogo.assetManager.get("orc/Orc_Death.g3db"), true, true, false, 1);

		for (GameObject o : estados) {
			o.transform.scale(2, 2, 2);
		}
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
//		for (GameObject o : estados) {
//			o.update(delta);
//		}
		estados[estado].update(delta);
		
		if (estado == IDLE) {
			time -= delta;
			if (time < 0) time = 0;
		}
		if (estado == RUN) {
			for (GameObject o : estados) {
				o.transform.rotate(Vector3.Y, 30 * delta);
				o.setAngle(30 * delta);
			}
			if (getGameObject().getAngle() >= angleDestination - 1
					&& getGameObject().getAngle() <= angleDestination + 1) {
				estado = SHOT;
				enableShot = true;
				
				setAngleDestination(0);
			}
			
		}
		else if (estado == SHOT) {
			
			if (estados[SHOT].isAnimationFinished()) {
				estados[SHOT].resetAnimation();
				estado = IDLE;
				time=2;
				
			}
		}

	}

	public void rotateToRobot(float angle) {
		if (estado == IDLE && time == 0) {
			estado = RUN;
			setAngleDestination(angle);
		}
	}
	
	public void goIdle() {
		estado = IDLE;
	}

	public int getEstado() {
		return this.estado;
	}
	
	public void setToPosition(Vector3 position) {
		for (GameObject o : estados) {
			o.transform.translate(position);
		}
	}

	public void setAngleDestination(float angle) {
		this.angleDestination = angle;
	}
	public float getAngleDestination() {
		return angleDestination;
	}

	@Override
	public GameObject getGameObject() {
		// TODO Auto-generated method stub
		return estados[estado];
	}

	public boolean isEnableShot() {
		return enableShot;
	}

	public void setEnableShot(boolean enableShot) {
		this.enableShot = enableShot;
	}
	

}
