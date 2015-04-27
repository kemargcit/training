/**
 * LibrarianServiceTest.java
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.LibraryBranch;

/**
 * @author kemar
 *Apr 27, 20151:23:09 PM
 */
public class LibrarianServiceTest {

	/**
	 * Test method for {@link com.gcit.lms.service.LibrarianService#getNumOfBookCopies(com.gcit.lms.domain.Book, com.gcit.lms.domain.LibraryBranch)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetNumOfBookCopies() throws Exception {
		
		List<Book> books = new AdministratorService().readAllBook();

		LibraryBranch lBranch = new AdministratorService().getBranchById(1);
				int numOfCopy = 0;
     try {
    	 for(Book book:books){
    		 

	 numOfCopy = new LibrarianService().getNumOfBookCopies(book, lBranch);
	 
     System.out.println(book.getBookId()+" "+numOfCopy);
	 
    	 }
	} catch (Exception e) {
	}
     /*if(bookCopy==null){
    	 bookCopy.setBook(book);
    	 bookCopy.setLibraryBranch(lBranch);
    	 bookCopy.setNumOfCopies(1);
    	 
     }*/
	}

}
