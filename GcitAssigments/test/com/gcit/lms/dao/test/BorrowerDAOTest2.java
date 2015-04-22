/**
 * BorrowerDAOTest2.java
 */
package com.gcit.lms.dao.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.domain.Borrower;

/**
 * @author kemar
 *Apr 21, 201512:18:16 AM
 */
public class BorrowerDAOTest2 {

	/**
	 * Test method for {@link com.gcit.lms.dao.BorrowerDAO#mapResultsFirstLevel(java.sql.ResultSet)}.
	 */
	@Test @Ignore
	public void testMapResultsFirstLevel() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.gcit.lms.dao.BorrowerDAO#BorrowerDAO(java.sql.Connection)}.
	 */
	@Test @Ignore
	public void testBorrowerDAO() {
		fail("Not yet implemented");
	}
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/library", "root", "stpatrick876");
		return conn;
	}
	

	/**
	 * Test method for {@link com.gcit.lms.dao.BorrowerDAO#addBorrower(com.gcit.lms.domain.Borrower)}.
	 */
	@Test
	public void testAddBorrower() {
		Borrower borrower = new Borrower();
		borrower.setBorrowerName("royal");
		borrower.setBorrowerAddress("queens");
		borrower.setBorrowerPhone("84526412548");
		borrower.setCardNo(3);
		try {
			new BorrowerDAO(this.getConnection()).addBorrower(borrower);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
		
	}

	/**
	 * Test method for {@link com.gcit.lms.dao.BorrowerDAO#updateBorrower(com.gcit.lms.domain.Borrower)}.
	 */
	@Test
	public void testUpdateBorrower() {
		Borrower borrower = new Borrower();
		borrower.setBorrowerName("royal");
		borrower.setBorrowerAddress("queens");
		borrower.setBorrowerPhone("84526412548");
		borrower.setCardNo(3);
		try {
			new BorrowerDAO(this.getConnection()).updateBorrower(borrower);
		} catch (SQLException e) {
			fail(e.getMessage());
		}	}

	/**
	 * Test method for {@link com.gcit.lms.dao.BorrowerDAO#removeBorrower(com.gcit.lms.domain.Borrower)}.
	 */
	@Test
	public void testRemoveBorrower() {
		Borrower borrower = new Borrower();
		borrower.setBorrowerName("royal");
		borrower.setBorrowerAddress("queens");
		borrower.setBorrowerPhone("84526412548");
		borrower.setCardNo(3);
		try {
			new BorrowerDAO(this.getConnection()).removeBorrower(borrower);
		} catch (SQLException e) {
			fail(e.getMessage());
		}	
	}

	/**
	 * Test method for {@link com.gcit.lms.dao.BorrowerDAO#readAll()}.
	 */
	@Test
	public void testReadAll() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.gcit.lms.dao.BorrowerDAO#getBorrowerByName(java.lang.String)}.
	 */
	@Test
	public void testGetBorrowerByName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.gcit.lms.dao.BorrowerDAO#getBorrowerByCardNo(int)}.
	 */
	@Test
	public void testGetBorrowerByCardNo() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.gcit.lms.dao.BorrowerDAO#mapResults(java.sql.ResultSet)}.
	 */
	@Test
	public void testMapResultsResultSet() {
		fail("Not yet implemented");
	}

}
