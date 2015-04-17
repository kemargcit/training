/**
 * EditLibraryBranch.java
 */
package com.gcit.librarymanagementsystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author kemar
 *Apr 17, 20151:22:05 PM
 */
public class EditLibraryBranch {
	Scanner sc = new Scanner(System.in);
	PreparedStatement pstmt ;
	Statement stmt = null;
	DatabaseConnection dbConnection=new DatabaseConnection();


	public  EditLibraryBranch(){
		System.out.println("****** Edit Library Branch******");


	}

	public void editLibraryBranchMenu(){
		int menuSelection;
		System.out.println("1) Add Branch\n2)Update Branch\n3)Delete Branch");
		System.out.println("4 Main menu");
		System.out.println("Enter a number from menu");
		menuSelection=sc.nextInt();
		switch (menuSelection) {
		case 1:
			try {
				this.addLibraryBranch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				this.updateLibraryBranch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			case 3:
			this.deleteBranch();
			break;
		case 4:
			MainMenu mainMenu=new MainMenu();
			mainMenu.mainMenuSetup();
			break;


		default:
			break;
		}

	}
	
	public void addLibraryBranch() throws SQLException{
		System.out.println("****Add Library Branch****");
		System.out.println("Enter a Branch name ");
		String branchName = sc.next();
		System.out.println("enter Branch Address");
		String branchAddress = sc.next();
		try {
			String  insertIntoLibraryBranch = "INSERT INTO tbl_library_branch"
					+ "(branchName,branchAddress) VALUES"
					+ "(?,?)";
			pstmt = dbConnection.databaseConnection().prepareCall(insertIntoLibraryBranch);
			pstmt.setString(1, branchName);
			pstmt.setString(2, branchAddress);
			pstmt.executeUpdate();
			System.out.println("New Library Branch Added\n");
	        this.editLibraryBranchMenu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("New Branch entry failed");

			e.printStackTrace();
		}finally{
			dbConnection.databaseConnection().close();
			pstmt.close();

		}

		
	}
	/**
	 * updates a library branch
	 * @throws SQLException 
	 */
	private void updateLibraryBranch() throws SQLException {
		System.out.println("****Update Library Branch****");
		String searchForBranchQuery = null;
		String branchNameOrID = null;
		System.out.println("Search for branch you want to update by id or name ");
		System.out.println("1)branchName \n2)branchId");
		int selectedBranchId = 0;
		int nameOrBranchId = sc.nextInt();
		switch (nameOrBranchId) {
		case 1:
			System.out.println("enter Name");
			branchNameOrID=sc.next();
			searchForBranchQuery = "select * from tbl_library_branch where branchName =?";
			break;
		case 2:
			System.out.println("enter PublisherId");
			branchNameOrID=sc.next();
			searchForBranchQuery = "select * from tbl_library_branch where branchId=?";
			break;

		default:
			break;
		}

		try {
			pstmt = dbConnection.databaseConnection().prepareCall(searchForBranchQuery);
			pstmt.setString(1, branchNameOrID);

			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				System.out.println("branchId "+rs.getInt("branchId"));
				System.out.println("branchName "+rs.getString("branchName"));
				System.out.println("branchAddress "+rs.getString("branchAddress"));

				selectedBranchId=rs.getInt("branchId");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Enter name in columns you want to update or old value");
		System.out.println("enter branchName");
		String updatedbranchName = sc.next();
		System.out.println("enter branchAddress");
		String updatedBranchAddress = sc.next();
		String updateQuery = "update tbl_library_branch SET branchName = ?,branchAddress = ? where branchId= ?";

		try {
			PreparedStatement pstmt2 = dbConnection.databaseConnection().prepareCall(updateQuery);
			pstmt2.setString(1, updatedbranchName);	
			pstmt2.setString(2, updatedBranchAddress);
			pstmt2.setInt(4, selectedBranchId);
			pstmt2.executeUpdate();
			System.out.println("record updated");
			editLibraryBranchMenu();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbConnection.databaseConnection().close();
			pstmt.close();
		}


		
		
		
	}
	
	public void deleteBranch(){
		System.out.println("****Delete Branch****");
	}

}
