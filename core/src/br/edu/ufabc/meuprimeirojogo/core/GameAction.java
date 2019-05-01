package br.edu.ufabc.meuprimeirojogo.core;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.utils.Array;
import br.edu.ufabc.meuprimeirojogo.model.AbstractModel;


public class GameAction {

	protected Array<AbstractModel> objects;

	public GameAction() {
		objects = new Array<AbstractModel>();
		int mode = 0;

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
