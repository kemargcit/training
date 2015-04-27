/**
 * LibrarianService.java
 */
package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopy;
import com.gcit.lms.domain.LibraryBranch;

/**
 * @author kemar
 *Apr 21, 20151:43:42 PM
 */
public class LibrarianService {
	
	
	/**
	 * @return
	 * @throws SQLException 
	 */
	public List<LibraryBranch> readAll() throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		LibraryBranchDAO libraryBranchDAO = new LibraryBranchDAO(conn);

		List<LibraryBranch> libraryBranchs = libraryBranchDAO.readAll();
		return libraryBranchs;
	}

	/**
	 * @param libraryBranch
	 * @throws SQLException 
	 */
	public void updateBranch(LibraryBranch libraryBranch) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		LibraryBranchDAO libraryBranchDAO = new LibraryBranchDAO(conn);
		try{
		libraryBranchDAO.updateBranch(libraryBranch);
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
			throw e;
		}
		
	}

	/**
	 * @return
	 * @throws SQLException 
	 */
	public int getNumOfBookCopies(Book book, LibraryBranch libraryBranch) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		BookCopyDAO bookCopyDAO = new BookCopyDAO(conn);
		
		BookCopy bookCopy=bookCopyDAO.readOne(libraryBranch.getBranchId(), book.getBookId());
		   if(bookCopy==null){
			   return 0;
		   }
		   else
			return bookCopy.getNumOfCopies();
					
	
	}

	/**
	 * @param bookCopy
	 * @throws SQLException 
	 */
	public void updateBookCopy(BookCopy bookCopy) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		BookCopyDAO bookCopyDAO = new BookCopyDAO(conn);
		try {
	 		bookCopyDAO.updateBookCopies(bookCopy);
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
			throw e;
		}
	}
	
	public LibraryBranch getBranchById(int branchId) throws Exception{
		Connection conn = ConnectionUtil.getConnection();
		
		return new LibraryBranchDAO(conn).getBranchById(branchId);

	}

	public BookCopy readOne(int branchId, int bookid) throws Exception{
		Connection conn = ConnectionUtil.getConnection();

		return new BookCopyDAO(conn).readOne(branchId, bookid);
		
	}
	
	public void  addBookCopies(BookCopy bookCopy) throws Exception{
		Connection conn = ConnectionUtil.getConnection();
		BookCopyDAO bookCopyDAO = new BookCopyDAO(conn);
		try {
	 		bookCopyDAO.addBookCopies(bookCopy);
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
			throw e;
		}
	}

}
