package mazeSolver;

import java.util.Random;
import java.util.Stack;
import maze.Cell;
import maze.Maze;

public class SampleSolver implements MazeSolver {
	Maze maze = null;

	Random randGen = new Random();

	boolean solved = false;
	boolean[][] visited = null;

	// randomly get a direction sequential for each time
	protected int[] randDirSeq() {
		int[] seq = new int[6];
		boolean[] present = new boolean[6];
		// make sure 6 directions has random number and seperated by different numbers
		for (int i = 0; i < 6; i++) {
			
			do {
				seq[i] = this.randGen.nextInt(6);
			} while (present[seq[i]]);
			
			present[seq[i]] = true;
		}
		return seq;
	}

	
	boolean isVisited(Cell cell) {
		return this.visited[cell.r][cell.c];
	}

	public void solveMaze(Maze maze) {
		this.maze = maze;
//		if (maze.type == 2) {
//			this.visited = new boolean[maze.sizeR][maze.sizeC + (maze.sizeR + 1) / 2];
//		} else {
			this.visited = new boolean[maze.sizeR][maze.sizeC];
//		}
		Stack<Cell> stack = new Stack<Cell>();

		stack.push(maze.entrance);

		while (!stack.isEmpty()) {
			Cell cell = (Cell) stack.peek();
			this.visited[cell.r][cell.c] = true;
			maze.drawFtPrt(cell);
			if (cell == maze.exit) {
				this.solved = true;
				return;
			}
			if (maze.type == 1 && cell.tunnelTo != null && !isVisited(cell.tunnelTo)) {
				stack.push(cell.tunnelTo);
				continue;
			}
			boolean expandable = false;
			int[] seq = randDirSeq();
			for (int i = 0; i < 6; i++) {
				Cell next = cell.neigh[seq[i]];
				if (next != null && !(cell.wall[seq[i]]).present && !isVisited(next)) {
					stack.push(next);
					expandable = true;
					break;
				}
			}
			if (!expandable) {
				stack.pop();
			}
		}
	}

	public boolean isSolved() {
		return this.solved;
	}

	public int cellsExplored() {
		int acc = 0;
		int sizeC = this.maze.sizeC;
		if (this.maze.type == 2) {
			sizeC = this.maze.sizeC + (this.maze.sizeR + 1) / 2;
		}

		for (int i = 0; i < this.maze.sizeR; i++) {
			for (int j = 0; j < sizeC; j++) {
				if (this.visited[i][j])
					acc++;
			}
		}
		return acc;
	}
}
