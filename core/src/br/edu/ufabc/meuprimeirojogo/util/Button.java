package br.edu.ufabc.meuprimeirojogo.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Button {
	private float posX;
	private float posY;
	private Texture texture;
	
	public Button(String filename){
		texture = new Texture(Gdx.files.internal(filename));
	}
	public Button(String filename, float x, float y) {
		this(filename);
		this.posX = x;
		this.posY = y;
	}
	
	public boolean isTouched(float x, float y) {
	    return (x >= posX && x <= posX + texture.getWidth() &&
	    		y >= posY && y <= posY + texture.getHeight());	
	}
	
	public boolean isTouched(Vector3 pos) {
		return (pos.x >= posX && pos.x <= posX + texture.getWidth() &&
				pos.y >= posY && pos.y <= posY + texture.getHeight());
	}
	public float getPosX() {
		return posX;
	}
	public void setPosX(float posX) {
		this.posX = posX;
	}
	public float getPosY() {
		return posY;
	}
	public void setPosY(float posY) {
		this.posY = posY;
	}
	public Texture getTexture() {
		return texture;
	}
	
	
	
	
	
	
	
	

}
