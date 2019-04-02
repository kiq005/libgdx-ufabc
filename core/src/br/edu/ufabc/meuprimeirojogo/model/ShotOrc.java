package br.edu.ufabc.meuprimeirojogo.model;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class ShotOrc extends AbstractModel{

	private GameObject shot;
	private float      speed = 700.5f;
	public ShotOrc(Vector3 position){
		super(true,true);
		shot = new GameObject((Model)MeuJogo.assetManager.get("shot_orc/shot.g3db"));
		position.y = 1f;
		shot.transform.translate(position);
		shot.transform.scale(0.01f, 0.01f, 0.01f);
		
	}
	
	public void rotateToAngle(float angle) {
		shot.transform.rotate(Vector3.Y, angle);
	}
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		shot.transform.translate(0, 0, speed*delta);
	}

	@Override
	public GameObject getGameObject() {
		// TODO Auto-generated method stub
		return shot;
	}

}
