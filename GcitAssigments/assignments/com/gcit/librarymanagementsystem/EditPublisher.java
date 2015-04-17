/**
 * EditPublisher.java
 */
package com.gcit.librarymanagementsystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author kemar
 *Apr 17, 201512:06:32 PM
 */
public class EditPublisher {

	Scanner sc = new Scanner(System.in);
	PreparedStatement pstmt ;
	Statement stmt = null;
	DatabaseConnection dbConnection=new DatabaseConnection();


	public  EditPublisher(){
		System.out.println("****** Edit Publisher******");


	}

	public void editPublisherMenu(){
		int menuSelection;
		System.out.println("1) Add Publisher\n2)Update Publisher\n3)Delete Publsher");
		System.out.println("4 Main menu");
		System.out.println("Enter a number from menu");
		menuSelection=sc.nextInt();
		switch (menuSelection) {
		case 1:
			try {
				this.addPublisher();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				this.updatePublisher();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			case 3:
			try {
				this.deletePublisher();
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
	public void addPublisher() throws SQLException{

		System.out.println("****Add Publisher****");
		System.out.println("Enter a Publisher name ");
		String publisherName = sc.next();
		System.out.println("enter Publisher Address");
		String publisherAddress = sc.next();
		System.out.println("Enter Publisher Phone Number");
		Long publisherPhone = sc.nextLong();
		try {
			String  insertIntoPublisher= "INSERT INTO tbl_publisher"
					+ "(publisherName,publisherAddress,publisherPhone) VALUES"
					+ "(?,?,?)";
			pstmt = dbConnection.databaseConnection().prepareCall(insertIntoPublisher);
			pstmt.setString(1, publisherName);
			pstmt.setString(2, publisherAddress);
			pstmt.setLong(3, publisherPhone);
			pstmt.executeUpdate();
			System.out.println("New Publisher Added\n");
			editPublisherMenu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("New publisher entry failed");

			e.printStackTrace();
		}finally{
			dbConnection.databaseConnection().close();
			pstmt.close();

		}

	}
	public void updatePublisher() throws SQLException{
		System.out.println("****Update Publisher****");
		String searchForAuthorQuery = null;
		String publisherNameOrID = null;
		System.out.println("Search for publisher you want to update by id or name ");
		System.out.println("1)PublisherName \n2)publisheId");
		int selectedPublisherId = 0;
		int nameOrPublisherId = sc.nextInt();
		switch (nameOrPublisherId) {
		case 1:
			System.out.println("enter Name");
			publisherNameOrID=sc.next();
			searchForAuthorQuery = "select * from tbl_publisher where publisherName =?";
			break;
		case 2:
			System.out.println("enter PublisherId");
			publisherNameOrID=sc.next();
			searchForAuthorQuery = "select * from tbl_publisher where publisherId=?";
			break;

		default:
			break;
		}

		try {
			pstmt = dbConnection.databaseConnection().prepareCall(searchForAuthorQuery);
			pstmt.setString(1, publisherNameOrID);

			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				System.out.println("publiherId "+rs.getInt("publisherId"));
				System.out.println("publisherName "+rs.getString("publisherName"));
				System.out.println("publisherAddress "+rs.getString("publisherAddress"));
				System.out.println("publisherPhone "+rs.getLong("publisherPhone"));

				selectedPublisherId=rs.getInt("publisherId");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Enter name in columns you want to update or old value");
		System.out.println("enter publisherName");
		String updatedPubliserName = sc.next();
		System.out.println("enter publisherAddress");
		String updatedPubliserAddress = sc.next();
		System.out.println("enter phone number");
		Long updatedPublisherNumber=sc.nextLong();

		String updateQuery = "update tbl_publisher SET publisherName = ?,publisherAddress = ?,publisherPhone = ? where publisherId= ?";

		try {
			PreparedStatement pstmt2 = dbConnection.databaseConnection().prepareCall(updateQuery);
			pstmt2.setString(1, updatedPubliserName);	
			pstmt2.setString(2, updatedPubliserAddress);
			pstmt2.setLong(3, updatedPublisherNumber);
			pstmt2.setInt(4, selectedPublisherId);
			//System.out.println(updatedPubliserName + " "+selectedPublisherId);
			pstmt2.executeUpdate();
			System.out.println("record updated");
			editPublisherMenu();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbConnection.databaseConnection().close();
			pstmt.close();
		}





	}
	public void deletePublisher() throws SQLException{
		System.out.println("****Delete Publisher****");
		String searchForPublisherQuery = null;
		String publisherNameOrID = null;
		System.out.println("Search for publisher you want to delete by name or piublisherId ");
		System.out.println("1)publisher name \n2)publisherId");
		int selectedPublisherId = 0;
		int nameOrPublisherId = sc.nextInt();
		switch (nameOrPublisherId) {
		case 1:
			System.out.println("enter publiher name");
			publisherNameOrID=sc.next();
			searchForPublisherQuery = "select * from tbl_publisher where publisherName =?";


			break;
		case 2:
			System.out.println("enter publisherId");
			publisherNameOrID=sc.next();
			searchForPublisherQuery = "select * from tbl_publisher where publisherId=?";

			break;
			
		default:
			break;
		}
		try {
			pstmt = dbConnection.databaseConnection().prepareCall(searchForPublisherQuery);
			pstmt.setString(1, publisherNameOrID);

			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				System.out.println("publiherId "+rs.getInt("publisherId"));
				System.out.println("publisherName "+rs.getString("publisherName"));
				System.out.println("publisherAddress "+rs.getString("publisherAddress"));
				System.out.println("publisherPhone "+rs.getLong("publisherPhone"));

				selectedPublisherId=rs.getInt("publisherId");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbConnection.databaseConnection().close();
			pstmt.close();

		}
		
		System.out.println("You are about to delete this publisher :"+publisherNameOrID+"are you sure");
		System.out.println("enter y/n");
		String deleteOrNot = sc.next();
		
		String updateQuery = "delete from tbl_publisher  where publisherId= ?";
        if (deleteOrNot.equals("y")){
		try {
			PreparedStatement pstmt2 = dbConnection.databaseConnection().prepareCall(updateQuery);
			pstmt2.setInt(1,selectedPublisherId);
			pstmt2.executeUpdate();
			
			System.out.println("record deleted ");
        	this.editPublisherMenu();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbConnection.databaseConnection().close();
			pstmt.close();

		}
		
	}
        else{
        	this.editPublisherMenu();
        	
        }
		
	}

}
