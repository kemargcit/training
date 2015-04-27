package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;

public class BookDAO extends BaseDAO<Book> implements Serializable {

	public BookDAO(Connection conn) {
		super(conn);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1619700647002508164L;

	public void addBook(Book book) throws SQLException {

		Integer pubId = null;
		if (book.getPublisher() != null)
			pubId = book.getPublisher().getId();

		int bookId = saveWithId(
				"insert into tbl_book (title, pubId) values (?,?)", new Object[] {
						book.getTitle(), pubId });

		for (Author a : book.getAuthors()) {
			save("insert into tbl_book_authors (bookId, authorId) values (?,?)",
					new Object[] { bookId, a.getAuthorId() });
		}
		
		for (Genre g : book.getGenres()) {
			save("insert into tbl_book_genres (bookId, genre_Id) values (?,?)",
					new Object[] { bookId, g.getGenreId() });
		}
	}

	public void updateAuthor(Book book) throws SQLException {
		String updateQuery = "update tbl_book";
	}

	public void removeAuthor(Book book) throws SQLException {
		
	}
	public void removeBook(Book book) throws SQLException {
		
		String deleteQuery = "Delete from tbl_book where bookId =?";
		save(deleteQuery,new Object[]{book.getBookId()});
		
		
	}

	@SuppressWarnings("unchecked")
	public List<Book> readAll() throws SQLException {
		return (List<Book>) read("select * from tbl_book", null);
	}
	@SuppressWarnings("unchecked")
	public List<Book> readAllByBranch(String branchName) throws SQLException {
		return (List<Book>) read("select * from tbl_book ", null);
	}
	public Book readOne(int bookId) throws SQLException {
	@SuppressWarnings("unchecked")
	List<Book> books=(List<Book>) read("select * from tbl_book where bookId=?",new Object[]{bookId});
		
	if(books!=null&&books.size()>0){
		return books.get(0);
	}
	else{
	return null;
	}
	}
	@SuppressWarnings("unchecked")
	public List<Book> readAllByTitle(String title) throws SQLException {
		return (List<Book>) read("select * from tbl_book where title=?",new Object[]{title});
	}
	@Override
	protected List<Book> mapResults(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		PublisherDAO pDAO = new PublisherDAO(conn);
		AuthorDAO aDAO = new AuthorDAO(conn);
		
		while (rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			b.setPublisher(pDAO.readOnePubById(rs.getInt("pubId")));
			
			@SuppressWarnings("unchecked")
			List<Author> authors = (List<Author>) aDAO.readFirstLevel("select * from tbl_author where authorId in "
					+ "(select authorId from tbl_book_authors where bookId = ?)", new Object[]{b.getBookId()});
			b.setAuthors(authors);
			
			books.add(b);
		}
		return books;
	}

	@Override
	protected List<Book> mapResultsFirstLevel(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		PublisherDAO pDAO = new PublisherDAO(conn);
		AuthorDAO aDAO = new AuthorDAO(conn);
		
		while (rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			b.setPublisher(pDAO.readOnePubById(rs.getInt("pubId")));
			
			books.add(b);
		}
		return books;
	}
}
