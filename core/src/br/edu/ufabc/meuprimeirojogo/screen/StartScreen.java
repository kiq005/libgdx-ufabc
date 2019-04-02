package br.edu.ufabc.meuprimeirojogo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.util.Utilities;

public class StartScreen extends MyScreen {

	private Texture texture;
	private SpriteBatch spriteBatch;
	private Matrix4 viewMatrix;
	private Matrix4 tranMatrix;
	private BitmapFont bitmapFont;
	private boolean loaded = false;
	private int progress = 0;
	private boolean visible = true;
	private float time = 0.0f;
	private String gameTitle = "Gears Of Bronx by Isidro";
	private float stringTime = 0.0f;
	private int stringIndex = 1;
	private boolean stringDone = false;
	private Music sounds;

	public StartScreen(String id) {
		super(id);
		texture = new Texture("start.jpg");
		spriteBatch = new SpriteBatch();
		viewMatrix = new Matrix4();
		tranMatrix = new Matrix4();
		bitmapFont = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		sounds = Gdx.audio.newMusic(Gdx.files.internal("sounds/loopshot2.mp3"));
		sounds.setLooping(true);
		//sounds.play();
		

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		texture.dispose();
		spriteBatch.dispose();

	}

	@Override
	public void update(float delta) {

		if (!MeuJogo.assetManager.isFinished()) {
			MeuJogo.assetManager.update();
			progress = (int) (MeuJogo.assetManager.getProgress() * 100);

		} else {
			loaded = true;
		}

		if (loaded && Gdx.input.justTouched()) {
			setDone(true); // terminei o que tinha pra fazer
		}
	}

	@Override
	public void draw(float delta) {
		// define que vou usar bits de cores para limpar a tela
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// define que vou usar a cor preta (0,0,0,0)
		Gdx.gl20.glClearColor(0, 0, 0, 0);
		viewMatrix.setToOrtho2D(0, 0, Utilities.GAME_WIDTH, Utilities.GAME_HEIGHT); // defino a "configuração da resolução"
		spriteBatch.setProjectionMatrix(viewMatrix); // buffer irá seguir essa configuração
		spriteBatch.setTransformMatrix(tranMatrix); // toda vez q redimensionar a tela, armazene as distorçoes na matriz

		spriteBatch.begin();
		spriteBatch.draw(texture, 0, 0, Utilities.GAME_WIDTH, Utilities.GAME_HEIGHT, 0, 0, 800, 600, false, false);
		/*
		 * Logica para escrever estilo typewriter
		 */
		bitmapFont.getData().setScale(1, 2);
		if (!stringDone) {
			bitmapFont.draw(spriteBatch, gameTitle.substring(0, stringIndex), 100, 370);
			stringTime += Gdx.graphics.getDeltaTime();
			if (stringTime >= 0.15f) {
				stringTime = 0;
				stringIndex++;
				if (stringIndex >= gameTitle.length()) {
					stringDone = true;
				}
			}
		} else {
			if (sounds.isPlaying())
				sounds.stop();
			bitmapFont.draw(spriteBatch, gameTitle, 100, 370);
		}

		// bitmapFont.draw(spriteBatch, gameTitle, 100, 370);

		bitmapFont.getData().setScale(1);
		if (!loaded)

			bitmapFont.draw(spriteBatch, "Loading... " + progress + "%", 50, 100);
		else {
			if (visible) {
				bitmapFont.draw(spriteBatch, "Touch to Start", 50, 100);
			}
			time += Gdx.graphics.getDeltaTime();
			if (time >= 0.2f) {
				visible = !visible;
				time = 0;
			}

		}

		spriteBatch.end();

	}

}
