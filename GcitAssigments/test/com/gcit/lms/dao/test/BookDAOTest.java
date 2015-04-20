/**
 * BookDAOTest.java
 */
package com.gcit.lms.dao.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;

/**
 * @author kemar
 *Apr 18, 201511:21:52 PM
 */
public class BookDAOTest {

	/**
	 * Test method for {@link com.gcit.lms.dao.BookDAO#addBook(com.gcit.lms.domain.Book)}.
	 */
	/*@Test
	public void testAddBook() {
		Book book = new Book();
		book.setTitle("vegas");
		book.setPubId(4);
		try {
			new BookDAO().addBook(book);
		} catch (SQLException e) {
			fail(e.getMessage());

		}
		
	}*/
	/*@Test
	public void testUpdateBook() {
		Book book = new Book();
book.setBookId(17);
book.setTitle("vegas");
book.setPubId(5);
		try {
			new BookDAO().updateBook(book);
		} catch (SQLException e) {
			fail(e.getMessage());

		}
		
	}*/
	/*@Test
	public void testDeleteBook() {
		Book book = new Book();
book.setBookId(17);

		try {
			new BookDAO().removeBook(book);;
		} catch (SQLException e) {
			fail(e.getMessage());

		}
		
	}*/
	
	@Test
	public void testReadAll() {

		List<Book> bookList = new ArrayList<Book>();

		try {

			bookList = new BookDAO().readAll();
		//	for (Book b2 : bookList) {
				List<Author> a = bookList.get(0).getAuthors();
				for (Author a2 : a) {
					System.out.println(a2.getAuthorName());
				}
		//	}
		} catch (SQLException e) {
			fail(e.getMessage());

		}

	}
}
