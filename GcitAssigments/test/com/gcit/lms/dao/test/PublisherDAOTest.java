/**
 * PublisherDAOTest.java
 */
package com.gcit.lms.dao.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Publisher;

/**
 * @author kemar
 *Apr 19, 20156:38:09 PM
 */
public class PublisherDAOTest {

	/**
	 * Test method for {@link com.gcit.lms.dao.PublisherDAO#addPublisher(com.gcit.lms.domain.Publisher)}.
	 */
	@Test @Ignore
	public void testAddPublisher() {

		Publisher publisher = new Publisher();
		publisher.setName("francis");
		publisher.setAddress("canarse");
		publisher.setPhoneNumber("1234567895");
		try {
			new PublisherDAO().addPublisher(publisher);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	} 

	/**
	 * Test method for {@link com.gcit.lms.dao.PublisherDAO#updatePublisher(com.gcit.lms.domain.Publisher)}.
	 */
	@Test @Ignore
	public void testUpdatePublisher() {
		PublisherDAO publisherDAO = new PublisherDAO();
		Publisher publisher = null;
		try {
			publisher = publisherDAO.getPublisherByName("francis");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		publisher.setPhoneNumber("1111111111");
		try {
			new PublisherDAO().updatePublisher(publisher);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link com.gcit.lms.dao.PublisherDAO#removeBranch(com.gcit.lms.domain.Publisher)}.
	 */
	@Test @Ignore
	public void testRemovePublisher() {
		PublisherDAO publisherDAO = new PublisherDAO();
		Publisher publisher = null;
		try {
			publisher = publisherDAO.getPublisherByName("francis");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			new PublisherDAO().removePublisher(publisher);
		} catch (SQLException e) {
			fail(e.getMessage());
		}

	}

	/**
	 * Test method for {@link com.gcit.lms.dao.PublisherDAO#getListOfBooks(int)}.
	 */
	@Test @Ignore
	public void testGetListOfBooks() {
		List<Book> books = new PublisherDAO().getListOfBooks(1);
		for(Book book:books){
			System.out.println(book.getBookId()+" "+book.getTitle());
		}
	}

	/**
	 * Test method for {@link com.gcit.lms.dao.PublisherDAO#readAll()}.
	 */
	@Test @Ignore
	public void testReadAll() {
		try {
			List<Publisher> publishers = new PublisherDAO().readAll();
			for(Publisher publisher:publishers){
				System.out.println(publisher.getName());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	/**
	 * Test method for {@link com.gcit.lms.dao.PublisherDAO#getBranchByName(java.lang.String)}.
	 */
	@Test @Ignore
	public void testGetPublisherByName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.gcit.lms.dao.PublisherDAO#getBranchById(int)}.
	 */
	@Test 
	public void testGetPublisherById() {
     try {
		Publisher publisher = new PublisherDAO().getPublisherById(2);
		System.out.println(publisher.getName());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
