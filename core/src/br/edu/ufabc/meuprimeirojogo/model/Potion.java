package br.edu.ufabc.meuprimeirojogo.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class Potion extends AbstractCollectable {
	
	private static boolean collidable = true;
	private static boolean moveable = false;

	private Hero hero;
	private float healthToRestore;
	
	public Potion(float healthToRestore, Hero hero) {
		super(collidable, moveable);
		state = UNCOLLECTED;
		this.setHealthToRestore(healthToRestore);
		Model modelUncollectedPotion = MeuJogo.modelManager.getModel("uncollectedPotion");
		Model modelCollectedPotion = MeuJogo.modelManager.getModel("collectedPotion");

		collectables = new GameObject[2];
		collectables[UNCOLLECTED] = new GameObject(modelUncollectedPotion, true, false, false, 0.0f);
		collectables[COLLECTED] = new GameObject(modelCollectedPotion, true, false, false, 0.0f);
	}
	
 	private void collect() {
 		//state = COLLECTED;
 		for(Material m : collectables[UNCOLLECTED].materials) {
			m.set(ColorAttribute.createDiffuse(Color.GRAY));
		}
 	}
	
	public float getHealthToRestore() {
		return healthToRestore;
	}
	
	public void setHealthToRestore(float healthToRestore) {
		this.healthToRestore = healthToRestore;
	}

	@Override
	public void applyCollectableEffect() {
		this.hero.applyDamage(-healthToRestore);
	}

	@Override
	public void update(float delta) {
		float heroDistance = this.getHeroDistance();
		
		if (this.state == UNCOLLECTED && heroDistance < 2) {
			applyCollectableEffect();
			collect();
		}
		collectables[state].update(delta);
	}
	
	public float getHeroDistance() {
		return hero.getPosition().dst(this.getPosition());
	}

}
