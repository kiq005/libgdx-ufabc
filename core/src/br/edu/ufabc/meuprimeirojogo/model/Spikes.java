package br.edu.ufabc.meuprimeirojogo.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class Spikes extends AbstractCollectable {
	
	private static boolean moveable = false;

	private Hero hero;
	private float damage;
	
	public Spikes(float damage, Hero hero) {
		super(moveable);
		state = UNCOLLECTED;
		this.setHealthToRestore(damage);
		Model modelUncollectedPotion = MeuJogo.modelManager.getModel("inactiveSpike");
		Model modelCollectedPotion = MeuJogo.modelManager.getModel("activeSpike");

		collectables = new GameObject[2];
		collectables[UNCOLLECTED] = new GameObject(modelUncollectedPotion, true, false, false, 0.0f);
		collectables[COLLECTED] = new GameObject(modelCollectedPotion, true, false, false, 0.0f);
		
		this.hero = hero;
	}
	
 	private void collect() {
 		//state = COLLECTED;
 		for(Material m : collectables[UNCOLLECTED].materials) {
			m.set(ColorAttribute.createDiffuse(Color.GRAY));
		}
 	}
	
	public float getDamage() {
		return damage;
	}
	
	public void setHealthToRestore(float healthToRestore) {
		this.damage = healthToRestore;
	}

	@Override
	public void applyCollectableEffect() {
		this.hero.applyDamage(damage);
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
