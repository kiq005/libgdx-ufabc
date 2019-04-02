package br.edu.ufabc.meuprimeirojogo.core;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import br.edu.ufabc.meuprimeirojogo.Commands;
import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.model.AbstractModel;
import br.edu.ufabc.meuprimeirojogo.model.Chao;
import br.edu.ufabc.meuprimeirojogo.model.ObjetoColidivel;
import br.edu.ufabc.meuprimeirojogo.model.Orc;
import br.edu.ufabc.meuprimeirojogo.model.Poste;
import br.edu.ufabc.meuprimeirojogo.model.Robot;
import br.edu.ufabc.meuprimeirojogo.model.ShotOrc;
import br.edu.ufabc.meuprimeirojogo.util.Utilities;

public class GameAction {

	protected Array<AbstractModel> objects;
	protected Robot robot;
	protected Orc orc;
	protected String strMsg;

	public GameAction() {
		objects = new Array<AbstractModel>();
		// vou carregar os modelos aqui (inicialmente)
//		

		objects.add(new Chao());
		int mode = 0;

		// para fins de debug
		GameObject cenario = objects.get(0).getGameObject();
		Vector3 position = new Vector3();
		ObjetoColidivel object;
		for (Node n : cenario.nodes) {

			position = n.globalTransform.getTranslation(position);
			if (n.id.contains("poste")) {
				Poste poste = new Poste(mode);
				mode = (mode + 1) % 3;

				poste.getGameObject().transform.setToTranslation(position);
				poste.getGameObject().updateBoundingBox();
				poste.updateLight(position);
				objects.add(poste);
			}

			else if (n.id.contains("lixo")) {

				object = new ObjetoColidivel((Model) MeuJogo.assetManager.get("cenario/lixo.g3db"));
				object.getGameObject().transform.setToTranslation(position);
				object.getGameObject().updateBoundingBox();
				objects.add(object);
			} else if (n.id.contains("lixeira")) {
				object = new ObjetoColidivel((Model) MeuJogo.assetManager.get("cenario/lixeira.g3db"));
				object.getGameObject().transform.setToTranslation(position);
				object.getGameObject().updateBoundingBox();
				objects.add(object);

			} else if (n.id.contains("banco")) {
				object = new ObjetoColidivel((Model) MeuJogo.assetManager.get("cenario/banco.g3db"));
				object.getGameObject().transform.setToTranslation(position);
				object.getGameObject().updateBoundingBox();
				objects.add(object);
			}
		}

//		
		orc = new Orc();
		orc.setToPosition(new Vector3(-5, 0, -5));
		robot = new Robot();
		objects.add(robot);
		objects.add(orc);

		for (AbstractModel obj : objects) {
			for (Material mat : obj.getGameObject().materials) {
				mat.remove(ColorAttribute.Emissive);
			}
		}

	}

	public void update(float delta) {
		for (AbstractModel o : objects) {
			o.update(delta);
		}

		if (Commands.noCommand()) {
			robot.noAction();
		}
		if (Commands.set[Commands.UP]) {
			robot.step(delta);
		}
		if (Commands.set[Commands.LEFT]) {
			robot.rotateLeft(delta);
		}
		if (Commands.set[Commands.RIGHT]) {
			robot.rotateRight(delta);
		}
		if (Commands.set[Commands.DOWN]) {
			robot.stepBack(delta);
		}
		if (Commands.set[Commands.SHOT]) {
			robot.shoot();
		}

		Vector3 posRobo = new Vector3();
		Vector3 posOrc = new Vector3();

		robot.getGameObject().transform.getTranslation(posRobo);
		orc.getGameObject().transform.getTranslation(posOrc);
		float dst = posRobo.dst(posOrc);
		float angle = orc.getGameObject().getAngle();
		float dstAngle = Utilities.getAngle(posRobo, posOrc);
		strMsg = " Dist = " + String.format("%.2f", dst) + " ORC = " + String.format("%.2f", angle) + " ANG = "
				+ String.format("%.2f", dstAngle);
		if (dst <= 10.0f) {
			
			orc.rotateToRobot(dstAngle);
			if (orc.isEnableShot()) {
				ShotOrc shot = new ShotOrc(posOrc);
				shot.rotateToAngle(dstAngle);
				objects.add(shot);
				orc.setEnableShot(false);
			}
		}
		else {
			orc.goIdle();
		}

		for (AbstractModel m : objects) {

			if (m instanceof Poste || m instanceof ObjetoColidivel) {
				// calculo de distancia tosco
				if (robot.collidesWith(m)) {
					robot.stepBack(delta);
				}
//				//if (lixo.getGameObject().getBoundingBox().intersects(m.getGameObject().getBoundingBox())) {
//					//System.out.println("");
//					colidiu = "Colisao";
//				}
			}
		}
	}
}
