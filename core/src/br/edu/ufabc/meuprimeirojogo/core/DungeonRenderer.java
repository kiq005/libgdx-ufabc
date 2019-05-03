package br.edu.ufabc.meuprimeirojogo.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Matrix4;

import br.edu.ufabc.meuprimeirojogo.Commands;
import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.model.AbstractModel;
import br.edu.ufabc.meuprimeirojogo.util.Button;
import br.edu.ufabc.meuprimeirojogo.util.ChasingCamera;
import br.edu.ufabc.meuprimeirojogo.util.Utilities;

public class DungeonRenderer {
	private DungeonAction gameAction;
	private Environment environment;
	private ModelBatch modelBatch;
//	private PerspectiveCamera camera;
	private ChasingCamera camera;
	private CameraInputController input;
	private SpriteBatch spritebatch;
	private Matrix4 viewMatrix;
	private Matrix4 tranMatrix;
	
	private HUD hud;

	public DungeonRenderer(DungeonAction action) {
		this.gameAction = action;
		
		hud = new HUD(action);
		
		modelBatch = new ModelBatch();
		environment = new Environment();
		
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0f, 0f, 0f, 0));
		environment.add(gameAction.hero.GetLight());
		environment.add(new DirectionalLight().set(0.1f, 0.1f, 0.1f, -0.2f, -0.8f, 1));
		//environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -0.2f, -0.8f, 1));
		
		camera = new ChasingCamera(67.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 25f, 20f, 0f);
		camera.far = 1000f;
		camera.position.set(50, 50, 50);
		camera.lookAt(200, 5, 200);
		camera.update();
		
		//input = new CameraInputController(camera);
		//Gdx.input.setInputProcessor(input);
		
		spritebatch = new SpriteBatch();
		viewMatrix = new Matrix4();
		tranMatrix = new Matrix4();
	}

	public void draw(float delta) {
		camera.setObjectToFollow(gameAction.hero.getGameObject());
		
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		viewMatrix.setToOrtho2D(0, 0, Utilities.GAME_WIDTH, Utilities.GAME_HEIGHT);
		spritebatch.setProjectionMatrix(viewMatrix);
		spritebatch.setTransformMatrix(tranMatrix);

		modelBatch.begin(camera);
		for (AbstractModel o : gameAction.objects) {
			if (o.getGameObject().isVisible())
				modelBatch.render(o.getGameObject(), environment);
		}
		/*if (Commands.set[Commands.DEBUG]) {
			for (AbstractModel o : gameAction.objects) {
				modelBatch.render(o.getGameObject().getBoxInstance(), environment);
			}
		}*/
		modelBatch.end();
		camera.update();
		
		spritebatch.begin();
		hud.draw(spritebatch);
		spritebatch.end();
	}
}
