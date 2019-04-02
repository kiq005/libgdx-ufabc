package br.edu.ufabc.meuprimeirojogo.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.math.Vector3;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;

public class Poste extends AbstractModel {
	public static final int OFF   = 0;
	public static final int ON    = 1;
	public static final int BLINK = 2;
	
	private GameObject modelPoste;
	private int        mode;
	private PointLight light;
	private float      intensity;
	
	public Poste(int mode) {
		super(true,false);
		this.mode = mode;
		modelPoste = new GameObject((Model)MeuJogo.assetManager.get("cenario/poste.g3db"));
		modelPoste.updateBoxScale(0.7f, 1, 0.7f);
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		modelPoste.update(delta);
		intensity+=delta;
		if (mode == BLINK) {
			light.setIntensity((float)Math.abs(Math.sin(intensity*5)*20));
		}
		
	}

	@Override
	public GameObject getGameObject() {
		// TODO Auto-generated method stub
		return modelPoste;
	}
	
	public void updateBox() {
		modelPoste.updateBoundingBox();
		
	}
	
	public int getMode() {
		return this.mode;
	}
	
	public PointLight posteLight() {
		return this.light;
	}
	
	public void updateLight(Vector3 position) {
		
		light = new PointLight().set(Color.WHITE, position.x, position.y+4f, position.z, (mode > 0)?0.3f:0);
	}
	

}
