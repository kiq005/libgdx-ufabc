package br.edu.ufabc.meuprimeirojogo.core;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.model.AbstractCharacter;
import br.edu.ufabc.meuprimeirojogo.model.AbstractModel;
import br.edu.ufabc.meuprimeirojogo.model.DungeonMap;
import br.edu.ufabc.meuprimeirojogo.model.Enemy;
import br.edu.ufabc.meuprimeirojogo.model.Hero;
import br.edu.ufabc.meuprimeirojogo.util.Utilities;

public class DungeonAction {

	protected Array<AbstractModel> objects;
	protected Hero hero;
	
	private float attack_radious = 14f;
	private int level = 1;
	private DungeonMap map;
	
	private boolean creatingNewDungeon = false;
	
	private int progress = 0;

	public DungeonAction() {
		CreateDungeon();
	}

	public void update(float delta) {
		if(creatingNewDungeon) {
			updateCreationProcess();
			return;
		}
		
		boolean foundEnemy = false;
		float minDistToHero = attack_radious;
		for (AbstractModel o : objects) {
			o.update(delta);
			if( o instanceof Enemy) {
				Enemy enemy = (Enemy) o;
				if (enemy.getHealthPoints() <= 0) continue;
				
				float dist = enemy.getPosition().sub(hero.getPosition()).len();
				if(dist < minDistToHero) {
					minDistToHero = dist;
					hero.SetEnemy(enemy);
					foundEnemy = true;
				}
			}
		}
		
		if(!foundEnemy) hero.SetEnemy(null);
		hero.SetCounter(map.CountEnemies());
		
		if(map.CountEnemies() <= 0) {
			++level;
			String[] mapTypes = new String[] {"Cave", "Castle", "Cemetery", "Forest"};
			MeuJogo.modelManager.loadBundle(new String[] {
					mapTypes[level % mapTypes.length],
					"Enemy", "Collectables", "Hero"});
			creatingNewDungeon = true;
		}
		
		// Collisions
		/*
		for(int i=0; i<objects.size; ++i) {
			if (objects.get(i) instanceof AbstractCharacter) {
				AbstractCharacter o1 = (AbstractCharacter) objects.get(i); 
				for(int j=i+1; j < objects.size; ++j) {
					if (o1.collidesWith(objects.get(j))) {
						o1.revertMovement();
					}
				}
			}
		}*/
	}
	
	private void updateCreationProcess() {
		if (!MeuJogo.modelManager.assetManager.isFinished()) {
			MeuJogo.modelManager.assetManager.update();
			// TODO: Indicate loading progress...
		} else {
			creatingNewDungeon = false;
			CreateDungeon();
		}
	}
	
	private void CreateDungeon() {
		map = new DungeonMap(20 + level, 20 + level, level);
		hero = map.GetHero();
		
		objects = new Array<AbstractModel>();
		map.AddToObjectList(objects);
		
		for (AbstractModel obj : objects) {
			for (Material mat : obj.getGameObject().materials) {
				mat.remove(ColorAttribute.Emissive);
			}
		}
	}
}
