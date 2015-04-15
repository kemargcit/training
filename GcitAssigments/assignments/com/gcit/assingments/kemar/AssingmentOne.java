/**
 * AssingmentOne.java
 */
package com.gcit.assingments.kemar;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Kemar Bell
 *Apr 13, 20156:45:23 PM
 *1) user is asked a question to guess a number 
 *2) if the number he guesses is within 10 numbers plus or minus of the number the java program has,
 * then exit out and give the answer 
 *3) if the number he guesses is not within 10 numbers, ask the user to keep trying and give him more chances. 
 *4) if he is not able to guess within 5 chances, exit and display 'Sorry'
 */
public class AssingmentOne {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//create random number for the game number
		Random rn = new Random();
		int gameNumber = rn.nextInt(100) + 1;
		int userGuess=0;
		int tries =1;
		Scanner input = new Scanner(System.in);

		while (tries <= 5){
			System.out.println("enter a number between 1-100");
			userGuess=input.nextInt();
			//check if the user input is 10 + or - game number
			if (userGuess >= (gameNumber-10 )&& userGuess <= (gameNumber+10)){
				System.out.println("You almost got it "+ "the actual number is "+gameNumber);
				break;
			}
			else{
				//Increment tries and continue to while loop
				tries++;
				continue;
			}
		}
		
		if (tries>5){

			System.out.println("sorry you are out of tries");
		}
		input.close();


	}

}
