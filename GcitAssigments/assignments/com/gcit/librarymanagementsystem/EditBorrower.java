/**
 * Editborrower.java
 */
package com.gcit.librarymanagementsystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author kemar
 *Apr 17, 20153:49:04 PM
 */
public class EditBorrower {
	
	Scanner sc = new Scanner(System.in);
	PreparedStatement pstmt ;
	Statement stmt = null;
	DatabaseConnection dbConnection=new DatabaseConnection();


	public  EditBorrower(){
		System.out.println("****** Edit borrower******");

		
	}
	/**
	 * 4)	Add/Update/Delete Borrowers menu
	 */
	public void editborrowerMenu(){
		int menuSelection;
		System.out.println("1) Add borrower\n2)Update borrower\n3)Delete Borrower");
		System.out.println("4 Main menu");
		System.out.println("Enter a number from menu");
		menuSelection=sc.nextInt();
		switch (menuSelection) {
		case 1:
			try {
				this.addborrower();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				this.updateBorrower();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				this.deleteBorrower();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			MainMenu mainMenu=new MainMenu();
			mainMenu.mainMenuSetup();
			break;


		default:
			break;
		}

	}



	/**
	 * @throws SQLException 
	 * Adds new borrower to library management system
	 */
	private void addborrower() throws SQLException {
		System.out.println("****Add borrower****");
		System.out.println("Enter a borrower name ");
		String borrowerName = sc.next();
		System.out.println("enter borrower  Address");
		String borrowerAddress = sc.next();
		System.out.println("Enter borrower Phone Number");
		Long borrowerPhone = sc.nextLong();
		try {
			String  insertIntoPublisher= "INSERT INTO tbl_borrower"
					+ "(name,address,phone) VALUES"
					+ "(?,?,?)";
			pstmt = dbConnection.databaseConnection().prepareCall(insertIntoPublisher);
			pstmt.setString(1, borrowerName);
			pstmt.setString(2, borrowerAddress);
			pstmt.setLong(3, borrowerPhone);
			pstmt.executeUpdate();
			System.out.println("New borrower Added\n");
			editborrowerMenu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("New borrower entry failed");

			e.printStackTrace();
		}finally{
			dbConnection.databaseConnection().close();
			pstmt.close();

		}
		
	}
	/**
	 * updates borrowers in library management system
	 * @throws SQLException
	 */
	public void updateBorrower() throws SQLException{
		System.out.println("****Update Borrower****");
		String searchForBorrowerQuery = null;
		String borrowerNameOrID = null;
		System.out.println("Search for borrower you want to update by cardNo or name ");
		System.out.println("1)borrowerName \n2)cardNo");
		int selectedBorrowerId = 0;
		int nameOrCardNo = sc.nextInt();
		switch (nameOrCardNo) {
		case 1:
			System.out.println("enter Name");
			borrowerNameOrID=sc.next();
			searchForBorrowerQuery = "select * from tbl_borrower where name =?";
			break;
		case 2:
			System.out.println("enter cardNo");
			borrowerNameOrID=sc.next();
			searchForBorrowerQuery = "select * from tbl_borrower where cardNo=?";
			break;

		default:
			break;
		}

		try {
			pstmt = dbConnection.databaseConnection().prepareCall(searchForBorrowerQuery);
			pstmt.setString(1, borrowerNameOrID);

			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				System.out.println("cardNo "+rs.getInt("cardNo"));
				System.out.println("name "+rs.getString("name"));
				System.out.println("address "+rs.getString("address"));
				System.out.println("Phone "+rs.getLong("phone"));

				selectedBorrowerId=rs.getInt("cardNo");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Enter name in columns you want to update or old value");
		System.out.println("enter name");
		String updatedBorrowerName = sc.next();
		System.out.println("enter borrowerAddress");
		String updatedBorrowerAddress = sc.next();
		System.out.println("enter phone number");
		Long updatedBarrowerNumber=sc.nextLong();

		String updateQuery = "update tbl_borrower SET name = ?,address = ?,phone = ? where cardNo= ?";

		try {
			PreparedStatement pstmt2 = dbConnection.databaseConnection().prepareCall(updateQuery);
			pstmt2.setString(1, updatedBorrowerName);	
			pstmt2.setString(2, updatedBorrowerAddress);
			pstmt2.setLong(3, updatedBarrowerNumber);
			pstmt2.setInt(4, selectedBorrowerId);
			pstmt2.executeUpdate();
			System.out.println("record updated");
			editborrowerMenu();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbConnection.databaseConnection().close();
			pstmt.close();
		}




	}
	/**
	 * deletes borrowers from library management system
	 * @throws SQLException
	 */
	public void deleteBorrower() throws SQLException{
		System.out.println("****Delete Borrower****");
		String searchForBorrowerQuery = null;
		String borrowerNameOrID = null;
		System.out.println("Search for barrower you want to delete by name or piublisherId ");
		System.out.println("1)borrower name \n2)cardNo");
		int selectedBorrowerId = 0;
		int nameOrBorrowerId = sc.nextInt();
		switch (nameOrBorrowerId) {
		case 1:
			System.out.println("enter borrower name");
			borrowerNameOrID=sc.next();
			searchForBorrowerQuery = "select * from tbl_borrower where name =?";


			break;
		case 2:
			System.out.println("enter publisherId");
			borrowerNameOrID=sc.next();
			searchForBorrowerQuery = "select * from tbl_borrower where cardNo=?";

			break;
			
		default:
			break;
		}
		try {
			pstmt = dbConnection.databaseConnection().prepareCall(searchForBorrowerQuery);
			pstmt.setString(1, borrowerNameOrID);

			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				System.out.println("cardNo "+rs.getInt("cardNo"));
				System.out.println("name "+rs.getString("name"));
				System.out.println("address "+rs.getString("address"));
				System.out.println("Phone "+rs.getLong("phone"));

				selectedBorrowerId=rs.getInt("cardNo");
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbConnection.databaseConnection().close();
			pstmt.close();

		}
		
		System.out.println("You are about to delete this Borrower :"+borrowerNameOrID+"are you sure");
		System.out.println("enter y/n");
		String deleteOrNot = sc.next();
		
		String updateQuery = "delete from tbl_borrower  where cardNo= ?";
        if (deleteOrNot.equals("y")){
		try {
			PreparedStatement pstmt2 = dbConnection.databaseConnection().prepareCall(updateQuery);
			pstmt2.setInt(1,selectedBorrowerId);
			pstmt2.executeUpdate();
			
			System.out.println("record deleted ");
        	this.editborrowerMenu();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbConnection.databaseConnection().close();
			pstmt.close();

		}
		
	}
        else{
        	this.editborrowerMenu();
        	
        }
		
	}

}
