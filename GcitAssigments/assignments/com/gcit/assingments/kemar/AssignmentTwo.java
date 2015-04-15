/**
 * AssignmentTwo.java
 */
package com.gcit.assingments.kemar;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Kemar Bell Apr 13, 2015 10:46:29 PM odd chips game
 */
public class AssignmentTwo {

	/**
	 * @param args
	 */
	 GameSetting gameSettings = new GameSetting();
	 Score score = new Score();
	 Scanner sc = new Scanner(System.in);
	 boolean isGameOver = false;
	private  int maxNumberOfYouCanTake = 0;
	AssignmentTwo assignmentTwo= new AssignmentTwo();


	/**
	 * This method gets the names of the players form input and sets to game
	 * settings object
	 * 
	 * @return an array of player names
	 */
	public  String[] getPlayers() {
		// get player names
		System.out.println("Enter the name of the first player");

		String playerOne = sc.next();
		System.out.println("Enter the name of the second player");

		String playerTwo = sc.next();
		while (playerOne.equalsIgnoreCase(playerTwo)) {
			System.out
					.println("Enter a deferent name for player two both players cant have the name "
							+ playerOne);
			playerTwo = sc.next();

		}
		// store player names to array
		String[] players = new String[2];
		players[0] = playerOne;
		players[1] = playerTwo;
		gameSettings.setPlayerOne(playerOne);
		gameSettings.setPlayerTwo(playerTwo);

		return players;

	}

	/**
	 * get the initial pile size from the users
	 * 
	 * @return the size of the pile
	 */
	public  int getInitialPile() {
		int pileSize = 0;
		System.out.println("Enter an odd number > 3 for pile size");
		try {
			pileSize = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("This is not a Numeber!");
			sc.next();
			pileSize = getInitialPile();
		}
		// TODO validate input for input mismatch exception
		while (pileSize < 3) {
			System.out
					.println("You have to start with at least 3 chips. Choose another number");
			pileSize = sc.nextInt();

		}
		while (pileSize % 2 == 0) {
			System.out
					.println("You have to start with an odd number of chips. Choose another number: ");
			pileSize = sc.nextInt();
		}
		gameSettings.setPileSize(pileSize);
		return pileSize;

	}

	/**
	 * sets up a new game
	 */
	public void setUpGame() {
		assignmentTwo.getPlayers();
		assignmentTwo.getInitialPile();

		score.setPlayerOneChips(0);
		score.setPlayerTwoChips(0);
	}

	/**
	 * this method processes a a players turn by taking his/her choice and
	 * updating score and pile size
	 * 
	 * @param playerName
	 *            the name of the player whose turn it is
	 * @param maxChips
	 *            the maximum number of chips that the current player can choose
	 */
	public  int turn(String playerName, int maxChips) {
		int choice = 0;// the amount of chips a user chooses
		if (maxChips == 0) {
			maxChips = 1;
		}

		System.out.println("you can take any number of chips between 1-"
				+ maxChips + " how much chips will you take " + playerName);
		// TODO validate use choice --catch input mismatch exception
		try {
			choice = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("This is not a Numeber!");
			sc.next();

			choice = assignmentTwo.turn(playerName, maxChips);
		}

		if (choice > maxChips || choice <= 0) {
			System.out.println("OUT OF RANGE");
			choice = 0;
			assignmentTwo.turn(playerName, maxChips);
		}

		if (playerName.equals(gameSettings.getPlayerOne())) {

			score.setPlayerOneChips(score.getPlayerOneChips() + choice);

		} else {
			score.setPlayerTwoChips(score.getPlayerTwoChips() + choice);
		}
		gameSettings.setPileSize(gameSettings.getPileSize() - choice);
		return choice;
	}

	/**
	 * this method checks which players turn it is and allow that player to take
	 * his/hers turn and also if the game is over checks who is the winer and
	 * prints the winner
	 */
	public void play() {
		int plays = 2;
		while (gameSettings.getPileSize() > 0) {
			System.out.println(gameSettings.getPlayerOne() + " has "
					+ score.getPlayerOneChips() + " chips");
			System.out.println(gameSettings.getPlayerTwo() + " has "
					+ score.getPlayerTwoChips() + " chips");

			if (plays % 2 == 0) {// if plays is even its is player ones turn
									// else it is player twos turn
				System.out.println("It's your turn "
						+ gameSettings.getPlayerOne());
			} else {
				System.out.println("It's your turn "
						+ gameSettings.getPlayerTwo());
			}

			System.out.print("there are " + gameSettings.getPileSize()
					+ " chips remainining");
			maxNumberOfYouCanTake = (gameSettings.getPileSize() - 1) / 2;
			if (plays % 2 == 0) {
				System.out.println(" how much will you take "
						+ gameSettings.getPlayerOne());
				assignmentTwo.turn(gameSettings.getPlayerOne(),
						maxNumberOfYouCanTake);
			} else {
				System.out.println(" how much will you take "
						+ gameSettings.getPlayerTwo());

				assignmentTwo.turn(gameSettings.getPlayerTwo(),
						maxNumberOfYouCanTake);

			}

			plays++;
		}
		if (gameSettings.getPileSize() == 0) {
			System.out.println(gameSettings.getPlayerOne() + " has "
					+ score.getPlayerOneChips() + " chips");
			System.out.println(gameSettings.getPlayerTwo() + " has "
					+ score.getPlayerTwoChips() + " chips");

			if (score.getPlayerOneChips() % 2 == 0) {
				System.out.println("*** "
						+ gameSettings.getPlayerOne().toUpperCase()
						+ " WINS ***");
				isGameOver = true;
			} else {
				System.out.println("*** "
						+ gameSettings.getPlayerTwo().toUpperCase()
						+ " WINS ***");
				isGameOver = true;

			}
		}
	}

	/**
	 * check if the user wants to replay or exit
	 * 
	 * @return true if user wants to play again and false if the user does not
	 *         want to play again
	 */

	public  boolean replayOrExit() {
		String nextGame = null;
		System.out.println("Play another game? (y/n)");
		nextGame = sc.next();

		if (nextGame.equalsIgnoreCase("y")) {
			return true;

			// if user chooses y set isGame over back to false and continue to
			// while loop to start a new game
		} else if (nextGame.equalsIgnoreCase("n")) {

			return false;

		} else {
			System.out.println("Enter either y for new game or n to exit");
			// System.out.println(isReplay);

			return assignmentTwo.replayOrExit();

		}

	}

	public static void main(String[] args) {
		
		while (isGameOver == false) {
			assignmentTwo.setUpGame();
			assignmentTwo.play();

			// if game is over which occurs in the plays method when pileSize
			// gets to 0 and the game is won, check if the users wants to play
			// again
			if (isGameOver) {

				if (replayOrExit() == true) {
					isGameOver = false;

					continue;
				} else {
					System.out.println("Goodbye");
					System.exit(0);
				}

			}
		}
	}

}
