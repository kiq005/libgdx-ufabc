package br.edu.ufabc.meuprimeirojogo.model;

import com.badlogic.gdx.math.Vector3;

import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public abstract class AbstractCharacter extends AbstractModel {

	public int state;

	public GameObject characters[];
	
	private float strength = 20;
	protected float healthPoints = 100;
	private float maxHealthPoints = 100;
	
	private Vector3 lastMovement;
	
	public AbstractCharacter(boolean collidable, boolean moveable) {
		super(collidable, moveable);
	}
		
	public AbstractCharacter(boolean collidable, boolean moveable, float customStrength, float customHealthPoints) {
		super(collidable, moveable);
		this.strength = customStrength;
		this.healthPoints = customHealthPoints;
		this.maxHealthPoints = customHealthPoints;
		this.lastMovement = new Vector3();
	}
	
	public float getHealthPoints() {
		return healthPoints;
	}
	
	public float getMaxHealthPoints() {
		return maxHealthPoints;
	}
	
	public void setHealthPoints(float healthPoints) {
		this.healthPoints = healthPoints;
	}
	
	public float getStrength() {
		return strength;
	}
	
	public void setStrength(float strength) {
		this.strength = strength;
	}
	

	public void applyDamage(float damageAmount) {
		this.healthPoints -= damageAmount; 
	}
	
	public void setPosition(Vector3 pos) {
		for (int i = 0; i < characters.length; i++) {
			characters[i].transform.translate(pos);
		}
	}
	
	public void revertMovement() {
		for (int i = 0; i < characters.length; i++) {
			characters[i].transform.translate(lastMovement.scl(-1));
		}
		lastMovement = new Vector3(0,0,0);
	}
	
 	public void setPosition(float x, float y, float z){
 		for (int i = 0; i < characters.length; i++) {
			characters[i].transform.translate(x, y, z);
		}
 		lastMovement = new Vector3(x, y, z);
	}
 	
 	public void setRotation(float angle) {
 		for (int i = 0; i < characters.length; i++) {
			characters[i].transform.rotate(Vector3.Y, angle);
		}
 	}
 	
 	public void setScale(float value) {
 		for (int i = 0; i < characters.length; i++) {
			characters[i].transform.scale(value, value, value);
		}
 	}
 	
 	public Vector3 getPosition() {
 		return this.getGameObject().transform.getTranslation(new Vector3());
 	}
	
	@Override
	public GameObject getGameObject() {
		return characters[state];
	}
 	
	public abstract void update(float delta);

}
