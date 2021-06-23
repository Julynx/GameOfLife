package game;
import java.util.Scanner;

/**
 * Conway's Game of Life
 * Simple Text Based Java Implementation
 * Sample testing code
 * 
 * IMPORTANT: If you are using Eclipse you must unlock the console by disabling the
 * option "Limit Console Output" as shown below:
 *     
 *     Window > Preferences > Run/Debug > Console > Limit Console Output [ ]
 * 
 * -- INPUT EXAMPLE: THE ACORN --
 * 
 *     25, 80
 *     25, 81
 *     23, 81
 *     24, 83
 *     25, 84
 *     25, 85
 *     25, 86
 * 
 * @author juliocabria.business@protonmail.com
 * @date 23/06/2021
 */
public class Main {
	
	public static final int iterations = 5206;
	public static final int screenHeight = 40;
	public static final int screenWidth = 170;
	
	/**
	 * Main function that asks for a set of cells and simulates the game
	 * @param args Currently arguments are not used
	 * @throws InterruptedException Throws an exception when the thread is
	 * interrupted in its sleep
	 */
	public static void main(String[] args) throws InterruptedException {
		
		GameOfLife game = new GameOfLife(screenHeight, screenWidth);
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		String inputLine = ""; 
		
		// Print welcome message and initial board
		System.out.println("Conway's Game of Life");
		System.out.print(game);
		
		// Ask for cells to insert
		while (!inputLine.contentEquals("I")) {

			System.out.print("Introduce a cell by its coordinates \"3, 4\" or \"I\" to start the simulation: ");
			inputLine = keyboard.nextLine();
			
			if (!inputLine.contentEquals("I")) {
				
				String[] coordinates = inputLine.split(",");
				
				int posx = Integer.valueOf(coordinates[0].trim());
				int posy = Integer.valueOf(coordinates[1].trim());
			
				game.insertCell(posx, posy);
				System.out.print(game);
			}
		}
		
		// Game simulation
		for (int i = 0; i < iterations; i ++) {
			
			System.out.println("Iteration " + i + " of " + iterations);
			System.out.print(game);
			game.next();
			Thread.sleep(500);
		}
	}
}
