/**
 * BorrowerDAO.java
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

import com.gcit.lms.domain.Borrower;

/**
 * @author kemar
 *Apr 19, 20152:19:57 PM
 */
public class BorrowerDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6162620077049608551L;
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/library", "root", "stpatrick876");
		return conn;
	}
	
	public void addBorrower(Borrower borrower) throws SQLException{
		Connection conn = getConnection();

		String updateQuery = "insert into tbl_borrower (cardNo,name,address,phone) values (?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(updateQuery);
		pstmt.setInt(1, borrower.getCardNo());
		pstmt.setString(2, borrower.getBorrowerName());
		pstmt.setString(3, borrower.getBorrowerAddress());
		pstmt.setString(4, borrower.getBorrowerPhone());


		pstmt.executeUpdate();

	}
	public void updateBorrower(Borrower borrower) throws SQLException {
		Connection conn = getConnection();

		String updateQuery = "update tbl_borrower set name = ? , address=?, phone=? where cardNo = ?";
		PreparedStatement pstmt = conn.prepareStatement(updateQuery);
		pstmt.setString(1, borrower.getBorrowerName());
		pstmt.setString(2, borrower.getBorrowerAddress());
		pstmt.setString(3, borrower.getBorrowerPhone());
		pstmt.setInt(4, borrower.getCardNo());

		pstmt.executeUpdate();

	}
	public void removeBorrower(Borrower borrower) throws SQLException{
		Connection conn = getConnection();

		String removeQuery = "delete from tbl_borrower where cardNo=?";
		PreparedStatement pstmt = conn.prepareStatement(removeQuery);
		pstmt.setInt(1, borrower.getCardNo());
		pstmt.executeUpdate();
	}


	public List<Borrower> readAll() throws SQLException {
		String select = "select * from tbl_borrower";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		ResultSet rs = stmt.executeQuery();
		List<Borrower> borrowers = new ArrayList<Borrower>();
		while(rs.next()) {
			Borrower borrower = new Borrower();
		  borrower.setCardNo(rs.getInt("cardNo"));
		  borrower.setBorrowerName(rs.getString("name"));
		  borrower.setBorrowerAddress(rs.getString("address"));
		  borrower.setBorrowerPhone(rs.getString("phone"));
			borrowers.add(borrower);
		}
		

		return borrowers;
	}
	public Borrower getBorrowerByName(String borrowerName) throws SQLException{
		String select = "select * from tbl_borrower where name=?";
		PreparedStatement pstmt = getConnection().prepareStatement(select);
		pstmt.setString(1, borrowerName);

		ResultSet rs = pstmt.executeQuery();

		Borrower borrower = new Borrower();
		if(rs.next()) {
			  borrower.setCardNo(rs.getInt("cardNo"));
			  borrower.setBorrowerName(rs.getString("name"));
			  borrower.setBorrowerAddress(rs.getString("address"));
			  borrower.setBorrowerPhone(rs.getString("phone"));
		}

		return borrower;	
	}
	public Borrower getBorrowerByCardNo(int cardNo) throws SQLException{
		String select = "select * from tbl_borrower where cardNo=?";
		PreparedStatement pstmt = getConnection().prepareStatement(select);
		pstmt.setInt(1, cardNo);

		ResultSet rs = pstmt.executeQuery();

		Borrower borrower = new Borrower();
		if(rs.next()) {
			  borrower.setCardNo(rs.getInt("cardNo"));
			  borrower.setBorrowerName(rs.getString("name"));
			  borrower.setBorrowerAddress(rs.getString("address"));
			  borrower.setBorrowerPhone(rs.getString("phone"));

		}

		return borrower;	
	}

}
