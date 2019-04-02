package br.edu.ufabc.meuprimeirojogo.model;

import com.badlogic.gdx.graphics.g3d.Model;

import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class ObjetoColidivel extends AbstractModel{
	private GameObject currentObject;
	public ObjetoColidivel(Model model) {
		super(true, false);
		currentObject = new GameObject(model);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameObject getGameObject() {
		// TODO Auto-generated method stub
		return currentObject;
	}

}
