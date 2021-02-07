package mazeGenerator;

import maze.Cell;

public class Edge {

	private Cell firstCell;
	private Cell secondCell;

	
	public Edge(Cell firstCell, Cell secondCell) {
		this.firstCell = firstCell;
		this.secondCell = secondCell;
	}
	
	
	public Cell getFirstCell() {
		return firstCell;
	}
	
	public Cell getSecondCell() {
		return secondCell;
	}	
}
