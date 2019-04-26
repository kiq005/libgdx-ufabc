package br.edu.ufabc.meuprimeirojogo.core;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.utils.Array;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.model.AbstractModel;
import br.edu.ufabc.meuprimeirojogo.model.DungeonMap;

public class DungeonAction {

	protected Array<AbstractModel> objects;

	public DungeonAction() {
		DungeonMap map = new DungeonMap(50, 50);
		
		objects = new Array<AbstractModel>();
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
