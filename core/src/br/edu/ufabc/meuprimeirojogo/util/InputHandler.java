package br.edu.ufabc.meuprimeirojogo.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;

public class InputHandler {
	
	public float x_axis;
	public float y_axis;
	public Boolean attack;
	public Boolean deffend;
	public Boolean run;
	
	public InputHandler() {
		// TODO: Get controllers
	}

	public void update() {
		x_axis = 0f;
		y_axis = 0f;
		attack = false;
		deffend = false;
		run = false;
		
		// TODO: Handler gamepad input
		if ( Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP))
			y_axis = 1f;
		if ( Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN) )
			y_axis = -1f;
		if ( Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT) )
			x_axis = 1f;
		if ( Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT) )
			x_axis = -1f;
		if ( Gdx.input.isButtonPressed(Buttons.LEFT) || Gdx.input.isKeyPressed(Keys.SPACE) )
			attack = true;
		if ( Gdx.input.isButtonPressed(Buttons.RIGHT) || Gdx.input.isKeyPressed(Keys.F) )
			deffend = true;
		if ( Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT) )
			run = true;
	}
	
}
