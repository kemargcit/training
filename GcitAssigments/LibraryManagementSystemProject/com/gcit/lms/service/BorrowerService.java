/**
 * BorrowerService.java
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.domain.Borrower;

/**
 * @author kemar
 *Apr 19, 20159:52:06 PM
 */
public class BorrowerService {
	public void addBorrower(Borrower borrower) throws SQLException{
		new BorrowerDAO().addBorrower(borrower);
	}

	
	public void updateBorrower(Borrower borrower) throws SQLException {
		new BorrowerDAO().updateBorrower(borrower);
	}

	
	public void removeBorrower(Borrower borrower) throws SQLException{
		new BorrowerDAO().removeBorrower(borrower);
	}
	public List<Borrower> readAll() throws SQLException {
		List<Borrower> borrowers = new BorrowerDAO().readAll();
		return borrowers;
	}
	public Borrower getBorrowerByName(String borrowerName) throws SQLException{
		Borrower borrower = new BorrowerDAO().getBorrowerByName(borrowerName);
		return borrower;
	}
	public Borrower getBorrowerByCardNo(int cardNo) throws SQLException{
		Borrower borrower = new BorrowerDAO().getBorrowerByCardNo(cardNo);
		return borrower;

	}


}
