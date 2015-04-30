/**
 * AdministratorService.java
 */
package com.gcit.lms.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.LibraryBranch;
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
	Connection conn = ConnectionUtil.getConnection();
	try {
		new BookDAO(conn).removeBook(book);
		conn.commit();
	} catch(Exception e) {
		conn.rollback();
		throw e;
	}
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
	List<Author> authors=new AuthorDAO(conn).readAll(1,10);
	
	return authors;
	
}
public Publisher readOnePubById(int publisherId) throws SQLException, ClassNotFoundException {
	Connection conn = ConnectionUtil.getConnection();
   Publisher publisher= new PublisherDAO(conn).readOnePubById(publisherId);
	return publisher;
	
}

public void addPublisher(Publisher publisher) throws SQLException, ClassNotFoundException {
	Connection conn = ConnectionUtil.getConnection();
	try {
		new PublisherDAO(conn).addPublisher(publisher);
		conn.commit();
	} catch(Exception e) {
		conn.rollback();
		throw e;
	}

}

public void addBorrower(Borrower borrower) throws SQLException, ClassNotFoundException{
	Connection conn = ConnectionUtil.getConnection();
	try {
		new BorrowerDAO(conn).addBorrower(borrower);
		conn.commit();
	} catch(Exception e) {
		conn.rollback();
		throw e;
	}
}

public List<Publisher> readAllPublihers() throws SQLException, ClassNotFoundException {
	Connection conn = ConnectionUtil.getConnection();
	   List<Publisher> publishers= new PublisherDAO(conn).readAll();
		return publishers;
}
public List<Genre> readAllGenres() throws SQLException, ClassNotFoundException {
	Connection conn = ConnectionUtil.getConnection();
	   List<Genre> genres= new GenreDAO(conn).readAllGenre();
		return genres;
}

public List<Borrower> readAllBorrowers() throws SQLException, ClassNotFoundException {
	Connection conn = ConnectionUtil.getConnection();
List<Borrower> borrowers = new BorrowerDAO(conn).readAll();
return borrowers;
}

public void removeAuthor(Author author) throws SQLException, ClassNotFoundException {
	Connection conn = ConnectionUtil.getConnection();
	try {
		new AuthorDAO(conn).removeAuthor(author);
		conn.commit();
	} catch(Exception e) {
		conn.rollback();
		throw e;
	}

}
public void updateAuthor(Author author) throws SQLException, ClassNotFoundException {
	Connection conn = ConnectionUtil.getConnection();
	try {
		new AuthorDAO(conn).updateAuthor(author);
		conn.commit();
	} catch(Exception e) {
		conn.rollback();
		throw e;
	}
	
}

public Genre getGenreById(int genreId) throws Exception{
	Connection conn = ConnectionUtil.getConnection();
	Genre genre = new GenreDAO(conn).getGenreById(genreId);
	return genre;
}
public void removePublisher(Publisher publisher) throws Exception {
	Connection conn = ConnectionUtil.getConnection();
	try {
		new PublisherDAO(conn).removePublisher(publisher);
		conn.commit();
	} catch(Exception e) {
		conn.rollback();
		throw e;
	}
}
public void updatePublisher(Publisher publisher) throws Exception {
	Connection conn = ConnectionUtil.getConnection();
	try {
		new PublisherDAO(conn).updatePublisher(publisher);
		conn.commit();
	} catch(Exception e) {
		conn.rollback();
		throw e;
	}
}

public void removeBorrower(Borrower borrower) throws Exception{
	Connection conn = ConnectionUtil.getConnection();
	try {
		new BorrowerDAO(conn).removeBorrower(borrower);
		conn.commit();
	} catch(Exception e) {
		conn.rollback();
		throw e;
	}
}

public void updateBorrower(Borrower borrower) throws Exception {
	Connection conn = ConnectionUtil.getConnection();
	try {
		new BorrowerDAO(conn).updateBorrower(borrower);
		conn.commit();
	} catch(Exception e) {
		conn.rollback();
		throw e;
	}
}

public List<BookLoan> readAllBookLoan() throws Exception {
	Connection conn = ConnectionUtil.getConnection();

	return new BookLoanDAO(conn).readAll();
	
}
public LibraryBranch getBranchById(int branchId) throws Exception{
	Connection conn = ConnectionUtil.getConnection();

	return new LibraryBranchDAO(conn).getBranchById(branchId);
}

public Borrower getBorrowerByCardNo(int cardNo) throws Exception{
	Connection conn = ConnectionUtil.getConnection();

	return new BorrowerDAO(conn).getBorrowerByCardNo(cardNo);
}
public void overideDueDate(BookLoan bookLoan) throws Exception{
	Connection conn = ConnectionUtil.getConnection();
	try {
		new BookLoanDAO(conn).overideDueDate(bookLoan);
		conn.commit();
	} catch(Exception e) {
		conn.rollback();
		throw e;
	}
}
public List<Author> getAuthors() throws Exception {
	AuthorDAO aDAO = new AuthorDAO(ConnectionUtil.getConnection());

	return aDAO.readAll(1,10);
}

public int getAuthorCount() throws Exception{
	Connection conn = ConnectionUtil.getConnection();
 
	return new AuthorDAO(conn).readAllCount();
}

public List<Author> searchAuthorByName(String searchString,int pageNo, int pageSize ) throws Exception {
	AuthorDAO aDAO = new AuthorDAO(ConnectionUtil.getConnection());
	aDAO.setPageNo(pageNo);
	aDAO.setPageSize(pageSize);
	
	return  aDAO.searchAuthorByName(searchString, pageNo, pageSize);
}
public int searchAuthorCount(String searchString) throws Exception{
	Connection conn = ConnectionUtil.getConnection();
	return new AuthorDAO(conn).searchAuthoryNameCount(searchString);
	
}

	
}
