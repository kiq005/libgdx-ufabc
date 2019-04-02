package br.edu.ufabc.meuprimeirojogo.screen;

import br.edu.ufabc.meuprimeirojogo.core.GameAction;
import br.edu.ufabc.meuprimeirojogo.core.Renderer;

public class GameScreen extends MyScreen {
	private GameAction gameAction;
	private Renderer renderer;

	public GameScreen(String id) {
		super(id);
		gameAction = new GameAction();
		renderer   = new Renderer(gameAction);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float delta) {
		gameAction.update(delta);

	}

	@Override
	public void draw(float delta) {
		// define que vou usar bits de cores para limpar a tela
        renderer.draw(delta);
	}

}
