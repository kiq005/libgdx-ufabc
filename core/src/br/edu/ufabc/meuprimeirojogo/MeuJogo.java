package br.edu.ufabc.meuprimeirojogo;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSystem;
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
	public static ParticleSystem particleSystem;
	public static String[] assetsToLoad = new String[] {"Castle", "Enemy", "Collectables", "Hero"};
	private Music sounds;
	
	@Override
	public void create() {
		inputHandler = new InputHandler();
		particleSystem = new ParticleSystem();
		modelManager = new ModelManager();
		modelBuider  = new ModelBuilder();
		
		currentScreen = new StartScreen("LOADING", assetsToLoad);
		setScreen(currentScreen);
		
		sounds = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Game_sound.mp3"));
		sounds.setLooping(true);
		
	}

	public void render() {
		inputHandler.update();
		currentScreen.render(Gdx.graphics.getDeltaTime());
		particleSystem.update();
		
		if (currentScreen.isDone()) {
			// aqui eu cuido da transição das telas
			if (currentScreen.getId().equals("LOADING")) {
				sounds.play();
				currentScreen = new Dungeon("DUNGEON");
			} else if (currentScreen.getId().equals("DUNGEON")) {
				//Trocar cenario
				currentScreen = new EndScreen("YOUDIED");
				sounds.stop();
			}
			else {
				currentScreen = new StartScreen("LOADING");
			}

		}
	}

}
