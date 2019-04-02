package br.edu.ufabc.meuprimeirojogo.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class GamePad {
	
	private Button[] buttons;
	public static final int UP    =  0;
	public static final int DOWN  =  1;
	public static final int LEFT  =  2;
	public static final int RIGHT =  3;
	public static final int NONE  = -1;
	public boolean enabled;
	
	public GamePad(boolean enabled) {
		this.enabled = enabled;
		buttons = new Button[4];
		buttons[UP] = new Button("controls/up.png", 500 ,170);
		buttons[DOWN] = new Button("controls/down.png",500, 80);
		buttons[LEFT] = new Button("controls/left.png",420, 120);
		buttons[RIGHT] = new Button("controls/right.png", 580, 120);
	}
	
	public int buttonTouched(float x, float y) {
		if (buttons[UP].isTouched(x, y))
			return UP;
		else if (buttons[DOWN].isTouched(x, y))
			return DOWN;
		else if (buttons[LEFT].isTouched(x, y))
			return LEFT;
		else if (buttons[RIGHT].isTouched(x, y))
			return RIGHT;
		else return NONE;
	}
	
	public int buttonTouched(Vector3 pos) {
		if (buttons[UP].isTouched(pos))
			return UP;
		else if (buttons[DOWN].isTouched(pos))
			return DOWN;
		else if (buttons[LEFT].isTouched(pos))
			return LEFT;
		else if (buttons[RIGHT].isTouched(pos))
			return RIGHT;
		else return NONE;
	}
	
	public Button[] getButtons() {
		return this.buttons;
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	
	

}
