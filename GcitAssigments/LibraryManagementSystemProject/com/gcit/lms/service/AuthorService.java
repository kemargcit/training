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
	AuthorDAO authorDAO = new AuthorDAO();
	
	public void addAuthor(Author author) throws SQLException{
		authorDAO.addAuthor(author);
	}
	
	public void updateAuthor(Author author) throws SQLException {
		authorDAO.updateAuthor(author);
	}

	public void removeAuthor(Author author) throws SQLException{
		authorDAO.removeAuthor(author); 
	}
	
	public List<Book> getListOfBooks(int authorId) throws SQLException{
		List<Book> books = authorDAO.getListOfBooks(authorId);
		return books;
		
	}
	
	public List<Author> readAll() throws SQLException {
		List<Author> authors = authorDAO.readAll();
		return authors;
	}
	
	public Author getAuthorByName(String authorName) throws SQLException{
		
		Author author =  authorDAO.getAuthorByName(authorName);
		return author;
	}

	public Author getAuthorById(int authorId) throws SQLException{
		Author author =  authorDAO.getAuthorById(authorId);
		return author;
	}

}
