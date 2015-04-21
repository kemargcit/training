/**
 * GenreService.java
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;

/**
 * @author kemar
 *Apr 19, 201510:03:25 PM
 */
public class GenreService {
	GenreDAO genreDAO = new GenreDAO();
	public void addGenre(Genre genre) throws SQLException {
		genreDAO.addGenre(genre);
		
	}

	public void updateGenre(Genre genre) throws SQLException {
		genreDAO.updateGenre(genre);
	}
	public void removeGenre(Genre genre) throws SQLException {
		genreDAO.removeGenre(genre);
	}

	public List<Book> getListOfBooksByGenre(int genreId){
		List<Book> books= genreDAO.getListOfBooks(genreId);
		return books;
	}
	
	public List<Genre> readAllGenre() throws SQLException {
		List<Genre> genres = genreDAO.readAllGenre();
		return genres;
	}
	
	public Genre getGenreByName(String genreName) throws SQLException{
		Genre genre = genreDAO.getGenreByName(genreName);
		return genre;
	}
	public Genre getGenreById(int genreId) throws SQLException{
		Genre genre = genreDAO.getGenreById(genreId);
		return genre;
	}


}
