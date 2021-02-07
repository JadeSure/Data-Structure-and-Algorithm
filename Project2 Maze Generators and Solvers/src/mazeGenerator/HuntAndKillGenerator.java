package mazeGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import maze.Cell;
import maze.Maze;

public class HuntAndKillGenerator implements MazeGenerator {

	private Cell current;
	private HashSet<Cell> visitedCells = new HashSet<Cell>();
	private int mazeHeight;
	private int mazeWidth;
	private Maze maze;

	public HuntAndKillGenerator() {
	}

	@Override
	public void generateMaze(Maze maze) {
		mazeHeight = maze.sizeR;
		mazeWidth=maze.sizeC;
		int length = mazeHeight * mazeHeight;
		this.maze = maze;
		current = getRandomCell(maze.sizeC,maze.sizeR);
		visitedCells.add(current);
		
		while (visitedCells.size()<length){
			 if (current.tunnelTo !=null && !visitedCells.contains(current.tunnelTo)) {
				current = current.tunnelTo;
				visitedCells.add(current);
			}
			 // get a random unvisited neighbor, break it and add it to cell set
			else if (hasUnvisitedAround(current) ){
				// create new cell as the random unvisited around current cell
				Cell newCell = getRandomUnvisitedAroundCell(current);
				addPathBetween(current, newCell);
				current = newCell;
				visitedCells.add(current);
				// arrive the wall (without neighbor), random select the other one
			} else {
				current = getRandomUncompleteCell();
			}
		}

	} // end of generateMaze()

	// randomly find a visited cell which has at least one unvisited neighbor
	// low effiency?
	private Cell getRandomUncompleteCell() {
		// put all the visited cell into tmpList and try 
		ArrayList<Cell> tmpList = new ArrayList<Cell>(visitedCells);
		Cell cell = tmpList.get(0);

		while (!hasUnvisitedAround(cell)) {
			Collections.shuffle(tmpList);
			cell = tmpList.get(0);
		}
		return cell;
	}

	// break the path between two selected, adjacent cell
	private void addPathBetween(Cell current, Cell newCell) {
		//North Cell
		if (current.r-1 == newCell.r){
			current.wall[5].present=false;
			newCell.wall[2].present=false;
			return;
		}
		//South Cell
		if (current.r+1 == newCell.r){
			current.wall[2].present=false;
			newCell.wall[5].present=false;
			return;
		}
		//EAST CELL
		if (current.c+1 == newCell.c){
			current.wall[0].present=false;
			newCell.wall[3].present=false;
			return;

		}
		//WEST CELL
		if (current.c-1 == newCell.c){
			current.wall[3].present=false;
			newCell.wall[0].present=false;
			return;
		}
	}

	// get random one cell which is unvisited around cell
	private Cell getRandomUnvisitedAroundCell(Cell current) {
		List<Cell> list = new ArrayList<Cell>(getUnvisitedCellsAround(current));
		Collections.shuffle(list);
		return list.get(0);
	}

	// this cell has unvisited cell neighbors
	private boolean hasUnvisitedAround(Cell current) {
		return getUnvisitedCellsAround(current).size() > 0;
	}

	// check the four directions to get the unvisited cell
	// if it has, it will return a set of collection, max = 3, min = 0
	private Collection<Cell> getUnvisitedCellsAround(Cell currentCell) {
		HashSet<Cell> set = new HashSet<>();
		//North Cell
		if (currentCell.r-1 >= 0){
			Cell ctmp = maze.map[currentCell.r-1][currentCell.c];
			if (!visitedCells.contains(ctmp)){
				set.add(ctmp);
			}
		}
		//South Cell
		if (currentCell.r+1 < mazeHeight){
			Cell ctmp = maze.map[currentCell.r+1][currentCell.c];
			if (!visitedCells.contains(ctmp)){
				set.add(ctmp);
			}
		}
		//RIGHT CELL
		if (currentCell.c+1 < mazeWidth){
			Cell ctmp = maze.map[currentCell.r][currentCell.c+1];
			if (!visitedCells.contains(ctmp)){
				set.add(ctmp);
			}
		}
		//LEFT CELL
		if (currentCell.c-1 >= 0){
			Cell ctmp = maze.map[currentCell.r][currentCell.c-1];
			if (!visitedCells.contains(ctmp)){
				set.add(ctmp);
			}
		}
		return set;
	}

	// randomly select a cell as the starting point
	private Cell getRandomCell(int mazeWidth, int mazeHeight) {
		Random r = new Random();
		Cell c = maze.map[r.nextInt(mazeHeight)][r.nextInt(mazeWidth)];
		return c;
	}
} // end of class HuntAndKillGenerator

