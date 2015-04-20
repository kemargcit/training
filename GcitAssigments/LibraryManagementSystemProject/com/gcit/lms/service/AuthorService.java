/**
 * AuthorService.java
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;

/**
 * @author kemar
 *Apr 19, 20155:21:59 PM
 */
public class AuthorService {
	
	public void addAuthor(Author author) throws SQLException{
		new AuthorDAO().addAuthor(author);
	}
	
	public void updateAuthor(Author author) throws SQLException {
		new AuthorDAO().updateAuthor(author);
	}

	public void removeAuthor(Author author) throws SQLException{
		new AuthorDAO().removeAuthor(author); 
	}
	
	public List<Book> getListOfBooks(int authorId) throws SQLException{
		List<Book> books = new AuthorDAO().getListOfBooks(authorId);
		return books;
		
	}
	
	public List<Author> readAll() throws SQLException {
		List<Author> authors = new AuthorDAO().readAll();
		return authors;
	}
	
	public Author getAuthorByName(String authorName) throws SQLException{
		
		Author author =  new AuthorDAO().getAuthorByName(authorName);
		return author;
	}

	public Author getAuthorById(int authorId) throws SQLException{
		Author author =  new AuthorDAO().getAuthorById(authorId);
		return author;
	}

}
