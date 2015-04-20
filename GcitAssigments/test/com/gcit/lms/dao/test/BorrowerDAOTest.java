/**
 * BorrowerDAOTest.java
 */
package com.gcit.lms.dao.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.domain.Borrower;

/**
 * @author kemar
 *Apr 19, 20152:43:58 PM
 */
public class BorrowerDAOTest {

	/**
	 * Test method for {@link com.gcit.lms.dao.BorrowerDAO#addBorrower(com.gcit.lms.domain.Borrower)}.
	 */
	/*@Test
	public void testAddBorrower() {
		Borrower borrower = new Borrower();
		borrower.setCardNo(9);
		borrower.setBorrowerName("newBorrower");
		borrower.setBorrowerAddress("mclean");
		borrower.setBorrowerPhone("9542365425");

		try {
			new BorrowerDAO().addBorrower(borrower);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

	/**
	 * Test method for {@link com.gcit.lms.dao.BorrowerDAO#removeBorrower(com.gcit.lms.domain.Borrower)}.
	 *//*
	@Test
	public void testRemoveBorrower() {

		Borrower borrower = new Borrower();
		borrower.setCardNo(9);
		borrower.setBorrowerName("newBorrower");
		borrower.setBorrowerAddress("mclean");
		borrower.setBorrowerPhone("9542365425");

		try {
			new BorrowerDAO().removeBorrower(borrower);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
	}*/
	 
		@Test
		public void testUpdateBorrower() {

			Borrower borrower = new Borrower();
			borrower.setCardNo(8);
			borrower.setBorrowerName("newBorrower");
			borrower.setBorrowerAddress("mclean2");
			borrower.setBorrowerPhone("9542365425");

			try {
				new BorrowerDAO().updateBorrower(borrower);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				fail(e.getMessage());
			}
		}
}
