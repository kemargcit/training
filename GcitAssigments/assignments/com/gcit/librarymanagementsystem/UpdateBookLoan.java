/**
 * UpdateBookLoan.java
 */
package com.gcit.librarymanagementsystem;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Scanner;

/**
 * @author kemar bell
 *Apr 17, 20154:29:21 PM
 */
public class UpdateBookLoan {

	Scanner sc = new Scanner(System.in);
	PreparedStatement pstmt ;
	Statement stmt = null;
	DatabaseConnection dbConnection=new DatabaseConnection();


	public  UpdateBookLoan(){
		System.out.println("******Update Due Date******");


	}
/**
 * override the due date for a loan
 * @throws SQLException
 * @throws ParseException
 */

	public void updateDueDate() throws SQLException, ParseException{
		System.out.println("enter bookId, branchId and cardNo overide due date");
		System.out.println("bookId");
		int bookId=sc.nextInt();
		System.out.println("branchId");
		int branchId = sc.nextInt();
		System.out.println("cardNo");
		int cardNo = sc.nextInt();

		String searchForLoan = "select * from tbl_book_loans where bookId =? and branchId=? and cardNo=?";

		try {
			pstmt = dbConnection.databaseConnection().prepareCall(searchForLoan);
			pstmt.setInt(1, bookId);
			pstmt.setInt(2, branchId);
			pstmt.setInt(3, cardNo);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				System.out.println("bookId "+rs.getInt("bookId"));
				System.out.println("branchId "+rs.getInt("branchId"));
				System.out.println("cardNo "+rs.getInt("cardNo"));
				System.out.println("dateOut"+rs.getDate("dateOut"));
				System.out.println("dueDate "+rs.getDate("dueDate"));
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbConnection.databaseConnection().close();
			pstmt.close();

		}
		System.out.println("Do you want to override due date for this loan");
		System.out.println("enter y/n");
		String overrideOrNot = sc.next();
		
		String updateQuery = "update tbl_book_loans SET dueDate = ? where bookId =? and branchId=? and cardNo=?";
        if (overrideOrNot.equals("y")){
        	System.out.println("enter new due date");
        
        	String dateIn =sc.next();
        	Date dueDate = java.sql.Date.valueOf(dateIn);
        	try {
    			PreparedStatement pstmt2 = dbConnection.databaseConnection().prepareCall(updateQuery);
    			pstmt2.setDate(1, dueDate);	
    			pstmt2.setInt(2, bookId);
    			pstmt2.setInt(3, branchId);
    			pstmt2.setInt(4, cardNo);
    			pstmt2.executeUpdate();
    			System.out.println("record updated");
    			//editborrowerMenu();

    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}finally{
    			dbConnection.databaseConnection().close();
    			pstmt.close();
    		}
        }

	}




}
