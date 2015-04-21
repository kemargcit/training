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
	PublisherDAO publisherDAO = new PublisherDAO();
	public void addPublisher(Publisher publisher) throws SQLException {
		publisherDAO.addPublisher(publisher);
		
	}
	
	public void updatePublisher(Publisher publisher) throws SQLException {
		publisherDAO.updatePublisher(publisher);
	}

	public void removePublisher(Publisher publisher) throws SQLException {
		publisherDAO.removePublisher(publisher);
	}

	public List<Book> getListOfBooks(int publisherId){
		List<Book> books = publisherDAO.getListOfBooks(publisherId);
		return books;
	}
	
	public List<Publisher> readAll() throws SQLException {
		List<Publisher> publishers = publisherDAO.readAll();
		return publishers;
	}
	public Publisher getPublisherByName(String PublisherName) throws SQLException{
		Publisher publisher = publisherDAO.getPublisherByName(PublisherName);
		return publisher;
	}
	public Publisher getPublisherById(int publisherId) throws SQLException{
		Publisher publisher = publisherDAO.getPublisherById(publisherId);
		return publisher;
	}



}
