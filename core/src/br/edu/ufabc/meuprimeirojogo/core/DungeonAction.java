package br.edu.ufabc.meuprimeirojogo.core;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.utils.Array;
import br.edu.ufabc.meuprimeirojogo.model.AbstractModel;
import br.edu.ufabc.meuprimeirojogo.model.DungeonMap;
import br.edu.ufabc.meuprimeirojogo.model.Enemy;

public class DungeonAction {

	protected Array<AbstractModel> objects;

	public DungeonAction() {
		DungeonMap map = new DungeonMap(50, 50);
		
		objects = new Array<AbstractModel>();
		
		Enemy enemy = new Enemy();
		objects.add(enemy);
		enemy.setInitialPosition(20, 2.55f, 20);
		//enemy.getGameObject().transform.setToTranslation(-50, 0, -50);
		enemy.getGameObject().transform.scale(0.03f, 0.03f, 0.03f);
		
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
