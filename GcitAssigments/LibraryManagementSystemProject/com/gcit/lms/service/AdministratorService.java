/**
 * AdministratorService.java
 */
package com.gcit.lms.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Publisher;

/**
 * @author kemar
 *Apr 21, 20151:43:20 PM
 */
public class AdministratorService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8512347783731278797L;
	
	
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * 
	 */
	public List<Book> readAllBook() throws SQLException, ClassNotFoundException {
		BookDAO bookDAO = new BookDAO(ConnectionUtil.getConnection());
		List<Book> books = bookDAO.readAll();
		return books;
	}

	public void addBook(Book book) throws Exception , ClassNotFoundException {
		
		Connection conn = ConnectionUtil.getConnection();
		try {
			new BookDAO(conn).addBook(book);
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
			throw e;
		}
	}
	public Author readOneAuthor(int authorId) throws SQLException, ClassNotFoundException {
		Author author = new AuthorDAO(ConnectionUtil.getConnection()).readOneByAuthorId(authorId);
		return author;
	}

	public Author readOneByName(String authoName) throws SQLException, ClassNotFoundException {
		Author author = new AuthorDAO(ConnectionUtil.getConnection()).readOneByAuthorName(authoName);
		return author;
	}
	

	
	public Book readOneBookId(int bookId) throws SQLException, ClassNotFoundException{
		BookDAO bookDAO = new BookDAO(ConnectionUtil.getConnection());
		Book book = bookDAO.readOne(bookId);
		return book;
		
	}
public List<Book> readAllByTitle(String title) throws SQLException, ClassNotFoundException{
	BookDAO bookDAO = new BookDAO(ConnectionUtil.getConnection());
	List<Book> books = bookDAO.readAllByTitle(title);
	return books;
	
}

public void deleteBook(Book book) throws SQLException, ClassNotFoundException{
	BookDAO bookDAO = new BookDAO(ConnectionUtil.getConnection());
	
	 bookDAO.removeBook(book);
}
public void addAuthor(Author author) throws SQLException, ClassNotFoundException {
	Connection conn = ConnectionUtil.getConnection();
	try {
		new AuthorDAO(conn).addAuthor(author);
		conn.commit();
	} catch(Exception e) {
		conn.rollback();
		throw e;
	}
}
public List<Author> readAllAuthors() throws SQLException, ClassNotFoundException {
	Connection conn = ConnectionUtil.getConnection();
	List<Author> authors=new AuthorDAO(conn).readAll();
	
	return authors;
	
}
public Publisher readOnePubById(int publisherId) throws SQLException, ClassNotFoundException {
	Connection conn = ConnectionUtil.getConnection();
   Publisher publisher= new PublisherDAO(conn).readOnePubById(publisherId);
	return publisher;
	
}

public void addPublisher(Publisher publisher) throws SQLException, ClassNotFoundException {
	Connection conn = ConnectionUtil.getConnection();
	new PublisherDAO(conn).addPublisher(publisher);

}



}
