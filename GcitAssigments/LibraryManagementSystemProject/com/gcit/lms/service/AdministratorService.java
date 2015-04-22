/**
 * AdministratorService.java
 */
package com.gcit.lms.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.gcit.lms.client.Librarian;
import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
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
	
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/library", "root", "stpatrick876");
		return conn;
	}
	
	/**
	 * @throws SQLException 
	 * 
	 */
	public List<Book> readAllBook() throws SQLException {
		Connection conn=this.getConnection();
		BookDAO bookDAO = new BookDAO(conn);
		List<Book> books = bookDAO.readAll();
		return books;
	}

	public void addBook(Book book) throws Exception {
		Connection conn=this.getConnection();
		BookDAO bookDAO = new BookDAO(conn);
		if(book.getTitle()==null){
			throw new Exception("title cannot be empty");
		}
		bookDAO.addBook(book);		

	}
	
	public Author readOneAuthor(int authorId) throws SQLException {
		Connection conn=this.getConnection();

		Author author = new AuthorDAO(conn).readOne(authorId);
		return author;
	}

	public Author readOneByName(String authoName) throws SQLException {
		Connection conn=this.getConnection();

		Author author = new AuthorDAO(conn).readOneByName(authoName);
		return author;
	}
	
	public Publisher readOnePub(int publisherId) throws SQLException {
		Connection conn=this.getConnection();

		
		Publisher publisher = new PublisherDAO(conn).readOne(publisherId);
		return publisher;
		
		
	}
	
	public Book readOneBookId(int bookId) throws SQLException{
		Connection conn=this.getConnection();
		BookDAO bookDAO = new BookDAO(conn);
		Book book = bookDAO.readOne(bookId);
		return book;
		
	}
public List<Book> readAllByTitle(String title) throws SQLException{
	Connection conn=this.getConnection();
	BookDAO bookDAO = new BookDAO(conn);
	List<Book> books = bookDAO.readAllByTitle(title);
	return books;
	
}

public void deleteBook(Book book) throws SQLException{
	Connection conn=this.getConnection();
	BookDAO bookDAO = new BookDAO(conn);
	
	 bookDAO.removeBook(book);
}
	
/*public void updateBranch(LibraryBranch libraryBranch){
		
		sc=new Scanner(System.in);
		System.out.println("enter new Branch Name");
		String newBranchName = sc.next();
		System.out.println("enter new branch address");
		String newBranchAddress = sc.next();
		if(newBranchName.equalsIgnoreCase("n/a")){
			
		}else {
			libraryBranch.setBranchName(newBranchName);
		}
		
		if(newBranchAddress.equalsIgnoreCase("n/a")){
			
		}else {
			libraryBranch.setBranchAddress(newBranchAddress);
		}
		Connection conn;
		try {
			conn = this.getConnection();
			LibraryBranchDAO libraryBranchDAO = new LibraryBranchDAO(conn);
			libraryBranchDAO.updateBranch(libraryBranch);
			System.out.println("Branch successfully updated");
			Librarian librarian = new Librarian();
			librarian.librarianMenu2();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}*/

}
