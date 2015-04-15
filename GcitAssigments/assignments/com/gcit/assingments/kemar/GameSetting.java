/**
 * GameSetting.java
 */
package com.gcit.assingments.kemar;

/**
 * @author kemar Bell
 *Apr 13, 201511:38:49 PM
 */
public class GameSetting {
	private String playerOne;//player one name
	private String playerTwo;//player two name
	private int pileSize;
	/**
	 * @return the playerOne
	 */
	public String getPlayerOne() {
		return playerOne;
	}
	/**
	 * @param playerOne the playerOne to set
	 */
	public void setPlayerOne(String playerOne) {
		this.playerOne = playerOne;
	}
	/**
	 * @return the playerTwo
	 */
	public String getPlayerTwo() {
		return playerTwo;
	}
	/**
	 * @param playerTwo the playerTwo to set
	 */
	public void setPlayerTwo(String playerTwo) {
		this.playerTwo = playerTwo;
	}
	/**
	 * @return the pileSize
	 */
	public int getPileSize() {
		return pileSize;
	}
	/**
	 * @param pileSize the pileSize to set
	 */
	public void setPileSize(int initialPileSize) {
		this.pileSize = initialPileSize;
	}

}
