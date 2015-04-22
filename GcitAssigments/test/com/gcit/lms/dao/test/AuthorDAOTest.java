/**
 * AuthorDAOTest.java
 */
package com.gcit.lms.dao.test;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;

/**
 * @author kemar
 *Apr 18, 201510:37:19 PM
 */
public class AuthorDAOTest {

	/**
	 * Test method for {@link com.gcit.lms.dao.AuthorDAO#addAuthor(com.gcit.lms.domain.Author)}.
	 */
/*	@Test
	public void testAddAuthor() {Author a = new Author();
	a.setAuthorName("Testx");
	try {
		new AuthorDAO().addAuthor(a);
	} catch(Exception e) {
		fail(e.getMessage());
	}
	}

	*//**
	 * Test method for {@link com.gcit.lms.dao.AuthorDAO#updateAuthor(com.gcit.lms.domain.Author)}.
	 *//*
	@Test
	public void testUpdateAuthor() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link com.gcit.lms.dao.AuthorDAO#removeAuthor(com.gcit.lms.domain.Author)}.
	 *//*
	@Test
	public void testRemoveAuthor() {
		fail("Not yet implemented");
	}
*/
	/**
	 * Test method for {@link com.gcit.lms.dao.AuthorDAO#readAll()}.
	 */
	@Test
	public void testReadAll() {
		//java.util.List<Author> authors = new ArrayList<Author>();
//		try {
//			authors=new AuthorDAO().readAll();
//			System.out.println(authors.get(0).getAuthorName());
//			} catch(Exception e) {
//			fail(e.getMessage());
//		}	
		
		java.util.List<Author> authorList = new ArrayList<Author>();

		try {

			authorList = new AuthorDAO(null).readAll();
		//	for (Book b2 : bookList) {
				java.util.List<Book> a = authorList.get(0).getBooks();
				for (Book a2 : a) {
					System.out.println(a2.getTitle());
				}
		//	}
		} catch (SQLException e) {
			fail(e.getMessage());

		}
	}
}
/*
 * 
	*//**
	 * Test method for {@link com.gcit.lms.dao.AuthorDAO#getAuthorByName(java.lang.String)}.
	 *//*
	@Test
	public void testGetAuthorByName() {
		Author author = new Author();
		try {
			author=new AuthorDAO().getAuthorByName("joe");
			assertEquals( 1, author.getAuthorId());
		} catch (SQLException e) {
			fail(e.getMessage());

		}
		
	}

	*//**
	 * Test method for {@link com.gcit.lms.dao.AuthorDAO#getAuthorById(int)}.
	 *//*
	@Test
	public void testGetAuthorById() {
		fail("Not yet implemented");
	}

}
*/