/**
 * GenreDAO.java
 */
package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;

/**
 * @author kemar
 *Apr 19, 20153:20:54 PM
 */
public class GenreDAO extends BaseDAO<Genre> implements Serializable {

	/**
	 * @param conn
	 */
	public GenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 2688793748275673863L;
	
	
	
	
	public void addGenre(Genre genre) throws SQLException {
		String updateQuery = "insert into tbl_genre (genre_name) values (?)";
		   save(updateQuery, new Object[] {genre.getName()});

	}
	
	public void updateGenre(Genre genre) throws SQLException {

		String updateQuery = "update tbl_genre set genre_name = ? where genre_id = ?";
		save(updateQuery,new Object[]{ genre.getGenreId(), genre.getName()});

	}
	public void removeGenre(Genre genre) throws SQLException {
		String removeQuery = "delete from tbl_genre where genre_id=?";
		save(removeQuery, new Object[]{genre.getGenreId()});

	}
	
	
	public List<Genre> readAllGenre() throws SQLException {
		String select = "select * from tbl_genre";
		 List<Genre> genres =(List<Genre>) read(select,null);

		return genres;
	}

	
	
	public Genre getGenreByName(String genreName) throws SQLException{
		String select = "select * from tbl_genre where genre_name =?";
		@SuppressWarnings("unchecked")
		List<Genre> genres =(List<Genre>) read(select,new Object[] {genreName});
		if (genres!=null&&genres.size()>0){
		return genres.get(0);
		}
		else return null;
	}
	public Genre getGenreById(int genreId) throws SQLException{
		String select = "select * from tbl_genre where genre_id =?";
		@SuppressWarnings("unchecked")
		List<Genre> genres =(List<Genre>) read(select,new Object[] {genreId});
		if (genres!=null&&genres.size()>0){
		return genres.get(0);
		}
		else return null;
	}


	/* (non-Javadoc)
	 * @see com.gcit.lms.dao.BaseDAO#mapResults(java.sql.ResultSet)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected List<Genre> mapResults(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<Genre>();
		while(rs.next()) {
			Genre genre = new Genre();
			int genreId = rs.getInt("genre_id");
			genre.setGenreId(genreId);
			
			genre.setName(rs.getString("genre_name"));
			
         	/*genre.setBooks((List<Book>) new BookDAO(conn).read("select * from tbl_book where bookId in (select bookId from tbl_book_genre where genreId=?",
         			new Object[] {genreId} ));
*/
			genres.add(genre);
		}		
		return genres;
	}


	/* (non-Javadoc)
	 * @see com.gcit.lms.dao.BaseDAO#mapResultsFirstLevel(java.sql.ResultSet)
	 */
	@Override
	protected List<?> mapResultsFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
