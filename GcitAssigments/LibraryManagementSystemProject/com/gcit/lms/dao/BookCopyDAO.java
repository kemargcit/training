/**
 * BookCopiesDAO.java
 */
package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopy;

/**
 * @author kemar
 *Apr 21, 20151:46:42 AM
 */
public class BookCopyDAO extends BaseDAO<BookCopy> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2805856045683585679L;

	/**
	 * @param conn
	 */
	public BookCopyDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void  addBookCopies(BookCopy bookCopy) throws SQLException{
	String 	Select = "Insert into tbl_book_copies (bookId,branchId,noOfCopies) values (?,?,?)";
	save(Select,new Object[]{bookCopy.getBook().getBookId(),bookCopy.getLibraryBranch().getBranchId(),bookCopy.getNumOfCopies()});
		
	}

	public void updateBookCopies(BookCopy bookCopy) throws SQLException{
		String update = "update tbl_book_copies set noOfCopies=? where bookId=? and branchId=?";
		save(update, new Object[]{bookCopy.getNumOfCopies(),bookCopy.getLibraryBranch().getBranchId(),bookCopy.getNumOfCopies()});
	}
	  /**
	 * @param branchId
	 * @param bookid
	 * @return
	 * @throws SQLException 
	 */
	public BookCopy readOne(int branchId, int bookid) throws SQLException{
		  String select = "Select * from tbl_book_copies where bookId=? and branchId=?";
		  read(select,new Object[]{branchId,bookid});
		return null;
		  
	  }
	/* (non-Javadoc)
	 * @see com.gcit.lms.dao.BaseDAO#mapResults(java.sql.ResultSet)
	 */
	@Override
	protected List<BookCopy> mapResults(ResultSet rs) throws SQLException {
 
		List<BookCopy> bookCopies = new ArrayList<BookCopy>();
		while(rs.next()){
			BookCopy bookCopy = new BookCopy();
			//Book book = new BookDAO(conn).r
		//	bookCopy.setBook();
		}
				
		return null;
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
