package mazeGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import maze.Cell;
import maze.Maze;
import maze.Wall;

public class KruskalGenerator implements MazeGenerator {

	private int mazeWidth;
	private int mazeHeight;
	private ArrayList<HashSet<Cell>> setList = new ArrayList<>();
	private ArrayList<Wall> wallList = new ArrayList<>();
	private Maze maze;


	@Override
	public void generateMaze(Maze maze) {
		mazeHeight = maze.sizeR;
		mazeWidth=maze.sizeC;
		int length = mazeHeight * mazeHeight;
		this.maze = maze;


		//Create set for each cell and add to ArrayList of all sets
		for (int x = 0; x < mazeWidth; x++){
			for (int y = 0; y < mazeHeight; y++){
				HashSet<Cell> tmpSet = new HashSet<Cell>();
				if (getSetIndexOf(maze.map[x][y])!=-2){
					continue;
				}
				tmpSet.add(maze.map[x][y]);
				if (maze.map[x][y].tunnelTo!=null){
					tmpSet.add(maze.map[x][y].tunnelTo);
				}
				setList.add(tmpSet);
			}
		}

		//Initialize set of walls - add horizontal walls
		for (int x = 0; x < mazeHeight; x++) {
			for (int y = 0; y < mazeWidth; y++){
				//set it as DOWN or UP - doesn't matter
				wallList.add(maze.map[x][y].wall[2]);
			}
		}

		//Initialize set of walls - add vertical walls
		for (int x = 0; x < mazeWidth; x++) {
			for (int y = 0; y < mazeHeight; y++){
				//set it as LEFT or RIGHT - doesn't matter
				wallList.add(maze.map[x][y].wall[0]);
			}
		}
		Cell c1 ;
		Cell c2 ;
		Wall currentWall ;
		while(!wallList.isEmpty()){
			currentWall = getRandomWall();
			List<Cell> adjacentCells = getAdjacentCells(currentWall);
			if (adjacentCells.size()!=2){
				wallList.remove(currentWall);
				continue;
			}
			c1 = adjacentCells.get(0);
			c2 = adjacentCells.get(1);
			if (getSetIndexOf(c1) != getSetIndexOf(c2)){
				joinSetsOf(c1, c2);
				currentWall.present=false;
			}
			wallList.remove(currentWall);
		}

	} // end of generateMaze()

	private int getSetIndexOf(Cell c){
		int out = -2;
		for (int x = 0; x < setList.size(); x++){
			if (setList.get(x).contains(c)) {
				out = x;
			}
		}
		return out;
	}

	private List<Cell> getAdjacentCells(Wall currentWall) {
		List<Cell> result = new ArrayList<>();
		for (int x = 0; x < mazeHeight; x++) {
			for (int y = 0; y < mazeWidth; y++){
				if (Arrays.asList(maze.map[x][y].wall).contains(currentWall)){
					result.add(maze.map[x][y]);
				}
			}
		}
		return result;
	}

	private Wall getRandomWall(){
		Collections.shuffle(wallList);
		return wallList.get(0);
	}

	private void joinSetsOf(Cell c1, Cell c2){
		int index1 = getSetIndexOf(c1);
		int index2 = getSetIndexOf(c2);
		Cell tmpCell = new Cell();

		Iterator it = setList.get(index2).iterator();
		while (it.hasNext()){
			tmpCell = (Cell)it.next();
			setList.get(index1).add(tmpCell);
		}
		setList.remove(index2);
	}


} // end of class KruskalGenerator
