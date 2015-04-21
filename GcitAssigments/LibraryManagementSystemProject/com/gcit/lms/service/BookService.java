/**
 * BookService.java
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;

/**
 * @author kemar
 *Apr 19, 20159:36:24 PM
 */
public class BookService {
	BookDAO bookDAO = new BookDAO();
	public void addBook(Book book) throws SQLException {
		bookDAO.addBook(book);
	}

	public void updateBook(Book book) throws SQLException {	
		bookDAO.updateBook(book);
	}
	public void removeBook(Book book) throws SQLException {
		bookDAO.removeBook(book);
	}
	public List<Author> getListOfAuthors(int bookId){
		List<Author> author = bookDAO.getListOfAuthors(bookId);
		return author;
	}
	public List<Book> readAll() throws SQLException {
		 List<Book>  book = bookDAO.readAll();
		return book;
	}
	
	public Book getBookById(int bookId) throws SQLException{
		Book book = bookDAO.getBookById(bookId);
		return book;
	}

	public Book getBookByTitle(String title) throws SQLException{
		Book book = bookDAO.getBookByTitle(title);
		return book;
	}

	
	
}
