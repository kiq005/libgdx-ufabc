package br.edu.ufabc.meuprimeirojogo.model;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;
import br.edu.ufabc.meuprimeirojogo.core.MazeGenerator;

public class DungeonMap {
	
	ObjetoColidivel tiles[][];
	int width;
	int height;

	public DungeonMap(int width, int height) {
		tiles = new ObjetoColidivel[width][height];
		this.width = width;
		this.height = height;
		
		int[][] map = GenerateMaze(width, height);
		
		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				String model_name = "ground";
				switch(map[x][y]) {
				case 0:
					model_name = "ground";
					break;
				case 1:
					model_name = "cliff";
					break;
				case 2:
					model_name = "ground";
					break;
				default:
					model_name = "ground";
					break;
				}
				
				Model go = MeuJogo.modelManager.getModel(model_name);
				
				tiles[x][y] = new ObjetoColidivel(go);
				tiles[x][y].getGameObject().transform.setToTranslation(new Vector3(x * 10, 0, y * 10));
			}
		}
		
		System.out.println("MAP CREATED");
	}
	
	public int[][] GenerateMaze(int width, int height){
		MazeGenerator mg = new MazeGenerator(width, height);
		int[][] map = mg.GetMaze();
		
		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				if( x==0 || x == width-1 || y==0 || y==height-1) {
					map[x][y] = 1;
				}
			}
		}
		
		return map;
	}
	
	public int[][] GenerateMap(int width, int height){
		int[][] map = new int[width][height];
		
		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				if( x==0 || x == width-1 || y==0 || y==height-1) {
					map[x][y] = 1;
				}
				else {
					map[x][y] = 0;
				}
			}
		}
		
		return map;
	}
	
	public void AddToObjectList(Array<AbstractModel> objects) {
		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				objects.add(tiles[x][y]);
			}
		}
	}
	
	
	
}
