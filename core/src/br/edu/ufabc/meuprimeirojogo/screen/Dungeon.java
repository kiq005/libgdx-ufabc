package br.edu.ufabc.meuprimeirojogo.screen;

import br.edu.ufabc.meuprimeirojogo.core.DungeonAction;
import br.edu.ufabc.meuprimeirojogo.core.DungeonRenderer;

public class Dungeon extends MyScreen {
	
	DungeonAction action;
	DungeonRenderer renderer;

	public Dungeon(String id) {
		super(id);
		action = new DungeonAction();
		renderer = new DungeonRenderer(action);
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void update(float delta) {
		action.update(delta);
	}

	@Override
	public void draw(float delta) {
		renderer.draw(delta);
	}

}
