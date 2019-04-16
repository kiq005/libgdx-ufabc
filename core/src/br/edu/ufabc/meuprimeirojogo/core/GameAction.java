package br.edu.ufabc.meuprimeirojogo.core;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import br.edu.ufabc.meuprimeirojogo.Commands;
import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.model.AbstractModel;
import br.edu.ufabc.meuprimeirojogo.model.ObjetoColidivel;
import br.edu.ufabc.meuprimeirojogo.util.Utilities;

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
