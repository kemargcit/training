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



public class AuthorDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1619700647002508164L;

	public void addAuthor(Author author) throws SQLException {
		Connection conn = getConnection();

		String updateQuery = "insert into tbl_author (authorName) values (?)";
		PreparedStatement pstmt = conn.prepareStatement(updateQuery);
		pstmt.setString(1, author.getAuthorName());
		pstmt.executeUpdate();
		conn.close();
		pstmt.close();
		
		

	}

	public void updateAuthor(Author author) throws SQLException {
		Connection conn = getConnection();

		String updateQuery = "update tbl_author set authorName = ? where authorId = ?";
		PreparedStatement pstmt = conn.prepareStatement(updateQuery);
		pstmt.setString(1, author.getAuthorName());
		pstmt.setInt(2, author.getAuthorId());
		pstmt.executeUpdate();
		conn.close();
		pstmt.close();

	}

	public void removeAuthor(Author author) throws SQLException {
		Connection conn = getConnection();

		String removeQuery = "delete from tbl_author where authorId=?";
		PreparedStatement pstmt = conn.prepareStatement(removeQuery);
		pstmt.setInt(1, author.getAuthorId());
		pstmt.executeUpdate();
		conn.close();
		pstmt.close();
	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/library", "root", "stpatrick876");
		return conn;
	}
	public List<Book> getListOfBooks(int authorId) throws SQLException{
		List<Book> books = new ArrayList<Book>();

		String select = "select tbl_book.bookId, tbl_book.title,tbl_book.pubId from tbl_book inner "
				+ "join tbl_book_authors on  tbl_book.bookId=tbl_book_authors.bookId "
				+ "inner join tbl_author "
				+ "on tbl_book_authors.authorId=tbl_author.authorId "
				+ "where tbl_author.authorId=?";
		Connection conn = getConnection();

		PreparedStatement pstmt = conn.prepareStatement(select);

		try {
			pstmt.setInt(1, authorId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("BookId"));
				book.setTitle(rs.getString("title"));
				book.setPubId(rs.getInt("pubId"));
				books.add(book);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.close();
			pstmt.close();
		}
		return books;
		
	}

	public List<Author> readAll() throws SQLException {
		String select = "select * from tbl_author";
		
		Connection conn = getConnection();

		PreparedStatement stmt = conn.prepareStatement(select);
		ResultSet rs = stmt.executeQuery();

		List<Author> authors = new ArrayList<Author>();
		while(rs.next()) {
			Author author = new Author();
			int authorId = rs.getInt("authorId");
			author.setAuthorId(authorId);
			author.setAuthorName(rs.getString("authorName"));
         	author.setBooks(this.getListOfBooks(authorId));

			authors.add(author);
		}
         conn.close();
         stmt.close();
		return authors;
	}

	public Author getAuthorByName(String authorName) throws SQLException{
		String select = "select * from tbl_author where authorName=?";
		Connection conn = getConnection();

		PreparedStatement pstmt =conn.prepareStatement(select);
		pstmt.setString(1, authorName);

		ResultSet rs = pstmt.executeQuery();

		Author author = new Author();
		if(rs.next()) {
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));

		}
         conn.close();
         pstmt.close();
		return author;	
	}
	public Author getAuthorById(int authorId) throws SQLException{
		String select = "select * from tbl_author where authorId=?";
		Connection conn = getConnection();

		PreparedStatement pstmt = conn.prepareStatement(select);
		pstmt.setInt(1, authorId);

		ResultSet rs = pstmt.executeQuery();

		Author author = new Author();
		if(rs.next()) {
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));

		}
      conn.close();
      pstmt.close();
		return author;	
	}

}
