package com.gcit.lms.client;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.LibraryBranch;
import com.gcit.lms.service.AdministratorService;
import com.gcit.lms.service.LibrarianService;


/**
 * Librarian.java
 */

/**
 * @author kemar
 *Apr 21, 20152:21:30 AM
 */
public class Librarian {

	Scanner sc = new Scanner(System.in);
	LibraryBranch libraryBranch = new LibraryBranch();
    LibrarianService librarianService = new LibrarianService();
	AdministratorService administratorService = new AdministratorService();

	public void LibrarianMenu(){

		System.out.println("****Librarian Menu****");
		int menuSelection = 0;

		System.out.println("1)Select the branch you manage \n2)Main Menu");
		menuSelection = sc.nextInt();

		switch (menuSelection) {
		case 1:
             try {
				this.librarianMenu1();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			MainMenu mainMenu = new MainMenu();
			mainMenu.mainMenuSetup();
			break;

		default:
			break;
		}
	}

/*	public void librarianMenu1(){
		//System.out.println("\n1) Enter the Branch you manage \n2)quit previous");
		int selection=sc.nextInt();
		if(selection==1){
			try {
				this.librarianMenu2();
			} catch (SQLException e) {
	// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(selection==2){
			this.LibrarianMenu();
		}

	}*/
	
	public void librarianMenu1() throws SQLException{

		List<LibraryBranch> libraryBranchs=librarianService.readAll();
		 int branchNum=1;
		  for(LibraryBranch Branch:libraryBranchs){
			  System.out.println(branchNum++ +") "+Branch.getBranchName()+", "+Branch.getBranchAddress());
		  }
		
		  System.out.println(branchNum+")back to preveios");
		  this.librarianMenu2(libraryBranchs);
		 
		

	}
		public void librarianMenu2(List<LibraryBranch> libraryBranchs ) throws SQLException{
		System.out.println("choose branch");
		
		int selectedBranch = sc.nextInt();
		if (selectedBranch-1 == libraryBranchs.size()){
			this.LibrarianMenu();
		}
		else if(selectedBranch>=0&&selectedBranch<=libraryBranchs.size()){
			int branchId =libraryBranchs.get(selectedBranch-1).getBranchId();
			String selectedBranchName = libraryBranchs.get(selectedBranch-1).getBranchName();
			String selectedBranchAddress = libraryBranchs.get(selectedBranch-1).getBranchAddress();
			System.out.println("You have selected "+selectedBranchName+", "+selectedBranchAddress);
		            libraryBranch.setBranchId(branchId);
            libraryBranch.setBranchName(selectedBranchName);
		            libraryBranch.setBranchAddress(selectedBranchAddress);
			System.out.println("1)	Update the details of the Library \n2)	Add copies of Book to the Branch "
					+ "\n3)	Quit to previous ");
			      
		            int updateAddOrQuit = sc.nextInt();
			if (updateAddOrQuit==1){
				System.out.println("enter new Library name or n/a");
				String newLibraryName = sc.next();
				if(newLibraryName.equalsIgnoreCase("n/a")){
					
				}else{
					libraryBranch.setBranchName(newLibraryName);
				}
				System.out.println("Enter new branch address or n/a");
				String newBranchAddress =sc.next();
				if(newBranchAddress.equalsIgnoreCase("n/a")){
					
				}else {
					libraryBranch.setBranchAddress(newBranchAddress);
				}
	        	librarianService.updateBranch(libraryBranch);
	        	this.librarianMenu1();
	        }else if (updateAddOrQuit==2){
	        	this.updateBookCopies(libraryBranch);
	        }else if (updateAddOrQuit==3){
	        	this.librarianMenu1();
	        }
		
		}
	}
	
	public void updateBookCopies(LibraryBranch libraryBranch) throws SQLException{
		System.out.println("Pick the Book you want to add copies of, to your branch: ");
		Book selectedBook;
		List<Book> books = administratorService.readAllBook();
		int bookNum=1;
		for(Book book : books){
			System.out.println(bookNum++ +") "+book.getBookId()+", "+book.getTitle());
		}

    	 System.out.println(" enter book number");
		int selectedBookNumber = sc.nextInt();
		int selctedBookId = books.get(selectedBookNumber-1).getBookId();
		String selectedBookTiltle = books.get(selectedBookNumber-1).getTitle();
		System.out.println("you have selected :"+selctedBookId+" "+selectedBookTiltle);
		selectedBook=administratorService.readOneBookId(selctedBookId);

		int numOfCopies=0;
		numOfCopies = librarianService.getNumOfBookCopies(selectedBook,libraryBranch).getNumOfCopies();
		System.out.println("there are "+numOfCopies+" coppies in "+libraryBranch.getBranchName());
		
		
		
		
		
	}

//	
}
