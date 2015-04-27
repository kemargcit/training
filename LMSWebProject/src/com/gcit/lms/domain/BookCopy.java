/**
 * BookCopies.java
 */
package com.gcit.lms.domain;

import java.io.Serializable;

/**
 * @author kemar
 *Apr 21, 20151:43:53 AM
 */
public class BookCopy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7824823293778228296L;
	
	private Book book;
	private LibraryBranch libraryBranch;
    private int numOfCopies;
	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}
	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}
	/**
	 * @return the libraryBranch
	 */
	public LibraryBranch getLibraryBranch() {
		return libraryBranch;
	}
	/**
	 * @param libraryBranch the libraryBranch to set
	 */
	public void setLibraryBranch(LibraryBranch libraryBranch) {
		this.libraryBranch = libraryBranch;
	}
	/**
	 * @return the numOfCopies
	 */
	public int getNumOfCopies() {
		return numOfCopies;
	}
	/**
	 * @param numOfCopies the numOfCopies to set
	 */
	public void setNumOfCopies(int numOfCopies) {
		this.numOfCopies = numOfCopies;
	}
	
}
