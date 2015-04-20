/**
 * PublisherService.java
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Publisher;

/**
 * @author kemar
 *Apr 19, 201511:30:57 PM
 */
public class PublisherService {
	public void addPublisher(Publisher publisher) throws SQLException {
		new PublisherDAO().addPublisher(publisher);
		
	}
	
	public void updatePublisher(Publisher publisher) throws SQLException {
		new PublisherDAO().updatePublisher(publisher);
	}

	public void removePublisher(Publisher publisher) throws SQLException {
		new PublisherDAO().removePublisher(publisher);
	}

	public List<Book> getListOfBooks(int publisherId){
		List<Book> books = new PublisherDAO().getListOfBooks(publisherId);
		return books;
	}
	
	public List<Publisher> readAll() throws SQLException {
		List<Publisher> publishers = new PublisherDAO().readAll();
		return publishers;
	}
	public Publisher getPublisherByName(String PublisherName) throws SQLException{
		Publisher publisher = new PublisherDAO().getPublisherByName(PublisherName);
		return publisher;
	}
	public Publisher getPublisherById(int publisherId) throws SQLException{
		Publisher publisher = new PublisherDAO().getPublisherById(publisherId);
		return publisher;
	}



}
