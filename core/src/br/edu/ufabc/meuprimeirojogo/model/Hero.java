package br.edu.ufabc.meuprimeirojogo.model;

import com.badlogic.gdx.math.Vector3;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class Hero extends AbstractCharacter {

	private static boolean collidable = true;
	private static boolean moveable = true;
	
	public Hero(float strength, float healthPoints, Vector3 initialPosition) {
		super(collidable, moveable, strength, healthPoints, initialPosition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public GameObject getGameObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
