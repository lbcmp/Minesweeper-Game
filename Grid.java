import java.util.Random;

public class Grid {
	private int numRows;
	private int numColumns;
	private int numBombs;
	private boolean[][] bombGrid;
	private int[][] countGrid;
	
	public Grid() {
		numRows = 10;
		numColumns = 10;
		numBombs = 25;
		bombGrid = createBombGrid();
		countGrid = createCountGrid();
	}
	
	public Grid(int numRows, int numColumns) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		numBombs = 25;
		bombGrid = createBombGrid();
		countGrid = createCountGrid();
	}
	
	public Grid(int numRows, int numColumns, int numBombs) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.numBombs = numBombs;
		bombGrid = createBombGrid();
		countGrid = createCountGrid();
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
	
	public int getNumBombs() {
		return numBombs;
	}
	
	public boolean[][] getBombGrid() {
		boolean [][] copy = new boolean[bombGrid.length][];
		for(int i = 0; i < bombGrid.length; i++) {
		    copy[i] = bombGrid[i].clone();
		}
		return copy;
	}
	
	public int[][] getCountGrid() {
		int [][] copy = new int[countGrid.length][];
		for(int i = 0; i < countGrid.length; i++) {
		    copy[i] = countGrid[i].clone();
		}
		return copy;
	}
	
	public boolean isBombAtLocation(int row, int column) {
		if (row >= 0 && row < countGrid.length && column >= 0 && column < countGrid[row].length) {
			if (bombGrid[row][column]) {
				return true;
			}		}
		return false;
	}
	
	public int getCountAtLocation(int row, int column) {
		if (row >= 0 && row < countGrid.length && column >= 0 && column < countGrid[row].length) {
			return countGrid[row][column];
		}
		return -1;
	}
	
	public boolean[][] createBombGrid() {
		boolean[][] bombGrid = new boolean[numRows][numColumns];
		for (int i = 0; i < numBombs; i++) {
		     Random rand = new Random();
		     int row = rand.nextInt(numRows);
		     int col = rand.nextInt(numColumns);
		     while(bombGrid[row][col]) { 
		          row = rand.nextInt(numRows);
		          col = rand.nextInt(numColumns);
		     }
		     bombGrid[row][col] = true;
		}
		return bombGrid;
	}
	
	public int[][] createCountGrid() {
		int[][] countGrid = new int[numRows][numColumns];
		for (int row = 0; row < countGrid.length; ++row) {
			for (int col = 0; col < countGrid[row].length; ++col) {
				countGrid[row][col] = ((row - 1 >= 0) && (col - 1 >= 0) && bombGrid[row - 1][col - 1]) ? countGrid[row][col] + 1 : countGrid[row][col];
				countGrid[row][col] = ((row - 1 >= 0) && bombGrid[row - 1][col]) ? countGrid[row][col] + 1 : countGrid[row][col];
				countGrid[row][col] = ((row - 1 >= 0) && (col + 1 < countGrid.length) && bombGrid[row - 1][col + 1]) ? countGrid[row][col] + 1 : countGrid[row][col];
				
				countGrid[row][col] = ((col - 1 >= 0) && bombGrid[row][col - 1]) ? countGrid[row][col] + 1 : countGrid[row][col];
				countGrid[row][col] = (bombGrid[row][col]) ? countGrid[row][col] + 1 : countGrid[row][col];
				countGrid[row][col] = ((col + 1 < countGrid.length) && bombGrid[row][col + 1]) ? countGrid[row][col] + 1 : countGrid[row][col];
				
				countGrid[row][col] = ((row + 1 < countGrid.length) && (col - 1 >= 0) && bombGrid[row + 1][col - 1]) ? countGrid[row][col] + 1 : countGrid[row][col];
				countGrid[row][col] = ((row + 1 < countGrid.length) && bombGrid[row + 1][col]) ? countGrid[row][col] + 1 : countGrid[row][col];
				countGrid[row][col] = ((row + 1 < countGrid.length) && (col + 1 < countGrid.length) && bombGrid[row + 1][col + 1]) ? countGrid[row][col] + 1 : countGrid[row][col];
			}
		}
		return countGrid;
	}
	// (row - 1, col - 1) (row - 1, col) (row - 1, col + 1)
	// (row, col - 1)     (row, col)     (row, col + 1)
	// (row + 1, col - 1) (row + 1, col) (row + 1, col + 1)
	
	public static void main(String[] args) {
		
	}	
	}

