package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;

public class AuthorDAO extends BaseDAO<Author> implements Serializable {

	public AuthorDAO(Connection conn) {
		super(conn);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1619700647002508164L;

	public void addAuthor(Author author) throws SQLException {
		save("insert into tbl_author (authorName) values (?)",
				new Object[] { author.getAuthorName() });

	}

	public void updateAuthor(Author author) throws SQLException {
		save("update tbl_author set authorName = ? where authorId = ?",
				new Object[] { author.getAuthorName(), author.getAuthorId() });
	}

	public void removeAuthor(Author author) throws SQLException {
		save("delete from tbl_author where authorId=?",
				new Object[] { author.getAuthorId() });
	}

	@SuppressWarnings("unchecked")
	public List<Author> readAll() throws SQLException {
		return  (List<Author>) read("select * from tbl_author", null);
	}

	public Author readOneByAuthorId(int authorId) throws SQLException {
		@SuppressWarnings("unchecked")
		List<Author> authors = (List<Author>) read(
				"select * from tbl_author where authorId = ?",
				new Object[] { authorId });
		if (authors != null && authors.size() > 0) {
			return authors.get(0);
		} else {
			return null;
		}
	}
	
	public Author readOneByAuthorName(String authorName) throws SQLException {
		@SuppressWarnings("unchecked")
		List<Author> authors = (List<Author>) read(
				"select * from tbl_author where authorName = ?",
				new Object[] { authorName });
		if (authors != null && authors.size() > 0) {
			return authors.get(0);
		} else {
			return null;
		}
	}

	@Override
	protected List<Author> mapResults(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<Author>();
		BookDAO bDAO = new BookDAO(conn);
		while (rs.next()) {
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));

			@SuppressWarnings("unchecked")
			List<Book> books = (List<Book>) bDAO.readFirstLevel("select * from tbl_book where bookId in "
					+ "(select bookId from tbl_book_authors where authorId = ?)", new Object[]{a.getAuthorId()});
			a.setBooks(books);
			
			authors.add(a);
		}
		return authors;
	}

	@Override
	protected List<Author> mapResultsFirstLevel(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<Author>();
		BookDAO bDAO = new BookDAO(conn);
		while (rs.next()) {
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));

			authors.add(a);
		}
		return authors;
	}
}
