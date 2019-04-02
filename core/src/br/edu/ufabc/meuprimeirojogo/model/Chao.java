package br.edu.ufabc.meuprimeirojogo.model;

import com.badlogic.gdx.graphics.g3d.Model;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class Chao extends AbstractModel {
	private GameObject objectChao;
	public Chao() {
		super(true, false);
		objectChao = new GameObject((Model)MeuJogo.assetManager.get("cenario/cenario.g3db"));
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
	}

	@Override
	public GameObject getGameObject() {
		// TODO Auto-generated method stub
		return objectChao;
	}

}
