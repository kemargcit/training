/**
 * EditBookAndAuthor.java
 *//*
package com.gcit.lms.client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.service.AuthorService;
import com.gcit.lms.service.BookService;

*//**
 * @author kemar
 *Apr 16, 201510:29:19 PM
 *//*
public class EditBookAndAuthor {
	Scanner sc = new Scanner(System.in);
	BookService bookService=new BookService();
	AuthorService authorService =new AuthorService();

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
		case 4:
			try {
				this.addAuthor();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

			case 5:
			try {
				this.updateAuthor();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 6:
			try {
				this.deleteAuthor();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		String title = sc.nextLine();
         sc.next();
		System.out.println("Enter publusher Id");
		int pubId = sc.nextInt();
		System.out.println("enter how many authors does this book have?");
		int numOfAuthors = sc.nextInt();
		List<Author> authors = new ArrayList<Author>();

		for(int i=1;i<=numOfAuthors;i++){
			System.out.println("Enter author id for author "+i);
			int authorId = sc.nextInt();
			System.out.println("Enter author Name for author "+i);
			String authorName = sc.next();
			Author author= new Author();
			author.setAuthorId(authorId);
			author.setAuthorName(authorName);
			authors.add(author);

		}

		Book book = new Book();
		book.setTitle(title);
		book.setPubId(pubId);
		book.setAuthors(authors);
		bookService.addBook(book);
		System.out.println("New Book Added");
		this.editBookOrAuthorMenu();
	}

	public void updateBook() throws SQLException{
		Book book = null;
		int bookId = 0;
		System.out.println("****Update Book****");
		System.out.println("Search for book you want to update by id or title ");
		System.out.println("1)tile \n2)bookId");
		int titleOrBookId = sc.nextInt();
		if(titleOrBookId==1){
			System.out.println("enter book title");
			String title = sc.next();
			book=bookService.getBookByTitle(title);
		}else if(titleOrBookId==2){
			System.out.println("enter book Id");
			bookId = sc.nextInt();
			book=bookService.getBookById(bookId);

		}
		bookId=book.getBookId();
		System.out.println("you are about to update the following book :");
		System.out.println(bookId+" "+book.getTitle());
		System.out.println("do you want to proceed ? y/n");
		if (sc.next().equalsIgnoreCase("y")){
			System.out.println("enter new title ");
			String newTitle = sc.next();
			book.setTitle(newTitle);

			bookService.updateBook(book);
			System.out.println("title updated to "+bookService.getBookById(bookId).getTitle());
			
			this.editBookOrAuthorMenu();
		}else{
            this.editBookOrAuthorMenu();
		}
	}

	public void addAuthor() throws SQLException{
		System.out.println("****Add Author****");

		System.out.println("Enter a Author name ");
		String authorName = sc.next();
		System.out.println("enter the number of of books yo want to ad for this author");
		int numOfBooks = sc.nextInt();
		List<Book> books = new ArrayList<Book>();

		for(int i=1;i<=numOfBooks;i++){
			Book book = new Book();
			System.out.println("enter id for book "+i);
			int bookId = sc.nextInt();
			System.out.println("enter for book "+i);
			sc.next();
			String title = sc.nextLine();
			System.out.println("enter publisher id for book "+i);
			int pubId = sc.nextInt();
			book.setBookId(bookId);
			book.setTitle(title);
			book.setPubId(pubId);

			books.add(book);
		}
		Author author = new Author();
		author.setAuthorName(authorName);
		author.setBooks(books);

		authorService.addAuthor(author);
		System.out.println("New Author added");
		this.editBookOrAuthorMenu();

	}

	//TODO cannot delete book foreign key constraint exception
	public void deleteBook() throws SQLException{
		Book book = null;
		System.out.println("****Delete Book****");
		String title = null;
		System.out.println("Search for book you want to delete by id or title ");
		System.out.println("1)tile \n2)bookId");
		int titleOrBookId = sc.nextInt();
		if(titleOrBookId==1){
			System.out.println("enter book title");
			title = sc.next();
			book=bookService.getBookByTitle(title);
		}else if(titleOrBookId==2){
			System.out.println("enter book Id");
			int bookId = sc.nextInt();
			book=bookService.getBookById(bookId);

		}


		System.out.println("You are about to delete this book :"+book.getTitle()+"are you sure");
		System.out.println("enter y/n");
		String deleteOrNot = sc.next();
		System.out.println(book.getBookId()+" "+book.getTitle());
		if (deleteOrNot.equals("y")){
        bookService.removeBook(book);
        System.out.println("book deleted");
        this.editBookOrAuthorMenu();

		}
		else{
			this.editBookOrAuthorMenu();

		}
	}


	public void updateAuthor() throws SQLException{
		Author author = null;
		System.out.println("****Update Author****");

		System.out.println("Search for author you want to update by id or name ");
		System.out.println("1)name \n2)authorId");
		int nameOrAuthorId = sc.nextInt();
		if (nameOrAuthorId==1){
			System.out.println("enter the author name");
             String authorName = sc.next();
			 author= authorService.getAuthorByName(authorName);
		}else if(nameOrAuthorId==2){
			System.out.println("enter the author Id");
			int authorId = sc.nextInt();
			 author = authorService.getAuthorById(authorId);
					
		}
		System.out.println("you are about to update the following author :");
         System.out.println(author.getAuthorId()+" "+author.getAuthorName());
 		System.out.println("do you want to proceed ? y/n");
 		if(sc.next().equalsIgnoreCase("y")){
 			System.out.println("enter author name");
 			String authorName = sc.next();
 			author.setAuthorName(authorName);
 			authorService.updateAuthor(author);
 			System.out.println("Author updated to "+author.getAuthorName());
 			this.editBookOrAuthorMenu();
 		}else if(sc.next().equalsIgnoreCase("n")){
 			this.editBookOrAuthorMenu();
 		}	
			}
	public void deleteAuthor() throws SQLException{
		Author author = null;
		System.out.println("****Delete Author****");
		System.out.println("Search for author you want to delete by name or authorId ");
		System.out.println("1)name \n2)authorId");
		int nameOrAuthorId = sc.nextInt();
		if (nameOrAuthorId==1){
			System.out.println("enter the author name");
             String authorName = sc.next();
			 author= authorService.getAuthorByName(authorName);
		}else if(nameOrAuthorId==2){
			System.out.println("enter the author Id");
			int authorId = sc.nextInt();
			 author = authorService.getAuthorById(authorId);			
		}

		System.out.println("You are about to delete this author :"+author.getAuthorName()+" are you sure");
		System.out.println("enter y/n");
		String deleteOrNot = sc.next();
		if (deleteOrNot.equals("y")){
			authorService.removeAuthor(author);
			System.out.println("author deleted ");
			this.editBookOrAuthorMenu();
		}
		else{
			this.editBookOrAuthorMenu();
		}
         
	}

}
*/