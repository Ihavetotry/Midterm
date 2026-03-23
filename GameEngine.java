package edu.txst.midterm;

/**
 * This class controls the game logic that includes the game movement,
 * the amount of steps taken, the coins collected and the win detection.
 */
public class GameEngine {
	private Board board;
	private int playerRow;
	private int playerCol;
	private int exitRow;
	private int exitCol;
	private StepCounter stepCounter;
	private int coins;

	// Cell Type Constants
	private static final int FLOOR = 0;
	private static final int WALL = 1;
	private static final int COIN = 2;
	private static final int EXIT = 5;
	private static final int PLAYER = 6;

	/**
	 * The constructor of the game engine for the board
	 * @param board creates a new board
	 */
	public GameEngine(Board board) {
		this.board = board;
		this.stepCounter = new StepCounter();
		this.coins = 0;
		findPlayer();
		findExit();
	}

	/**
	 * Determines when player has "won" or entered the exit
	 * @return if players has entered the exit
	 */
	public boolean playerWins() {
		return hasWon;
	}

	/**
	 * Returns the total amount of coins
	 * @return the current amount of coins
	 */
	public int getCoins() {
		return coins;
	}

	/**
	 * Returns the amount of step taken
	 * @return the current step count
	 */
	public int getSteps(){
		return stepCounter.getSteps();
	}

	/**
	 * This locates where the player is on the board
	 */
	private void findPlayer() {
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 10; c++) {
				if (board.getCell(r, c) == PLAYER) {
					playerRow = r;
					playerCol = c;
					return;
				}
			}
		}
	}

	/**
	 * Determines where the exit is located on the board
	 */
	private void findExit() {
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 10; c++) {
				if (board.getCell(r, c) == EXIT) {
					exitRow = r;
					exitCol = c;
					return;
				}
			}
		}
	}

	/**
	 * Attempts to move the player.
	 * 
	 * @param dRow Change in row (-1, 0, 1)
	 * @param dCol Change in column (-1, 0, 1)
	 */
	public void movePlayer(int dRow, int dCol) {
		int targetRow = playerRow + dRow;
		int targetCol = playerCol + dCol;
		int targetCell = board.getCell(targetRow, targetCol);

		// Check for Walls or Out of Bounds
		if (targetCell == WALL || targetCell == -1) {
			return; // Movement blocked
		}

		if(targetCell == COIN){
			coins++;
		}

		// Move the Player
		// Current position becomes Floor (or Goal if player was standing on one)
		// Note: For simplicity, this engine assumes player replaces the cell.
		// If you want "Player on Goal", you'd add a 6th constant.
		board.setCell(playerRow, playerCol, FLOOR);

		playerRow = targetRow;
		playerCol = targetCol;
		board.setCell(playerRow, playerCol, PLAYER);

		stepCounter.increaseSteps();

	}
}
