package br.edu.ufabc.meuprimeirojogo;


import java.io.File;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

import br.edu.ufabc.meuprimeirojogo.core.ModelManager;
import br.edu.ufabc.meuprimeirojogo.screen.Dungeon;
import br.edu.ufabc.meuprimeirojogo.screen.GameScreen;
import br.edu.ufabc.meuprimeirojogo.screen.MyScreen;
import br.edu.ufabc.meuprimeirojogo.screen.StartScreen;
import br.edu.ufabc.meuprimeirojogo.util.GamePad;
import br.edu.ufabc.meuprimeirojogo.util.InputHandler;
import br.edu.ufabc.meuprimeirojogo.util.Utilities;

public class MeuJogo extends Game {

	private MyScreen currentScreen;
	public static ModelManager modelManager;
	public static ModelBuilder modelBuider;
	public static boolean DEBUG = false;
	public static InputHandler inputHandler;

	@Override
	public void create() {
		inputHandler = new InputHandler();
		//Gdx.input.setInputProcessor(inputHandler);
		
		modelManager = new ModelManager();
		modelBuider  = new ModelBuilder();
		
		currentScreen = new StartScreen("LOADING", new String[] {"Cave", "Enemy", "Collectables"});
		setScreen(currentScreen);
	}

	public void render() {
		inputHandler.update();
		currentScreen.render(Gdx.graphics.getDeltaTime());
		if (currentScreen.isDone()) {
			// aqui eu cuido da transição das telas
			if (currentScreen.getId().equals("LOADING")) {
				currentScreen = new Dungeon("DUNGEON");
			} else {
				currentScreen = new StartScreen("LOADING", new String[] {"Cave", "Enemy", "Collectables"});
			}

		}
	}

}
