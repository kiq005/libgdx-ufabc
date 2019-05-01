package br.edu.ufabc.meuprimeirojogo.model;

import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class Hero extends AbstractCharacter {

	private static boolean collidable = true;
	private static boolean moveable = true;
	
	public Hero(float strength, float healthPoints) {
		super(collidable, moveable, strength, healthPoints);
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
