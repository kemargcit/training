/**
 * EditBookAndAuthor.java
 */
package com.gcit.librarymanagementsystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author kemar
 *Apr 16, 201510:29:19 PM
 */
public class EditBookAndAuthor {
	Scanner sc = new Scanner(System.in);
	PreparedStatement pstmt ;
	Statement stmt = null;
	DatabaseConnection dbConnection=new DatabaseConnection();


	public  EditBookAndAuthor(){
		System.out.println("****** Edit Book and Author******");


	}

	public void editBookOrAuthorMenu(){
		int menuSelection;
		System.out.println("1) Add Book\n2)Update Book\n3)Delete Book");
		System.out.println("4) Add Author\n5)Update Author\n6)Delete Author\n7)"
				+ "Main menu");
		System.out.println("Enter a number from menu");
		menuSelection=sc.nextInt();
		switch (menuSelection) {
		case 1:
			try {
				this.addBook();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			this.updateBook();
			break;
		case 3:
			this.deleteBook();
			break;
		case 4:
			try {
				this.addAuthor();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 5:
			this.updateAuthor();
			break;
		case 6:
			this.deleteAuthor();
			break;
		case 7:
			MainMenu mainMenu=new MainMenu();
			mainMenu.mainMenuSetup();
			break;
		default:
			break;
		}

	}

	public void addBook() throws SQLException{
		System.out.println("****Add Book****");
		System.out.println("Enter Book title ");
		String title = sc.next();
		System.out.println("Enter publusher Id");
		int pubId = sc.nextInt();

		try {
			String  insertIntoBook = "INSERT INTO tbl_book"
					+ "(title, pubId) VALUES"
					+ "(?,?)";
			pstmt = dbConnection.databaseConnection().prepareCall(insertIntoBook);
			pstmt.setString(1, title);
			pstmt.setInt(2, pubId);
			pstmt.executeUpdate();
			System.out.println("New book added");
			editBookOrAuthorMenu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbConnection.databaseConnection().close();
			pstmt.close();

		}


	}


	public void updateBook(){
		System.out.println("****Update Book****");

		String searchForBookQuery = null;
		String title = null;
		System.out.println("Search for book you want to update by id or title ");
		System.out.println("1)tile \n2)bookId");
		int selectedBookId = 0;
		int titleOrpubId = sc.nextInt();
		switch (titleOrpubId) {
		case 1:
			System.out.println("enter title");
			title=sc.next();
			searchForBookQuery = "select * from tbl_book where title =?";


			break;
		case 2:
			System.out.println("enter bookId");
			title=sc.next();
			searchForBookQuery = "select * from tbl_book where bookId=?";

			break;
			
		default:
			break;
		}

		try {
			pstmt = dbConnection.databaseConnection().prepareCall(searchForBookQuery);
			pstmt.setString(1, title);

			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				System.out.println("bookId "+rs.getInt("bookId"));
				System.out.println("Title "+rs.getString("title"));
				System.out.println("pubId "+rs.getInt("pubId"));

            selectedBookId=rs.getInt("bookId");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Enter info in columns you want to update or old value");
		System.out.println("title");
		String updatedtitle = sc.next();
		System.out.println("pubId");
		int updatedPubId = sc.nextInt();
		String updateQuery = "update tbl_book SET title = ? , pubId = ? where bookId= ?";
        
		try {
			PreparedStatement pstmt2 = dbConnection.databaseConnection().prepareCall(updateQuery);
              System.out.println(updatedtitle+" "+updatedPubId+" "+selectedBookId);
			pstmt2.setString(1, updatedtitle);
			pstmt2.setInt(2,updatedPubId);
			pstmt2.setInt(3, selectedBookId);
			pstmt2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}
	
	public void deleteBook(){
		System.out.println("****Delete Book****");

		String searchForBookQuery = null;
		String title = null;
		System.out.println("Search for book you want to update by id or title ");
		System.out.println("1)tile \n2)bookId");
		int selectedBookId = 0;
		int titleOrpubId = sc.nextInt();
		switch (titleOrpubId) {
		case 1:
			System.out.println("enter title");
			title=sc.next();
			searchForBookQuery = "select * from tbl_book where title =?";


			break;
		case 2:
			System.out.println("enter bookId");
			title=sc.next();
			searchForBookQuery = "select * from tbl_book where bookId=?";

			break;
			
		default:
			break;
		}
		try {
			pstmt = dbConnection.databaseConnection().prepareCall(searchForBookQuery);
			pstmt.setString(1, title);

			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				System.out.println("bookId "+rs.getInt("bookId"));
				System.out.println("Title "+rs.getString("title"));
				System.out.println("pubId "+rs.getInt("pubId"));

            selectedBookId=rs.getInt("bookId");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("You are about to delete this book :"+title+"are you sure");
		System.out.println("enter y/n");
		String deleteOrNot = sc.next();
		
		String updateQuery = "delete from tbl_book  where bookId= ?";
        if (deleteOrNot.equals("y")){
		try {
			PreparedStatement pstmt2 = dbConnection.databaseConnection().prepareCall(updateQuery);
			pstmt2.setInt(1,selectedBookId);
			pstmt2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
        else{
        	this.editBookOrAuthorMenu();
        	
        }
	}
	
	public void addAuthor() throws SQLException{
		System.out.println("****Add Author****");

		System.out.println("Enter a Author name ");
		String authorName = sc.next();
		

		try {
			String  insertIntoBook = "INSERT INTO tbl_author"
					+ "(authorName) VALUES"
					+ "(?)";
			pstmt = dbConnection.databaseConnection().prepareCall(insertIntoBook);
			pstmt.setString(1, authorName);
			pstmt.executeUpdate();
			System.out.println("New author added");
			editBookOrAuthorMenu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("New author entry failed");

			e.printStackTrace();
		}finally{
			dbConnection.databaseConnection().close();
			pstmt.close();

		}

		
	}
	
	public void updateAuthor(){
		System.out.println("****Update Author****");

		String searchForAuthorQuery = null;
		String authorNameOrID = null;
		System.out.println("Search for author you want to update by id or name ");
		System.out.println("1)name \n2)authorId");
		int selectedAuthorId = 0;
		int nameOrAuthorId = sc.nextInt();
		switch (nameOrAuthorId) {
		case 1:
			System.out.println("enter Name");
			authorNameOrID=sc.next();
			searchForAuthorQuery = "select * from tbl_author where authorName =?";


			break;
		case 2:
			System.out.println("enter AuthorId");
			authorNameOrID=sc.next();
			searchForAuthorQuery = "select * from tbl_author where authorId=?";

			break;
			
		default:
			break;
		}

		try {
			pstmt = dbConnection.databaseConnection().prepareCall(searchForAuthorQuery);
			pstmt.setString(1, authorNameOrID);

			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				System.out.println("AuthorId "+rs.getInt("authorId"));
				System.out.println("Name "+rs.getString("authorName"));

            selectedAuthorId=rs.getInt("authorId");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Enter name in columns you want to update or old value");
		System.out.println("name");
		String updatedAuthorName = sc.next();
		
		String updateQuery = "update tbl_author SET authorName = ? where authorId= ?";
        
		try {
			PreparedStatement pstmt2 = dbConnection.databaseConnection().prepareCall(updateQuery);
			pstmt2.setString(1, updatedAuthorName);			
			pstmt2.setInt(2, selectedAuthorId);
			System.out.println(updatedAuthorName + " "+selectedAuthorId);
			pstmt2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}
	public void deleteAuthor(){
		System.out.println("****Delete Author****");

		String searchForAuthorQuery = null;
		String authorNameOrID = null;
		System.out.println("Search for author you want to delete by name or authorId ");
		System.out.println("1)name \n2)authorId");
		int selectedAuthorId = 0;
		int nameOrAuthorId = sc.nextInt();
		switch (nameOrAuthorId) {
		case 1:
			System.out.println("enter ");
			authorNameOrID=sc.next();
			searchForAuthorQuery = "select * from tbl_author where authorName =?";


			break;
		case 2:
			System.out.println("enter bookId");
			authorNameOrID=sc.next();
			searchForAuthorQuery = "select * from tbl_author where authorId=?";

			break;
			
		default:
			break;
		}
		try {
			pstmt = dbConnection.databaseConnection().prepareCall(searchForAuthorQuery);
			pstmt.setString(1, authorNameOrID);

			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				System.out.println("authorId "+rs.getInt("authorId"));
				System.out.println("Name "+rs.getString("authorName"));

            selectedAuthorId=rs.getInt("authorId");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("You are about to delete this author :"+authorNameOrID+"are you sure");
		System.out.println("enter y/n");
		String deleteOrNot = sc.next();
		
		String updateQuery = "delete from tbl_author  where authorId= ?";
        if (deleteOrNot.equals("y")){
		try {
			PreparedStatement pstmt2 = dbConnection.databaseConnection().prepareCall(updateQuery);
			pstmt2.setInt(1,selectedAuthorId);
			pstmt2.executeUpdate();
			
			System.out.println("record deleted ");
        	this.editBookOrAuthorMenu();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
        else{
        	this.editBookOrAuthorMenu();
        	
        }
	}
	

}
