/**
 * MainMenu.java
 */
package com.gcit.librarymanagementsystem;

import java.util.Scanner;

/**
 * @author kemar
 *Apr 16, 20159:42:06 PM
 */
public class MainMenu {
	Scanner sc;

	public void mainMenuSetup(){
		int menuSelection = 0;
		sc = new Scanner(System.in);
		
	System.out.println("Welcome to the GCIT Library Management"
			+ " System. Which category of a user are you");
	
	System.out.println("1)Librarian \n2)Administrator\n3)Borrower ");
	
	System.out.println("Enter a number from menu");
	menuSelection=sc.nextInt();
	
	switch (menuSelection) {
	case 2:
		Administrator admin = new Administrator();
		admin.adminMenu();
		break;

	default:
		break;
	}
	
	
	
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainMenu mainMenu = new MainMenu();
		mainMenu.mainMenuSetup();
		
		
		
	}

}
