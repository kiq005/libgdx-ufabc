package br.edu.ufabc.meuprimeirojogo.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import br.edu.ufabc.meuprimeirojogo.util.Utilities;

public class MazeGenerator {
	private final int width;
	private final int height;
	private final int[][] maze;
 
	public MazeGenerator(int width, int height) {
		this.width = width;
		this.height = height;
		maze = new int[this.width][this.height];
		generateMaze(width/2, height/2, 10, 1000);
	}
	
	public int[][] GetMaze(){
		return maze;
	}
 
	private void generateMaze(int cx, int cy, int agents, int life) {
		ArrayList<DungeonGeneratorAgent> agentsList = new ArrayList<>();
		while(agents > 0) {
			agentsList.add(new DungeonGeneratorAgent(Utilities.random.nextInt(width), Utilities.random.nextInt(height), Utilities.getRandomNumberInRange(1, life)));
			--agents;
		}
		
		while(!agentsList.isEmpty()) {
			for(DungeonGeneratorAgent agent : agentsList) {
				agent.move(maze);
			}
			agentsList.removeIf( a -> (a.life <= 0) );
		}
	}
 
	private class DungeonGeneratorAgent{
		public int x;
		public int y;
		public int life;
		
		public DungeonGeneratorAgent(int x, int y, int life) {
			this.x = x;
			this.y = y;
			this.life = life;
		}
		
		public void move(int[][] maze) {
			x += Utilities.getRandomNumberInRange(-1, 1);
			y += Utilities.getRandomNumberInRange(-1, 1);
			if (x<0) x = 0;
			if (x>width-1) x = width-1;
			if (y<0) y = 0;
			if (y>height-1) y = height-1;
			
			maze[x][y] = 1;
			life -= 1;
		}
	}
	
	

}
