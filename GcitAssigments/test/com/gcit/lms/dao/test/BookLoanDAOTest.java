/**
 * BookLoanDAOTest.java
 */
package com.gcit.lms.dao.test;

import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.LibraryBranch;

/**
 * @author kemar
 *Apr 19, 20152:42:43 AM
 */
public class BookLoanDAOTest {

	/**
	 * Test method for {@link com.gcit.lms.dao.BookLoanDAO#addBookLoan(com.gcit.lms.domain.BookLoan)}.
	 */
	@Test @Ignore
	public void testAddBookLoan() {
		List<Author> authors = new ArrayList<Author>();
		authors.add(new Author(1,"joe", null));
		authors.add(new Author(2,"kemar", null));

		Book book = new Book(1,"saw",1, authors);
	    LibraryBranch libraryBranch = new  LibraryBranch();
	    libraryBranch.setBranchId(1);
	    libraryBranch.setBranchAddress("brooklyn");
	    libraryBranch.setBranchName("branchb");
	    
	    Borrower borrower = new Borrower();
	    borrower.setCardNo(1);
	    borrower.setBorrowerName("vincent");
		borrower.setBorrowerAddress("brooklyn");
		borrower.setBorrowerPhone("6463784523");
		Date duedate = new Date(2015-06-06);
		Date outdate = new Date(2015-06-05);

		BookLoan bookLoan = new BookLoan();
		      bookLoan.setBook(book);
		      bookLoan.setBorrower(borrower);
		      bookLoan.setLibraryBranch(libraryBranch);
		      bookLoan.setDateDue(duedate);
		      
		      bookLoan.setDateOut(outdate);
		    
		      try {
				new BookLoanDAO().addBookLoan(bookLoan);
			} catch (SQLException e) {
				fail(e.getMessage());
			}
		      
	}


	/**
	 * Test method for {@link com.gcit.lms.dao.BookLoanDAO#getBookLoans()}.
	 */
	@Test 
	public void testGetBookLoans() {
		try {
			List<BookLoan> bookLoans = new BookLoanDAO().getBookLoans();
			
			for(BookLoan bookLoan:bookLoans){
				System.out.println(bookLoan.getBook().getTitle()+" "+bookLoan.getBorrower().getBorrowerName()+" "+bookLoan.getLibraryBranch().getBranchName());
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Test method for {@link com.gcit.lms.dao.BookLoanDAO#overideDueDate()}.
	 */
	@Test @Ignore
	public void testOverideDueDate() {
		fail("Not yet implemented");
	}
	

}
