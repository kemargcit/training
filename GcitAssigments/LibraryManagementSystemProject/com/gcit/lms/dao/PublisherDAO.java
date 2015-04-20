/**
 * PublisherDAO.java
 */
package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Publisher;

/**
 * @author kemar
 *Apr 19, 20154:11:43 PM
 */
public class PublisherDAO {

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/library", "root", "stpatrick876");
		return conn;
	}

	public void addPublisher(Publisher publisher) throws SQLException {
		Connection conn = getConnection();

		String updateQuery = "insert into tbl_publisher (publisherName,publisherAddress,publisherPhone) values (?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(updateQuery);
		pstmt.setString(1, publisher.getName());
		pstmt.setString(2, publisher.getAddress());
		pstmt.setString(3, publisher.getPhoneNumber());

		pstmt.executeUpdate();

	}
	
	public void updatePublisher(Publisher publisher) throws SQLException {
		Connection conn = getConnection();

		String updateQuery = "update tbl_publisher set publisherName = ?,publisherAddress=?, publisherPhone=? where publisherId = ?";
		PreparedStatement pstmt = conn.prepareStatement(updateQuery);
		pstmt.setString(1, publisher.getName());
		pstmt.setString(2, publisher.getAddress());
		pstmt.setString(3, publisher.getPhoneNumber());
		pstmt.setInt(4, publisher.getId());

		pstmt.executeUpdate();

	}
	public void removePublisher(Publisher publisher) throws SQLException {
		Connection conn = getConnection();

		String removeQuery = "delete from tbl_publisher where publisherId=?";
		PreparedStatement pstmt = conn.prepareStatement(removeQuery);
		pstmt.setInt(1, publisher.getId());
		pstmt.executeUpdate();

	}

	public List<Book> getListOfBooks(int publisherId){
		List<Book> books = new ArrayList<Book>();

		String select = "select tbl_book.bookId, tbl_book.title,tbl_book.pubId from tbl_book inner "
				+ "join tbl_publisher on  tbl_book.pubId=tbl_publisher.publisherId "
				+ "where tbl_book.pubId=?";
		PreparedStatement stmt;
		try {
			stmt = getConnection().prepareStatement(select);
			stmt.setInt(1, publisherId);
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

	public List<Publisher> readAll() throws SQLException {
		String select = "select * from tbl_publisher";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		ResultSet rs = stmt.executeQuery();

		List<Publisher> publisers = new ArrayList<Publisher>();
		while(rs.next()) {
			Publisher publisher = new Publisher();


			int publisherId = rs.getInt("publisherId");
			publisher.setId(publisherId);
			publisher.setName(rs.getString("publisherName"));
			publisher.setAddress(rs.getString("publisherAddress"));
			publisher.setPhoneNumber(rs.getString("publisherPhone"));

			publisher.setBooks(this.getListOfBooks(publisherId));

			publisers.add(publisher);
		}

		return publisers;
	}
	
	
	
	public Publisher getPublisherByName(String PublisherName) throws SQLException{
		String select = "select * from tbl_publisher where publisherName=?";
		PreparedStatement pstmt = getConnection().prepareStatement(select);
		pstmt.setString(1, PublisherName);

		ResultSet rs = pstmt.executeQuery();

		Publisher publisher = new Publisher();
		if(rs.next()) {
			int publisherId = rs.getInt("publisherId");
			publisher.setId(publisherId);
			publisher.setName(rs.getString("publisherName"));
			publisher.setAddress(rs.getString("publisherAddress"));
			publisher.setPhoneNumber(rs.getString("publisherPhone"));

			publisher.setBooks(this.getListOfBooks(publisherId));

		}

		return publisher;	
	}
	public Publisher getPublisherById(int publisherId) throws SQLException{
		String select = "select * from tbl_publisher where publisherId=?";
		PreparedStatement pstmt = getConnection().prepareStatement(select);
		pstmt.setInt(1, publisherId);

		ResultSet rs = pstmt.executeQuery();

		Publisher publisher = new Publisher();
		if(rs.next()) {
			publisher.setId(publisherId);
			publisher.setName(rs.getString("publisherName"));
			publisher.setAddress(rs.getString("publisherAddress"));
			publisher.setPhoneNumber(rs.getString("publisherPhone"));

			publisher.setBooks(this.getListOfBooks(publisherId));
		}

		return publisher;	
	}

}
