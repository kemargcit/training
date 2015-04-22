/**
 * EditBook.java
 */
package com.gcit.lms.client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Publisher;
import com.gcit.lms.service.AdministratorService;

/**
 * @author kemar
 *Apr 21, 201512:12:44 PM
 */
public class EditBook {
	//BookService bookService = new BookService();
	AdministratorService administratorService = new AdministratorService();
	Scanner sc = new Scanner(System.in);
	public void editBookMenu(){
		
		int menuSelection;
		System.out.println("1) Add Book\n2)Update Book\n3)Delete Book"+
		"\n4) Main menu");
		System.out.println("Enter a number from menu");
		menuSelection=sc.nextInt();
		switch (menuSelection) {
		case 1:
			try {
				this.addBook();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				this.updateBook();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case 3:
			try {
				this.deleteBook();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		

		default:
			break;
		}

	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	private void addBook() throws Exception {
		System.out.println("****Add Book****");
		Book book = new Book();
		 System.out.println(" enter book title ");
		 String title = sc.next();
		 book.setTitle(title);
		 System.out.println("enter publisher id");
		 int pubId = sc.nextInt();
		 Publisher publisher = administratorService.readOnePub(pubId);
		 book.setPublisher(publisher);
		 System.out.println("how many authors");
		 int numOfAuthors = sc.nextInt();
		 List<Author> authors = new ArrayList<Author>();
		 for(int i=1;i<=numOfAuthors;i++){
			 System.out.println("Enter Author Name");
			 String authorName = sc.next();
					 
			 Author author = administratorService.readOneByName(authorName);
			 authors.add(author);
			 
		 }
		book.setAuthors(authors);
		
		administratorService.addBook(book);
		
		

		
	}
	
	public void updateBook() throws SQLException{
		System.out.println("****Update Book****");
		Book book = null;
		System.out.println("Search for Book to update by title or id");
		System.out.println("1) title \n2) id");
		int titleOrId = sc.nextInt();
		if(titleOrId==1){
			System.out.println("enter title");
			String title = sc.next();
			List<Book>	books=administratorService.readAllByTitle(title);
			int bookNum = 1;
			for(Book abook:books){
				 System.out.println(bookNum++ +") "+abook.getBookId()+" "+abook.getTitle());
			}
			System.out.println("Choose a Book");
			int selectedBook = sc.nextInt();
			System.out.println("you have selected "+books.get(selectedBook-1).getBookId()+books.get(selectedBook-1).getTitle());
		    book=books.get(selectedBook-1);
		}else if (titleOrId==2){
			System.out.println("enter Id ");
			int bookId = sc.nextInt();
			book=administratorService.readOneBookId(bookId);

			
		}
	 System.out.println("enter new book title or n/a");
	 String title = sc.next();
	 if(title.equalsIgnoreCase("n/a")){
		 
	 }else{
	 book.setTitle(title);
	 }
	 System.out.println("enter publisher id  or n/a");
	 String pubId = sc.next();
	 if(pubId.equalsIgnoreCase("n/a")){
	 }else {
		 Publisher publisher = administratorService.readOnePub(Integer.parseInt(pubId));
		 book.setPublisher(publisher);

	 }
     System.out.println("do you want to edit author y/n");
     String editAuthorOrNo = sc.next();
		if(editAuthorOrNo.equalsIgnoreCase("y")){
			List<Author> authors =administratorService.readOneBookId(book.getBookId()).getAuthors();
			System.out.print("The authors are ");
			int numOfAuthors = 0;
			for(Author author:authors){
				System.out.println(" "+author.getAuthorName());
			}
			System.out.println(numOfAuthors+" enter update for author(s) or n/a ");
			   
            /*for(int i=1;i<=numOfAuthors;i++){
            	Author author = new Author();
            	System.out.println("enter update author name ");
            	String name = sc.next();
            	if(name.equalsIgnoreCase("n/a")){
            		
            	}else{
            		authors.set(i,author.setAuthorName(name););
            	}
            	
            }*/
            

		}
	}
	/**
	 * @throws SQLException 
	 * 
	 */
	private void deleteBook() throws SQLException {
     System.out.println("****Delete Book****");
		Book book = null;

     System.out.println("Search for Book to delete by title or id");
		System.out.println("1) title \n2) id");
		int titleOrId = sc.nextInt();
		if(titleOrId==1){
			System.out.println("enter title");
			String title = sc.next();
			List<Book>	books=administratorService.readAllByTitle(title);
			int bookNum = 1;
			for(Book abook:books){
				 System.out.println(bookNum++ +") "+abook.getBookId()+" "+abook.getTitle());
			}
			System.out.println("Choose a Book");
			int selectedBook = sc.nextInt();
			System.out.println("you have selected "+books.get(selectedBook-1).getBookId()+books.get(selectedBook-1).getTitle());
		    book=books.get(selectedBook-1);
		    System.out.println("are you sure you want to delete");
		     
		    administratorService.deleteBook(book);
		}else if (titleOrId==2){
			System.out.println("enter Id ");
			int bookId = sc.nextInt();
			book=administratorService.readOneBookId(bookId);
             administratorService.deleteBook(book);
			
		}		
		
	}

}
