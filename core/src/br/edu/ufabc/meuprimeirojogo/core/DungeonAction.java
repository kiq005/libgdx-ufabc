package br.edu.ufabc.meuprimeirojogo.core;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import br.edu.ufabc.meuprimeirojogo.model.AbstractModel;
import br.edu.ufabc.meuprimeirojogo.model.DungeonMap;
import br.edu.ufabc.meuprimeirojogo.model.Enemy;
import br.edu.ufabc.meuprimeirojogo.model.Hero;

public class DungeonAction {

	protected Array<AbstractModel> objects;

	public DungeonAction() {
		DungeonMap map = new DungeonMap(50, 50);
		
		objects = new Array<AbstractModel>();
		
		Hero hero = new Hero(100, 3, 220, 500, Hero.HERO);
		hero.setPosition(35, 2.55f, 40);
//		
//		Enemy enemy = new Enemy(100, 3, 80, 750, Enemy.MUTANT, hero);
//		enemy.setPosition(20, 2.55f, 20);
//				
		objects.add(hero);
//		objects.add(enemy);
//		
//		enemy.getGameObject().transform.scale(0.03f, 0.03f, 0.03f);
		hero.getGameObject().transform.scale(0.03f, 0.03f, 0.03f);
		
		map.AddToObjectList(objects);
		
		for (AbstractModel obj : objects) {
			for (Material mat : obj.getGameObject().materials) {
				mat.remove(ColorAttribute.Emissive);
			}
		}
	}

	public void update(float delta) {
		for (AbstractModel o : objects) {
			o.update(delta);
		}

		for (AbstractModel m : objects) {
			m.update(delta);
		}
	}
	
}
