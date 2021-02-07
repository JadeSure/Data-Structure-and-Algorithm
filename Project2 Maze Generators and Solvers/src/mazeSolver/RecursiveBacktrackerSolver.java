package mazeSolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import maze.Cell;
import maze.Maze;

/**
 * Implements the recursive backtracking maze solving algorithm.
 */
public class RecursiveBacktrackerSolver implements MazeSolver {

	private Maze maze = null;

	private boolean solved = false;
	private boolean[][] visited = null;
	private HashSet<Cell> visitedCells = new HashSet<Cell>();

	// check does this one be visited or not
	boolean isVisited(Cell cell) {
		return this.visited[cell.r][cell.c];
	}

	@Override
	public void solveMaze(Maze maze) {
		this.maze = maze;

		this.visited = new boolean[maze.sizeR][maze.sizeC];
//		}
		Stack<Cell> stack = new Stack<Cell>();

		stack.push(maze.entrance);

		while (!stack.isEmpty()) {
			// check whether this point arrived to the block
			boolean expandable = false;
			Cell cell = (Cell) stack.peek();
			this.visited[cell.r][cell.c] = true;
			visitedCells.add(cell);
//			// draw the path for the maze
			maze.drawFtPrt(cell);

			// if the DFS way arrive in the exit cell
			if (cell == maze.exit) {
				this.solved = true;
				return;
			}

			// this cell has a tunnel and it is not been visited
			// push it to the stack, because this one will check after this step
			if (maze.type == 1 && cell.tunnelTo != null && !isVisited(cell.tunnelTo)) {
				stack.push(cell.tunnelTo);
				continue;
			}

			if (hasUnvisitedAround(cell)) {
				Cell newCell = getRandomUnvisitedAroundCell(cell);
				stack.push(newCell);
	
				visitedCells.add(cell);
				expandable = true;
			}

			if (!expandable) {
				stack.pop();
			}
		}

	} // end of solveMaze()

	// get random one cell which is unvisited around cell
	private Cell getRandomUnvisitedAroundCell(Cell current) {
		List<Cell> list = new ArrayList<Cell>(getUnvisitedCellsAround(current));
		return list.get(0);
	}

	// check the four directions to get the unvisited cell
	// if it has, it will return a set of collection, max = 3, min = 0
	private Collection<Cell> getUnvisitedCellsAround(Cell currentCell) {
		List<Cell> list = new ArrayList<>();
		// North Cell
		if (currentCell.r + 1 >= 0 && !(currentCell.wall[2].present)) {
			Cell ctmp = maze.map[currentCell.r + 1][currentCell.c];
			if (!visitedCells.contains(ctmp)) {
				list.add(ctmp);
			}
		
		}

		// RIGHT CELL
		if (currentCell.c + 1 < maze.sizeC && !(currentCell.wall[0].present)) {
			Cell ctmp = maze.map[currentCell.r][currentCell.c + 1];
			if (!visitedCells.contains(ctmp)) {
				list.add(ctmp);
			}
		
		}

		// South Cell
		if (currentCell.r - 1 < maze.sizeR && !(currentCell.wall[5].present)) {
			Cell ctmp = maze.map[currentCell.r - 1][currentCell.c];
			if (!visitedCells.contains(ctmp)) {
				list.add(ctmp);
			}
		
		}

		// LEFT CELL
		if (currentCell.c - 1 >= 0 && !(currentCell.wall[3].present)) {
			Cell ctmp = maze.map[currentCell.r][currentCell.c - 1];
			if (!visitedCells.contains(ctmp)) {
				list.add(ctmp);
			}
		
		}

		return list;
	}

	// this cell has unvisited cell neighbors
	private boolean hasUnvisitedAround(Cell current) {
		return getUnvisitedCellsAround(current).size() > 0;
	}

	@Override
	public boolean isSolved() {
		return this.solved;
	} // end if isSolved()

	// check do all the cells have been explored
	// if true, it will return true by isVisited and count++
	@Override
	public int cellsExplored() {
		int count = 0;

		for (int i = 0; i < maze.sizeR; i++) {
			for (int j = 0; j < maze.sizeC; j++) {
				if (this.visited[i][j])
					count++;
			}
		}
		return count;
	} // end of cellsExplored()

} // end of class RecursiveBackTrackerSolver
