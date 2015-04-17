/**
 * Administrator.java
 */
package com.gcit.librarymanagementsystem;

import java.util.Scanner;

/**
 * @author kemar
 *Apr 16, 20159:59:05 PM
 */
public class Administrator {
	Scanner sc;
	
/**
	 * @param sc
	 */
	public Administrator() {
		System.out.print("********Administrator Menu***********");

	}

public void adminMenu(){
	int menuSelection=0;
	sc=new Scanner(System.in);
System.out.println("\n1)Add/Update/Delete Book and Author\n"
		+ "2)Add/Update/Delete Publishers \n"
		+ "3)Add/Update/Delete Library Branches\n"
		+ "4)Add/Update/Delete Borrowers\n"
		+ "5)Over-ride Due Date for a Book Loan");
System.out.println("Enter a number from menu");
menuSelection=sc.nextInt();

switch (menuSelection) {
case 1:
	EditBookAndAuthor editBookAndAuthor = new EditBookAndAuthor();
	editBookAndAuthor.editBookOrAuthorMenu();
	break;
case 2:
	EditPublisher editPublisher = new EditPublisher();
	editPublisher.editPublisherMenu();
	break;
case 3:
	EditLibraryBranch editBranch = new EditLibraryBranch();
	
	editBranch.editLibraryBranchMenu();
	break;
default:
	break;
}
}



}
