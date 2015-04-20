/**
 * LibraryBranchDAO.java
 */
package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.LibraryBranch;

/**
 * @author kemar
 *Apr 19, 20153:45:26 PM
 */
public class LibraryBranchDAO {
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/library", "root", "stpatrick876");
		return conn;
	}
	public void addBranch(LibraryBranch libraryBranch) throws SQLException {
		Connection conn = getConnection();

		String updateQuery = "insert into tbl_library_branch (branchName,branchAddress) values (?,?)";
		PreparedStatement pstmt = conn.prepareStatement(updateQuery);
		pstmt.setString(1, libraryBranch.getBranchName());
		pstmt.setString(2, libraryBranch.getBranchAddress());
		pstmt.executeUpdate();

	}


	public void updateBranch(LibraryBranch libraryBranch) throws SQLException {
		Connection conn = getConnection();

		String updateQuery = "update tbl_library_branch set branchName = ?,branchAddress =? where branchId = ?";
		PreparedStatement pstmt = conn.prepareStatement(updateQuery);
		pstmt.setString(1, libraryBranch.getBranchName());
		pstmt.setString(2, libraryBranch.getBranchAddress());
		pstmt.setInt(3, libraryBranch.getBranchId());

		pstmt.executeUpdate();

	}

	public void removeBranch(LibraryBranch libraryBranch) throws SQLException {
		Connection conn = getConnection();

		String removeQuery = "delete from tbl_library_branch where branchId=?";
		PreparedStatement pstmt = conn.prepareStatement(removeQuery);
		pstmt.setInt(1, libraryBranch.getBranchId());
		pstmt.executeUpdate();

	}

	public List<LibraryBranch> readAll() throws SQLException {
		String select = "select * from tbl_library_branch";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		ResultSet rs = stmt.executeQuery();

		List<LibraryBranch> branches = new ArrayList<LibraryBranch>();
		while(rs.next()) {
			LibraryBranch libraryBranch = new LibraryBranch();
			libraryBranch.setBranchId(rs.getInt("branchId"));
			libraryBranch.setBranchName(rs.getString("branchName"));
			libraryBranch.setBranchAddress(rs.getString("branchAddress"));
			

			branches.add(libraryBranch);
		}

		return branches;
	}

	public LibraryBranch getBranchByName(String branchName) throws SQLException{
		String select = "select * from tbl_library_branch where branchName=?";
		PreparedStatement pstmt = getConnection().prepareStatement(select);
		pstmt.setString(1, branchName);

		ResultSet rs = pstmt.executeQuery();

		LibraryBranch libraryBranch = new LibraryBranch();
		if(rs.next()) {
			libraryBranch.setBranchId(rs.getInt("branchId"));
			libraryBranch.setBranchName(rs.getString("branchName"));
			libraryBranch.setBranchAddress(rs.getString("branchAddress"));

		}

		return libraryBranch;	
	}
	
	public LibraryBranch getBranchById(int branchId) throws SQLException{
		String select = "select * from tbl_library_branch where branchId=?";
		PreparedStatement pstmt = getConnection().prepareStatement(select);
		pstmt.setInt(1, branchId);

		ResultSet rs = pstmt.executeQuery();

		LibraryBranch libraryBranch = new LibraryBranch();
		if(rs.next()) {
			libraryBranch.setBranchId(rs.getInt("branchId"));
			libraryBranch.setBranchName(rs.getString("branchName"));
			libraryBranch.setBranchAddress(rs.getString("branchAddress"));

		}

		return libraryBranch;	
	}


}
