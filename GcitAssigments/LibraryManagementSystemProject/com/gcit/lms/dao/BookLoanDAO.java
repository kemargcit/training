/**
 * BookLoanDAO.java
 */
package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.BookLoan;

/**
 * @author kemar
 *Apr 19, 20152:16:10 AM
 */
public class BookLoanDAO extends BaseDAO<BookLoan> implements Serializable {
	/**
	 * @param conn
	 */
	public BookLoanDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -631970332837238218L;

	
	public void  addBookLoan(BookLoan bookLoan) throws SQLException{
			
			String updateQuery = "insert into tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate) values (?,?,?,?,?)";
            save(updateQuery, new Object [] {bookLoan.getBook().getBookId(),bookLoan.getLibraryBranch().getBranchId(),bookLoan.getBorrower().getCardNo(),
            	 bookLoan.getDateDue(),bookLoan.getDateDue()});		
	}
	
	public void overideDueDate(BookLoan bookLoan) throws SQLException{

		String updateQuery = "update tbl_book_loans SET dueDate = ? where bookId =? and branchId=? and cardNo=?";
         save(updateQuery,new Object[] {bookLoan.getDateDue(), bookLoan.getBook().getBookId(),bookLoan.getBorrower().getCardNo()});
		
	}
	
	@SuppressWarnings("unchecked")
	public List<BookLoan> readAll() throws SQLException {
		return  (List<BookLoan>) read("select * from tbl_book_loan", null);
	}

	/* (non-Javadoc)
	 * @see com.gcit.lms.dao.BaseDAO#mapResults(java.sql.ResultSet)
	 */
	@Override
	protected List<BookLoan> mapResults(ResultSet rs) throws SQLException {
		
		BookDAO bookDAO = new BookDAO(conn);

      List<BookLoan> bookLoans = new ArrayList<BookLoan>();
      while(rs.next()){
    	 BookLoan bookLoan = new BookLoan();
    	 String query = "select * from tbl_book where bookId=?";
    	 bookLoan.setBook(null);
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
	


	
		
		//return bookLoans;
		
	//}
	
	
}
