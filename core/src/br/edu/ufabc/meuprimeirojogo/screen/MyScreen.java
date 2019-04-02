package br.edu.ufabc.meuprimeirojogo.screen;

import com.badlogic.gdx.Screen;

public abstract class MyScreen implements Screen{

	private String id;
	private boolean done;  // ela terminou sua funcao?
	
	// toda tela tem um ID
	public MyScreen(String id) {
		this.id   = id;
		this.done = false;
	}
	
	
	public boolean isDone() {
		return done;
	}


	public void setDone(boolean done) {
		this.done = done;
	}


	public String getId() {
		return id;
	}


	// toda tela terá que implementar estes 2 métodos  
	public abstract void update(float delta);
	public abstract void draw(float delta);
	
	@Override
	public void show() {
		//do nothing
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		update(delta);
		draw(delta);
		
	}

	@Override
	public void resize(int width, int height) {
		// do  nothing
		
	}

	@Override
	public void pause() {
		// do nothing
		
	}

	@Override
	public void resume() {
		// do nothing
		
	}

	@Override
	public void hide() {
		// do nothing
		
	}

	

}
