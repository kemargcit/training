/**
 * UpdateBookLoan.java
 *//*
package com.gcit.lms.client;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.LibraryBranch;
import com.gcit.lms.service.BookLoanService;
import com.gcit.lms.service.BookService;
import com.gcit.lms.service.BorrowerService;
import com.gcit.lms.service.LibraryBranchService;

*//**
 * @author kemar bell
 *Apr 17, 20154:29:21 PM
 *//*
public class UpdateBookLoan {

	Scanner sc = new Scanner(System.in);
	BookLoanService bookLoanService = new BookLoanService();
    BookService bookService = new BookService();
    BorrowerService borrowerService= new BorrowerService();
    LibraryBranchService libraryBranchService = new LibraryBranchService();

	public  UpdateBookLoan(){
		System.out.println("******Update Due Date******");


	}
*//**
 * override the due date for a loan
 * @throws SQLException
 * @throws ParseException
 *//*

	

	public void updateDueDate() throws SQLException, ParseException{
		BookLoan bookLoan  = new BookLoan();
		System.out.println("enter bookId, branchId and cardNo overide due date");
		System.out.println("bookId");
		int bookId=sc.nextInt();
		System.out.println("branchId");
		int branchId = sc.nextInt();
		System.out.println("cardNo");
		int cardNo = sc.nextInt();
		Book book = bookService.getBookById(bookId);
		Borrower borrower = borrowerService.getBorrowerByCardNo(cardNo);
		LibraryBranch libraryBranch = libraryBranchService.getBranchById(branchId);
		
          bookLoan.setBook(book);
          bookLoan.setBorrower(borrower);
          bookLoan.setLibraryBranch(libraryBranch);
         // bookLoan.setDateDue(dateDue);
          
          bookLoanService.overideDueDate(bookLoan);
		
		
        }


}
*/