package br.edu.ufabc.meuprimeirojogo.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.math.Vector3;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class Hero extends AbstractCharacter {

	private static final float turn_speed = 10f;
	private static float move_speed = 25f;
	
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
		updateInput();
		characters[0].update(delta);
		light.setPosition(this.getPosition().add(3f, 13f, 3f));
	}
	
	public void updateInput() {
		float angle = getGameObject().getAngle();
		float r = MeuJogo.inputHandler.y_axis * move_speed;
		
		Vector3 dir = new Vector3();
		dir.x = (float) (r * Math.cos(Math.toRadians(angle + 90f)) );
		dir.z = (float) (r * Math.sin(Math.toRadians(angle + 90f)) );
		
		this.setPosition(dir);
		this.setRotation( MeuJogo.inputHandler.x_axis * turn_speed);
	}

	@Override
	public GameObject getGameObject() {
		return characters[0];
	}
	
	public PointLight GetLight() {
		return light;
	}

}
