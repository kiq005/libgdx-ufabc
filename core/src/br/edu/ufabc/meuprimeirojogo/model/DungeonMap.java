package br.edu.ufabc.meuprimeirojogo.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;
import br.edu.ufabc.meuprimeirojogo.core.GameObject;
import br.edu.ufabc.meuprimeirojogo.core.MazeGenerator;

public class DungeonMap {
	
	ObjetoColidivel ground[][];
	ObjetoColidivel walls[][];
	ObjetoColidivel objects[][];
	
	Hero hero;
	ArrayList<Enemy> enemies;
	
	int width;
	int height;
	
	private static final int GROUND = 0;
	
	private static final int N_WALL = 2;
	private static final int S_WALL = 3;
	private static final int E_WALL = 4;
	private static final int W_WALL = 5;
	
	private static final int NW_WALL = 6;
	private static final int NE_WALL = 7;
	private static final int SW_WALL = 8;
	private static final int SE_WALL = 9;
	
	private static final int NW_WALL_CORNER = 10;
	private static final int NE_WALL_CORNER = 11;
	private static final int SW_WALL_CORNER = 12;
	private static final int SE_WALL_CORNER = 13;
	
	private static final int SINGLE_WALL = 1;
	
	private int level;

	public DungeonMap(int width, int height, int level) {
		ground = new ObjetoColidivel[width][height];
		walls = new ObjetoColidivel[width][height];
		objects = new ObjetoColidivel[width][height];
		this.width = width;
		this.height = height;
		
		this.level = level;
		enemies = new ArrayList<>();
		
		int[][] map = GenerateMaze(width, height);
		AdjustTiles(map);
		
		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				ground[x][y] = new ObjetoColidivel(MeuJogo.modelManager.getModel("ground"));
				ground[x][y].getGameObject().transform.setToTranslation(new Vector3(x * 10, 0, y * 10));
				
				String model_name = "ground";
				switch(map[x][y]) {
				case GROUND:
					model_name = "ground";
					break;
				case SINGLE_WALL:
					model_name = "box";
					break;
				case N_WALL:
					model_name = "wall";
					break;
				case S_WALL:
					model_name = "wall";
					break;
				case W_WALL:
					model_name = "wall";
					break;
				case E_WALL:
					model_name = "wall";
					break;
				case NE_WALL:
					model_name = "corner_inner";
					break;
				case NW_WALL:
					model_name = "corner_inner";
					break;
				case SE_WALL:
					model_name = "corner_inner";
					break;
				case SW_WALL:
					model_name = "corner_inner";
					break;
				case NW_WALL_CORNER:
					model_name = "corner_outer";
					break;
				case NE_WALL_CORNER:
					model_name = "corner_outer";
					break;
				case SW_WALL_CORNER:
					model_name = "corner_outer";
					break;
				case SE_WALL_CORNER:
					model_name = "corner_outer";
					break;
				default:
					model_name = "ground";
					break;
				}
				
				Model go = MeuJogo.modelManager.getModel(model_name);
				
				walls[x][y] = new ObjetoColidivel(go);
				walls[x][y].getGameObject().transform.setToTranslation(new Vector3(x * 10, 0, y * 10));
				
				switch(map[x][y]) {
				case GROUND:
					AddObject(x, y);
					break;
				case SINGLE_WALL:
					break;
				case N_WALL:
					walls[x][y].getGameObject().transform.rotate(Vector3.Y, 180);
					//tiles[x][y].getGameObject().transform.translate(-10, 0, 0);
					break;
				case S_WALL:
					//tiles[x][y].getGameObject().transform.translate(0, 0, -10);
					break;
				case W_WALL:
					walls[x][y].getGameObject().transform.rotate(Vector3.Y, -90);
					//tiles[x][y].getGameObject().transform.translate(-10, 0, -10);
					break;
				case E_WALL:
					walls[x][y].getGameObject().transform.rotate(Vector3.Y, 90);
					break;
				case NE_WALL:
					walls[x][y].getGameObject().transform.rotate(Vector3.Y, 180);
					break;
				case NW_WALL:
					walls[x][y].getGameObject().transform.rotate(Vector3.Y, -90);
					break;
				case SE_WALL:
					walls[x][y].getGameObject().transform.rotate(Vector3.Y, 90);
					break;
				case SW_WALL:
					break;
				case NW_WALL_CORNER:
					break;
				case NE_WALL_CORNER:
					break;
				case SW_WALL_CORNER:
					break;
				case SE_WALL_CORNER:
					break;
				}
			}
		}
		
		System.out.println("MAP CREATED");
	}
	
	public void AdjustTiles(int[][] map){
		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				if(x==0) {
					if(y==0) {
						map[x][y] = NW_WALL;
					}
					else if(y==height-1) {
						map[x][y] = SW_WALL;
					}
					else {
						map[x][y] = W_WALL;
					}
				}
				else if(x==width-1) {
					if(y==0) {
						map[x][y] = NE_WALL;
					}
					else if(y==height-1) {
						map[x][y] = SE_WALL;
					}
					else {
						map[x][y] = E_WALL;
					}
				}
				else {
					if(y==0) {
						map[x][y] = N_WALL;
					}
					else if(y==height-1) {
						map[x][y] = S_WALL;
					}
					else {
						
					}
				}
			}
		}
	}
	
	public int[][] GenerateMaze(int width, int height){
		MazeGenerator mg = new MazeGenerator(width, height);
		int[][] map = mg.GetMaze();
		int[][] mapFinal = new int[width][height];
		int neighbours;
		
		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				mapFinal[x][y] = 1-map[x][y];
				neighbours = 0;
				
				if(x>0 && x<width-1 && y > 0 && y < height-1) {
					neighbours += map[x-1][y-1] + map[x][y-1] + map[x+1][y-1];
					neighbours += map[x-1][y] + map[x+1][y-1];
					neighbours += map[x-1][y+1] + map[x][y+1] + map[x+1][y+1];
				}
				
				if (neighbours < 3)
					mapFinal[x][y] = 0;
				
				if( x==0 || x == width-1 || y==0 || y==height-1) {
					mapFinal[x][y] = 1;
				}
				
			}
		}
		
		return mapFinal;
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
				objects.add(ground[x][y]);
				if(this.walls[x][y] != null) objects.add(this.walls[x][y]);
				if(this.objects[x][y] != null) objects.add(this.objects[x][y]);
			}
		}
		
		enemies.forEach( e -> objects.add(e));
		objects.add(hero);
	}
	
	public Hero GetHero() {
		return this.hero;
	}
	
	private void AddObject(int x, int y) {
		if(hero == null) {
			hero = new Hero(30, 200);
			hero.setPosition(x + 7.5f, 2.55f, y + 7.5f);
			hero.getGameObject().transform.scale(0.03f, 0.03f, 0.03f);
		}
		else if(x + y > 20 * (enemies.size()/level) && Math.random() < .125f) {
			/*
			Enemy enemy = new Enemy(100, 3, 20, 100, (int)(Math.random() * Enemy.enemyMap.size()), hero);
			enemy.setPosition(x + 7.5f, 2.55f, y + 7.5f);
			enemy.getGameObject().transform.scale(0.03f, 0.03f, 0.03f);
			enemies.add(enemy);
			*/
		}
		else {
			double objectSelection = Math.random() * 30;
			
			String model_name;
			
			if (objectSelection < 1) {
				model_name = "obj_s_low_1";
			}
			else if(objectSelection < 2) {
				model_name = "obj_s_low_2";
			}
			else if(objectSelection < 4) {
				model_name = "obj_low_1";
			}
			else if(objectSelection < 6) {
				model_name = "obj_low_2";
			}
			else if(objectSelection < 8) {
				model_name = "obj_low_3";
			}
			else if(objectSelection < 11) {
				model_name = "obj_medium_1";
			}
			else if(objectSelection < 14) {
				model_name = "obj_medium_2";
			}
			else if(objectSelection < 20) {
				model_name = "obj_high";
			}
			else {
				return;
			}
			
			Model go = MeuJogo.modelManager.getModel(model_name);
			objects[x][y] = new ObjetoColidivel(go);
			objects[x][y].getGameObject().transform.setToTranslation(new Vector3(x * 10, 0, y * 10));
		}
	}
	
	
}
