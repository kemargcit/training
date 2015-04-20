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
	public void addGenre(Genre genre) throws SQLException {
		new GenreDAO().addGenre(genre);
		
	}

	public void updateGenre(Genre genre) throws SQLException {
		new GenreDAO().updateGenre(genre);
	}
	public void removeGenre(Genre genre) throws SQLException {
		new GenreDAO().removeGenre(genre);
	}

	public List<Book> getListOfBooksByGenre(int genreId){
		List<Book> books= new GenreDAO().getListOfBooks(genreId);
		return books;
	}
	
	public List<Genre> readAllGenre() throws SQLException {
		List<Genre> genres = new GenreDAO().readAllGenre();
		return genres;
	}
	
	public Genre getGenreByName(String genreName) throws SQLException{
		Genre genre = new GenreDAO().getGenreByName(genreName);
		return genre;
	}
	public Genre getGenreById(int genreId) throws SQLException{
		Genre genre = new GenreDAO().getGenreById(genreId);
		return genre;
	}


}
