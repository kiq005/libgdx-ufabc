package br.edu.ufabc.meuprimeirojogo.model;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;

import br.edu.ufabc.meuprimeirojogo.Commands;
import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class LixoAndante extends AbstractModel {

	private GameObject lixo;
	
	public int movimento;
	public static final int PARADO  = 0;
	public static final int FRENTE  = 1;
	public static final int TRAS    =-1;
	public static final int DIREITA = 2;
	public static final int ESQUERDA=-2;
	public float  step = 3;
	public float  angulo = 40;
	
	public LixoAndante() {
		super(true, true);
		lixo = new GameObject((Model)MeuJogo.assetManager.get("cenario/lixo.g3db"));
		
	}
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		if (Commands.set[Commands.UP]) {
			lixo.transform.translate(0,0,step*delta);
		}
		if (Commands.set[Commands.DOWN]) {
			lixo.transform.translate(0,0,-step*delta);
		}
		if (Commands.set[Commands.LEFT]) {
			lixo.transform.rotate(Vector3.Y, angulo*delta);
			lixo.setAngle(angulo*delta);
		}
		if (Commands.set[Commands.RIGHT]) {
			lixo.transform.rotate(Vector3.Y,-angulo*delta);
			lixo.setAngle(-angulo*delta);
		}
		lixo.update(delta);
		if (isMoveable())
    		lixo.updateBoundingBox();
		
	}
	
	public void stepBack(float delta) {
		if (Commands.set[Commands.UP]) {
			lixo.transform.translate(0,0,-step*delta);
		}
		if (Commands.set[Commands.DOWN]) {
			lixo.transform.translate(0,0,step*delta);
		}
		
		lixo.update(delta);
		if (isMoveable())
    		lixo.updateBoundingBox();
		
	}

	@Override
	public GameObject getGameObject() {
		// TODO Auto-generated method stub
		return lixo;
	}

}
