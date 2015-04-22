/**
 * AuthorDAOTest2.java
 */
package com.gcit.lms.dao.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.domain.Author;

/**
 * @author kemar
 *Apr 21, 201512:34:31 AM
 */
public class AuthorDAOTest2 {
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/library", "root", "stpatrick876");
		return conn;
	}
	

	/**
	 * Test method for {@link com.gcit.lms.dao.AuthorDAO#AuthorDAO(java.sql.Connection)}.
	 */
	@Test
	public void testAuthorDAO() {
		Author author = new Author();
		author.setAuthorName("mike");
		try {
			new AuthorDAO(this.getConnection()).addAuthor(author);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Test method for {@link com.gcit.lms.dao.AuthorDAO#addAuthor(com.gcit.lms.domain.Author)}.
	 */
	@Test
	public void testAddAuthor() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.gcit.lms.dao.AuthorDAO#updateAuthor(com.gcit.lms.domain.Author)}.
	 */
	@Test
	public void testUpdateAuthor() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.gcit.lms.dao.AuthorDAO#removeAuthor(com.gcit.lms.domain.Author)}.
	 */
	@Test
	public void testRemoveAuthor() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.gcit.lms.dao.AuthorDAO#readAll()}.
	 */
	@Test
	public void testReadAll() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.gcit.lms.dao.AuthorDAO#readOne(int)}.
	 */
	@Test
	public void testReadOne() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.gcit.lms.dao.AuthorDAO#mapResults(java.sql.ResultSet)}.
	 */
	@Test
	public void testMapResultsResultSet() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.gcit.lms.dao.AuthorDAO#mapResultsFirstLevel(java.sql.ResultSet)}.
	 */
	@Test
	public void testMapResultsFirstLevelResultSet() {
		fail("Not yet implemented");
	}

}
