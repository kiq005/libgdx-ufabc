package br.edu.ufabc.meuprimeirojogo.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.math.Vector3;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class Hero extends AbstractCharacter {

	private static boolean collidable = true;
	private static boolean moveable = true;
	
	private PointLight light;
	
	public Hero(float strength, float healthPoints) {
		super(collidable, moveable, strength, healthPoints);
		Model modelIdle = MeuJogo.modelManager.getModel("nightshadeIdle");
		characters = new GameObject[1];
		characters[0] = new GameObject(modelIdle);
		
		light = new PointLight().set(Color.WHITE, this.getPosition(), 50f);
	}

	@Override
	public void update(float delta) {
		// Input
		updateInput();
		// 
		characters[0].update(delta);
		light.setPosition(this.getPosition().add(3f, 13f, 3f));
	}
	
	public void updateInput() {
		float angle = getGameObject().getAngle();
		
		
		Vector3 dir = new Vector3();
		
		
	}

	@Override
	public GameObject getGameObject() {
		return characters[0];
	}
	
	public PointLight GetLight() {
		return light;
	}

}
