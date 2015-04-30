/**
 * BorrowerService.java
 */
package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopy;
import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.LibraryBranch;

/**
 * @author kemar
 *Apr 21, 20151:46:09 PM
 */
public class BorrowerService {
	
	public Borrower getBorrowerByCardNo(int cardNo) throws Exception{
		
		Connection conn = ConnectionUtil.getConnection();

		return new BorrowerDAO(conn).getBorrowerByCardNo(cardNo);
	}
	public List<BookLoan> readAllByBorrower(int cardNo) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		return new BookLoanDAO(conn).readAllByBorrower(cardNo);
	}
	
	public List<BookCopy> readBooksByBranch(int branchId) throws Exception{
		Connection conn = ConnectionUtil.getConnection();
		return new BookCopyDAO(conn).readBooksByBranch(branchId);
		
	}
	
	public BookCopy readOne(int branchId, int bookid) throws Exception{
		Connection conn = ConnectionUtil.getConnection();

		return new BookCopyDAO(conn).readOne(branchId, bookid);
		
	}

	public void  addBookLoan(BookLoan bookLoan) throws Exception{
		Connection conn = ConnectionUtil.getConnection();
		try {
			new BookLoanDAO(conn).addBookLoan(bookLoan);
			BookCopy bookCopy = this.readOne(bookLoan.getLibraryBranch().getBranchId(), 
					bookLoan.getBook().getBookId());
			bookCopy.setNumOfCopies(bookCopy.getNumOfCopies()-1);
		    
			new BookCopyDAO(conn).updateBookCopies(bookCopy);
			
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
			throw e;
		}
	}
	public void  deleteBookLoan(BookLoan bookLoan, LibraryBranch originlibraryBranch) throws Exception{
		Connection conn = ConnectionUtil.getConnection();
		try {
			
			
             //increase book copy at return branch
			BookCopy bookCopy = this.readOne(bookLoan.getLibraryBranch().getBranchId(), 
					bookLoan.getBook().getBookId());
			if(bookCopy==null){
				BookCopy newBookCopy = new BookCopy();
				newBookCopy.setBook(bookLoan.getBook());
				newBookCopy.setLibraryBranch(bookLoan.getLibraryBranch());
				newBookCopy.setNumOfCopies(1);
				new BookCopyDAO(conn).addBookCopies(newBookCopy);
				
			}
			else{
			bookCopy.setNumOfCopies(bookCopy.getNumOfCopies()+1);
		    
			new BookCopyDAO(conn).updateBookCopies(bookCopy);
			}
			
			//delete book loan record
			bookLoan.setLibraryBranch(originlibraryBranch);
			new BookLoanDAO(conn).deleteBookLoan(bookLoan);
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
			throw e;
		}
	}
	
	public  BookLoan readAllByBorrowerBook(Borrower borrower,Book book) throws Exception {
		Connection conn = ConnectionUtil.getConnection();

		return new BookLoanDAO(conn).readAllByBorrowerBook(borrower, book).get(0);
	}

	}



