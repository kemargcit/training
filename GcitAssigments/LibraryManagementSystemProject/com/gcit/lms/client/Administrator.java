/**
 * Administrator.java
 */
package com.gcit.lms.client;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import com.gcit.librarymanagementsystem.EditBookAndAuthor;
import com.gcit.librarymanagementsystem.EditPublisher;
import com.gcit.librarymanagementsystem.UpdateBookLoan;

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
/**
 * administrator menu
 */
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
	EditBook editBook = new EditBook();
	editBook.editBookMenu();
	break;
	case 2:
	EditPublisher editPublisher = new EditPublisher();
	editPublisher.editPublisherMenu();
	break;
case 3:
	/*EditLibraryBranch editBranch = new EditLibraryBranch();
	editBranch.editLibraryBranchMenu();*/
	break;
case 4:
	EditBorrower editBorrower = new EditBorrower();
	editBorrower.editborrowerMenu();
	break;
case 5:
	UpdateBookLoan updateBookLoan = new UpdateBookLoan();
	try {
		updateBookLoan.updateDueDate();
	} catch (SQLException | ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	break;
default:
	break;
}
}



}
