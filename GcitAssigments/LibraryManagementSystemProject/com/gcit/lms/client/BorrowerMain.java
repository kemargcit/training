/**
 * BorrowerMain.java
 */
package com.gcit.lms.client;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopy;
import com.gcit.lms.domain.LibraryBranch;
import com.gcit.lms.service.AdministratorService;
import com.gcit.lms.service.LibrarianService;

/**
 * @author kemar
 *Apr 22, 20153:16:44 PM
 */
public class BorrowerMain {
Scanner sc = new Scanner(System.in);
LibrarianService librarianService = new LibrarianService();
AdministratorService administratorService = new AdministratorService();
String borrowerNum;
	/**
	 * 
	 */
	public void borrowerMenu() {
		System.out.println("enter borrower number");
		 borrowerNum = sc.next();
		if(borrowerNum.equals(null)){
			this.borrowerMenu();
		}else{
			this.borr1();
		}
		
	}
	/**
	 * 
	 */
	private void borr1() {
		System.out.println("1)Check out a book \n2)	Return a Book \n3) Quit to Previous");
		int borr1Selection = sc.nextInt();
		if(borr1Selection==1){
			try {
				this.checkOutBook();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (borr1Selection==2){
			
		}else if(borr1Selection==3){
			this.borr1();
		}
	}
	/**
	 * @throws SQLException 
	 * 
	 */
	private void checkOutBook() throws SQLException {	
		LibraryBranch  libraryBranch = new LibraryBranch();
		System.out.println("Pick the branch you want to check out from");
		List<LibraryBranch> libraryBranchs=librarianService.readAll();
		int branchNum=1;
		  for(LibraryBranch Branch:libraryBranchs){
			  System.out.println(branchNum++ +") "+Branch.getBranchName()+", "+Branch.getBranchAddress());
		  }
		
		  System.out.println(branchNum+")back to preveios");
		  
		  int selectedBranch = sc.nextInt();
			if (selectedBranch-1 == libraryBranchs.size()){
				//this.LibrarianMenu();
			}
			else if(selectedBranch>=0&&selectedBranch<=libraryBranchs.size()){
				int branchId =libraryBranchs.get(selectedBranch-1).getBranchId();
				String selectedBranchName = libraryBranchs.get(selectedBranch-1).getBranchName();
				String selectedBranchAddress = libraryBranchs.get(selectedBranch-1).getBranchAddress();
				System.out.println("You have selected "+selectedBranchName+", "+selectedBranchAddress);
			            libraryBranch.setBranchId(branchId);
	            libraryBranch.setBranchName(selectedBranchName);
			            libraryBranch.setBranchAddress(selectedBranchAddress);
		

	}
			System.out.println("Pick the Book you want to add copies of, to your branch: ");
			Book selectedBook;
			List<Book> books = administratorService.readAllBook();
	       for(Book book:books){
	           BookCopy bookCopy = librarianService.getNumOfBookCopies(book,libraryBranch);
	           if(bookCopy.getNumOfCopies()>0){
	        	   System.out.println(book.getBookId()+") "+book.getTitle());
	           }

	       }
			
			
	}
}
