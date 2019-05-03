package br.edu.ufabc.meuprimeirojogo;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

import br.edu.ufabc.meuprimeirojogo.core.ModelManager;
import br.edu.ufabc.meuprimeirojogo.screen.Dungeon;
import br.edu.ufabc.meuprimeirojogo.screen.EndScreen;
import br.edu.ufabc.meuprimeirojogo.screen.MyScreen;
import br.edu.ufabc.meuprimeirojogo.screen.StartScreen;
import br.edu.ufabc.meuprimeirojogo.util.InputHandler;

public class MeuJogo extends Game {

	public static MyScreen currentScreen;
	public static ModelManager modelManager;
	public static ModelBuilder modelBuider;
	public static boolean DEBUG = false;
	public static InputHandler inputHandler;
	public static String[] assetsToLoad = new String[] {"Cave", "Enemy", "Collectables", "Hero"};

	@Override
	public void create() {
		inputHandler = new InputHandler();
		//Gdx.input.setInputProcessor(inputHandler);
		
		modelManager = new ModelManager();
		modelBuider  = new ModelBuilder();
		
		currentScreen = new StartScreen("LOADING", assetsToLoad);
		setScreen(currentScreen);
	}

	public void render() {
		inputHandler.update();
		currentScreen.render(Gdx.graphics.getDeltaTime());
		if (currentScreen.isDone()) {
			// aqui eu cuido da transição das telas
			if (currentScreen.getId().equals("LOADING")) {
				currentScreen = new Dungeon("DUNGEON");
			} else if (currentScreen.getId().equals("DUNGEON")) {
				//Trocar cenario
				currentScreen = new EndScreen("YOUDIED");
			}
			else {
				currentScreen = new StartScreen("LOADING");
			}

		}
	}

}
