package br.edu.ufabc.meuprimeirojogo.model;

import com.badlogic.gdx.math.Vector3;

import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public abstract class AbstractCollectable extends ObjetoColidivel {
	
	public int state;

	public GameObject collectables[];
	
	public static final int UNCOLLECTED = 0;
	public static final int COLLECTED   = 1;
	
	public AbstractCollectable(boolean moveable) {
		super(moveable);
	}
	
	@Override
	public GameObject getGameObject() {
		return collectables[state];
	}
	
 	public void setPosition(float x, float y, float z){
		for (int i = 0; i < collectables.length; i++) {
			collectables[i].transform.translate(x, y, z);
		}
	}
 	
 	public Vector3 getPosition() {
 		Vector3 collectablePosition = new Vector3();
 		this.getGameObject().transform.getTranslation(collectablePosition);
 		return collectablePosition;
 	}
 	
	public abstract void applyCollectableEffect();
	public abstract void update(float delta);
	
}
