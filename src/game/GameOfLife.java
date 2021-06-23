package game;

/**
 * Conway's Game of Life. 
 * Simple Text Based Java Implementation
 * @author juliocabria.business@protonmail.com
 * @date 23/06/2021
 */
public class GameOfLife {
	// Characters used to print dead and alive cells
	private static final char aliveChar = 'o';
	private static final char deadChar = '-';
	// Width and Height of the simulation pool
	private int width = 4000;
	private int height = 4000;
	// Area of the simulation shown on screen
	private int shownWidth = 40;
	private int shownHeight = 170;
	// Position of the shown area, new cells will be inserted at this offset
	private int offsetx = width/2;
	private int offsety = height/2;
	// The simulation pool 
	private int[][] pool =  new int[this.width][this.height];
	
	/**
	 * Game of Life constructor.
	 * It sets the screen area and initializes the pool.
	 * @param x Width of the screen.
	 * @param y Height of the screen.
	 */
	public GameOfLife(int x, int y) {

		this.shownWidth = x;
		this.shownHeight = y;
		
		for (int i = 0; i < this.width; i++)
			for (int j = 0; j < this.height; j++)
				this.pool[i][j] = 0;
	}
	
	/**
	 * Method to compute the next pool state and update the board
	 */
	public void next() {
		
		int[][] nextPool = new int[width][height];
		
		for (int i = 1; i < width - 1; i++) {
			for (int j = 1; j < height - 1; j++) {
				
				int aliveNeighbors = this.getAliveNeighbors(i, j);
				boolean iAmAlive = (this.pool[i][j] == 1) ? true : false;
				
				// Death by loneliness
				if(iAmAlive && (aliveNeighbors < 2)) {
					nextPool[i][j] = 0;
					
				// Death by over-population
				} else if (iAmAlive && (aliveNeighbors > 3)) {
					nextPool[i][j] = 0;
					
				// Birth by population
				} else if (!iAmAlive && (aliveNeighbors == 3)) {
					nextPool[i][j] = 1;
					
				} else {
					nextPool[i][j] = this.pool[i][j];
				}	
			}
		}
		// The board is updated with the new state
		this.pool = nextPool;
	}
	
	/**
	 * Method to compute the number of alive neighbors of a cell,
	 * given its coordinates in the game board
	 * @param posx Width coordinate of the computed cell
	 * @param posy Height coordinate of the computed cell
	 * @return Number of alive neighbors
	 */
	public int getAliveNeighbors(int posx, int posy) {
		
		int neighbors = 0;
		
		// Account for the cell surroundings
		for (int i =  -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++) 	
				neighbors += this.pool[posx + i][posy + j];
		
		// Subtract the cell itself
		neighbors -= this.pool[posx][posy];
		
		return neighbors;
	}
	
	/**
	 * Method to insert an alive cell onto the board
	 * @param posx Width coordinate of the new cell
	 * @param posy Height coordinate of the new cell
	 */
	public void insertCell(int posx, int posy) {
		
		this.pool[posx + offsetx][posy + offsety] = 1;
	}
	
	/**
	 * Method to print the game pool. Overrides toString
	 */
	@Override
	public String toString() {
		
		int widthPrinted = Math.min(this.width, this.shownWidth);
		int heightPrinted = Math.min(this.height, this.shownHeight);
		
		String str = "";
		
		for (int i = offsetx; i < widthPrinted  + offsetx; i++) {
			for (int j = offsety; j < heightPrinted + offsety; j++) {
				str += ((this.pool[i][j] == 1) ? aliveChar: deadChar);
			}
			str += "\n";
		}
		return str;
	}
}
