/**
 * GenreDAO.java
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

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;

/**
 * @author kemar
 *Apr 19, 20153:20:54 PM
 */
public class GenreDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2688793748275673863L;
	
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/library", "root", "stpatrick876");
		return conn;
	}
	
	
	public void addGenre(Genre genre) throws SQLException {
		Connection conn = getConnection();

		String updateQuery = "insert into tbl_genre (genre_name) values (?)";
		PreparedStatement pstmt = conn.prepareStatement(updateQuery);
		pstmt.setString(1, genre.getName());
		pstmt.executeUpdate();

	}
	
	public void updateGenre(Genre genre) throws SQLException {
		Connection conn = getConnection();

		String updateQuery = "update tbl_genre set genre_name = ? where genre_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(updateQuery);
		pstmt.setInt(1, genre.getGenreId());
		pstmt.setString(2, genre.getName());
		pstmt.executeUpdate();

	}
	public void removeGenre(Genre genre) throws SQLException {
		Connection conn = getConnection();

		String removeQuery = "delete from tbl_genre where genre_id=?";
		PreparedStatement pstmt = conn.prepareStatement(removeQuery);
		pstmt.setInt(1, genre.getGenreId());
		pstmt.executeUpdate();

	}
	
	public List<Book> getListOfBooks(int genreId){
		List<Book> books = new ArrayList<Book>();

		String select = "select tbl_book.bookId, tbl_book.title,tbl_book.pubId from tbl_book inner "
				+ "join tbl_book_genre on  tbl_book.bookId=tbl_book_genre.bookId "
				+ "inner join tbl_genre "
				+ "on tbl_book_genre.genre_id=tbl_genre.genre_id "
				+ "where tbl_genre.genre_id=?";
		PreparedStatement stmt;
		try {
			stmt = getConnection().prepareStatement(select);
			stmt.setInt(1, genreId);
			ResultSet rs = stmt.executeQuery();
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
		}

		return books;
		
	}
	public List<Genre> readAllGenre() throws SQLException {
		String select = "select * from tbl_genre";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		ResultSet rs = stmt.executeQuery();

		List<Genre> genres = new ArrayList<Genre>();
		while(rs.next()) {
			Genre genre = new Genre();
			int genreId = rs.getInt("genre_id");
			genre.setGenreId(genreId);
			
			genre.setName(rs.getString("genre_name"));
			
         	genre.setBooks(this.getListOfBooks(genreId));

			genres.add(genre);
		}

		return genres;
	}

	
	
	public Genre getGenreByName(String genreName) throws SQLException{
		String select = "select * from tbl_genre where genre_name =?";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		ResultSet rs = stmt.executeQuery();
		Genre genre = new Genre();

		if (rs.next()){
			int genreId = rs.getInt("genre_id");
			genre.setGenreId(genreId);
			
			genre.setName(rs.getString("genre_name"));
			
         	genre.setBooks(this.getListOfBooks(genreId));

			
		}

		
		
		return genre;
		
		
	}
	public Genre getGenreById(int genreId) throws SQLException{
		String select = "select * from tbl_genre where genre_id =?";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		ResultSet rs = stmt.executeQuery();
		Genre genre = new Genre();

		if (rs.next()){
			genre.setGenreId(genreId);
			
			genre.setName(rs.getString("genre_name"));
			
         	genre.setBooks(this.getListOfBooks(genreId));

			
		}

		
		
		return genre;
		
		
	}
}
