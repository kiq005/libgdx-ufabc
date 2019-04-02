package br.edu.ufabc.meuprimeirojogo.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Matrix4;

import br.edu.ufabc.meuprimeirojogo.Commands;
import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.model.AbstractModel;
import br.edu.ufabc.meuprimeirojogo.model.LixoAndante;
import br.edu.ufabc.meuprimeirojogo.model.Poste;
import br.edu.ufabc.meuprimeirojogo.util.Button;
import br.edu.ufabc.meuprimeirojogo.util.ChasingCamera;

public class Renderer {

	private GameAction gameAction;
	private Environment environment;
	private ModelBatch modelBatch;
//	private PerspectiveCamera camera;
	private ChasingCamera camera;
	private CameraInputController input;
	private SpriteBatch spritebatch;
	private BitmapFont bmp;
	private Matrix4 viewMatrix;
	private Matrix4 tranMatrix;

	public Renderer(GameAction action) {
		this.gameAction = action;
		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.3f, 0.3f, 0.3f, 0));

		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -0.2f, -0.8f, 1));
		for (AbstractModel m : action.objects) {
			if (m instanceof Poste) {
				environment.add(((Poste) m).posteLight());
			}
		}
		camera = new ChasingCamera(67.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1.5f, -2.5f);
		camera.far = 300f;
		// considerando que o lixo está na posição 0

//		camera.position.set(0, 5,5);
//		camera.lookAt(0,5,10);
		camera.update();
		// input = new CameraInputController(camera);
		// Gdx.input.setInputProcessor(input);
		spritebatch = new SpriteBatch();
		bmp = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		bmp.getData().setScale(0.5f);
		viewMatrix = new Matrix4();
		tranMatrix = new Matrix4();
		camera.setObjectToFollow(gameAction.robot.getGameObject());
	}

	public void draw(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		viewMatrix.setToOrtho2D(0, 0, 800, 600);
		spritebatch.setProjectionMatrix(viewMatrix);
		spritebatch.setTransformMatrix(tranMatrix);

		modelBatch.begin(camera);
		for (AbstractModel o : gameAction.objects) {
			if (o.getGameObject().isVisible())
				modelBatch.render(o.getGameObject(), environment);
		}
		if (Commands.set[Commands.DEBUG]) {
			for (AbstractModel o : gameAction.objects) {
				modelBatch.render(o.getGameObject().getBoxInstance(), environment);
			}
		}
		modelBatch.end();
		camera.update();
		spritebatch.begin();
		bmp.draw(spritebatch, gameAction.strMsg, 50, 450);

		if (MeuJogo.gamePad.enabled)
			for (Button button : MeuJogo.gamePad.getButtons()) {
				spritebatch.draw(button.getTexture(), button.getPosX(), button.getPosY());
			}
		spritebatch.end();

//		if (Commands.set[Commands.SHOT]) {
//			camera.setOffsetZ(-2);
//			camera.setOffsetY(1);
//		} else {
//			camera.setOffsetZ(-7);
//			camera.setOffsetY(2);
//		}

	}
}
