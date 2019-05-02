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
	protected Hero hero;

	public DungeonAction() {
		DungeonMap map = new DungeonMap(50, 50, 1);
		hero = map.GetHero();
		
		objects = new Array<AbstractModel>();
		
//		hero = new Hero(30, 200);
//		hero.setPosition(40, 2.55f, 40);
//		
//		Enemy enemy = new Enemy(100, 3, 20, 100, Enemy.MUTANT, hero);
//		enemy.setPosition(20, 2.55f, 20);
//				
//		objects.add(hero);
//		objects.add(enemy);
//		
//		enemy.getGameObject().transform.scale(0.03f, 0.03f, 0.03f);
//		hero.getGameObject().transform.scale(0.03f, 0.03f, 0.03f);
		
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
			
		}
	}
	
}
