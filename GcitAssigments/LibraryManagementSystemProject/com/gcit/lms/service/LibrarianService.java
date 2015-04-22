/**
 * LibrarianService.java
 */
package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopy;
import com.gcit.lms.domain.LibraryBranch;

/**
 * @author kemar
 *Apr 21, 20151:43:42 PM
 */
public class LibrarianService {
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/library", "root", "stpatrick876");
		return conn;
	}
	
	/**
	 * @return
	 * @throws SQLException 
	 */
	public List<LibraryBranch> readAll() throws SQLException {
		Connection conn=this.getConnection();
		LibraryBranchDAO libraryBranchDAO = new LibraryBranchDAO(conn);

		List<LibraryBranch> libraryBranchs = libraryBranchDAO.readAll();
		return libraryBranchs;
	}

	/**
	 * @param libraryBranch
	 * @throws SQLException 
	 */
	public void updateBranch(LibraryBranch libraryBranch) throws SQLException {
		Connection conn=this.getConnection();
		LibraryBranchDAO libraryBranchDAO = new LibraryBranchDAO(conn);
		try{
		libraryBranchDAO.updateBranch(libraryBranch);
		System.out.println("branch updated");
		
		}catch(Exception e){
			System.out.println("branch  update failed");

		}
		
	}

	/**
	 * @return
	 * @throws SQLException 
	 */
	public BookCopy getNumOfBookCopies(Book book, LibraryBranch libraryBranch) throws SQLException {
		Connection conn=this.getConnection();
		BookCopyDAO bookCopyDAO = new BookCopyDAO(conn);
		BookCopy bookCopy=bookCopyDAO.readOne(libraryBranch.getBranchId(), book.getBookId());
		
		
		return bookCopy;
	}

}
