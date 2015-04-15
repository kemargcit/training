/**
 * Score.java
 */
package com.gcit.assingments.kemar;

/**
 * @author Kemar Bell
 *Apr 14, 201512:47:53 AM
 */
public class Score {
	private  int playerOneChips; //the number of chips playerOne currently holds
	private  int playerTwoChips;
	
	/**
	 * @return the playerOneChips
	 */
	public int getPlayerOneChips() {
		return playerOneChips;
	}
	/**
	 * @param playerOneChips the playerOneChips to set
	 */
	public void setPlayerOneChips(int playerOneChips) {
		this.playerOneChips = playerOneChips;
	}
	/**
	 * @return the playerTwoChips
	 */
	public int getPlayerTwoChips() {
		return playerTwoChips;
	}
	/**
	 * @param playerTwoChips the playerTwoChips to set
	 */
	public void setPlayerTwoChips(int playerTwoChips) {
		this.playerTwoChips = playerTwoChips;
	}
}
