/**
 * BookDAO.java
 */
package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;

/**
 * @author kemar
 *Apr 18, 201511:02:51 PM
 */
public class BookDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -355606829406268552L;
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/library", "root", "stpatrick876");
		return conn;
	}
	/**
	 * adds a new book to the book table 
	 * @param book a book object
	 * @throws SQLException
	 */
	public void addBook(Book book) throws SQLException {
		Connection conn = getConnection();

		String updateQuery = "insert into tbl_book (title,pubId) values (?,?)";
		PreparedStatement pstmt = conn.prepareStatement(updateQuery);
		pstmt.setString(1, book.getTitle());
		pstmt.setInt(2, book.getPubId());		
		pstmt.executeUpdate();

	}

	public void updateBook(Book book) throws SQLException {
		Connection conn = getConnection();

		String updateQuery = "update tbl_book set title = ?,pubId = ? where bookId = ?";
		PreparedStatement pstmt = conn.prepareStatement(updateQuery);
		pstmt.setString(1, book.getTitle());
		pstmt.setInt(2, book.getPubId());
		pstmt.setInt(3, book.getBookId());
		pstmt.executeUpdate();

	}

	public void removeBook(Book book) throws SQLException {
		Connection conn = getConnection();

		String removeQuery = "delete from tbl_book where bookId=?";
		PreparedStatement pstmt = conn.prepareStatement(removeQuery);
		pstmt.setInt(1, book.getBookId());
		pstmt.executeUpdate();

	}
	public List<Author> getListOfAuthors(int bookId){
		List<Author> authors = new ArrayList<Author>();

		String select = "select tbl_author.authorId, tbl_author.authorName from tbl_book inner "
				+ "join tbl_book_authors on  tbl_book.bookId=tbl_book_authors.bookId "
				+ "inner join tbl_author "
				+ "on tbl_book_authors.authorId=tbl_author.authorId "
				+ "where tbl_book.bookId=?";
		PreparedStatement stmt;
		try {
			stmt = getConnection().prepareStatement(select);
			stmt.setInt(1, bookId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Author author = new Author();
				author.setAuthorId(rs.getInt("authorId"));
				author.setAuthorName(rs.getString("authorName"));
				
				authors.add(author);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return authors;
		
	}
	public List<Book> readAll() throws SQLException {
		String select = "select * from tbl_book";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		ResultSet rs = stmt.executeQuery();
		List<Book> books = new ArrayList<Book>();
		while(rs.next()) {
			Book book = new Book();
			int bookId = rs.getInt("bookId");
		    book.setBookId(bookId);
		book.setTitle(rs.getString("title"));
		book.setPubId(rs.getInt("pubId"));
         	book.setAuthors(this.getListOfAuthors(bookId));
              
			books.add(book);
		}
		

		return books;
	}
	
	
	public Book getBookById(int bookId) throws SQLException{
		String select = "select * from tbl_book where bookId=?";
		Connection conn = getConnection();

		PreparedStatement pstmt = conn.prepareStatement(select);
		pstmt.setInt(1, bookId);

		ResultSet rs = pstmt.executeQuery();

		Book book = new Book();
		if(rs.next()) {
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			book.setPubId(rs.getInt("pubId"));
			
		}
      conn.close();
      pstmt.close();
		return book;
		
	}
	public Book getBookByTitle(String title) throws SQLException{
		String select = "select * from tbl_book where title=?";
		Connection conn = getConnection();

		PreparedStatement pstmt = conn.prepareStatement(select);
		pstmt.setString(1, title);

		ResultSet rs = pstmt.executeQuery();

		Book book = new Book();
		if(rs.next()) {
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			book.setPubId(rs.getInt("pubId"));
			
		}
      conn.close();
      pstmt.close();
		return book;
		
	}

}
