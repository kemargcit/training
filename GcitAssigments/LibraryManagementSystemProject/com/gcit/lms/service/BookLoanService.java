/**
 * BookLoanService.java
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.domain.BookLoan;

/**
 * @author kemar
 *Apr 19, 20159:46:16 PM
 */
public class BookLoanService {
	public void  addBookLoan(BookLoan bookLoan) throws SQLException{
		new BookLoanDAO().addBookLoan(bookLoan);
	}

	public List<BookLoan> getBookLoans() throws SQLException{
		List<BookLoan> bookLoans = new BookLoanDAO().getBookLoans();
		return bookLoans;
	}

	public void overideDueDate(BookLoan bookLoan) throws SQLException{
		 new BookLoanDAO().overideDueDate(bookLoan);
	}

}
