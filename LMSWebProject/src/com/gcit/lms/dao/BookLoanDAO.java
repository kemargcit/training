/**
 * BookLoanDAO.java
 */
package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.LibraryBranch;

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

	
	public void  addBookLoan(BookLoan bookLoan) throws SQLException, ParseException{
		Date out = new Date();
	    Calendar calo = Calendar.getInstance();  
	    calo.setTime(out);  
	    out = calo.getTime(); 
		Date due = new Date();
	    Calendar cal = Calendar.getInstance();  
	    cal.setTime(due);  
	    cal.add(Calendar.DATE, 7);    
	    due = cal.getTime();   
			String updateQuery = "insert into tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate) values (?,?,?,?,?)";
            
			save(updateQuery, new Object [] {bookLoan.getBook().getBookId(),bookLoan.getLibraryBranch().getBranchId(),bookLoan.getBorrower().getCardNo(),out,
          due});
	}
	
	public void  deleteBookLoan(BookLoan bookLoan) throws SQLException, ParseException{
		String updateQuery = "delete  from tbl_book_loans where branchId=? and cardNo=? and bookId=?";
		save(updateQuery, new Object [] {bookLoan.getLibraryBranch().getBranchId(),bookLoan.getBorrower().getCardNo(),bookLoan.getBook().getBookId()});
	}

	public void overideDueDate(BookLoan bookLoan) throws SQLException{
		

		String updateQuery = "update tbl_book_loans SET dueDate = ? where bookId =? and branchId=? and cardNo=?";
         save(updateQuery,new Object[] {bookLoan.getDateDue(), bookLoan.getBook().getBookId(),bookLoan.getLibraryBranch().getBranchId(),bookLoan.getBorrower().getCardNo()});
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<BookLoan> readAll() throws SQLException {
		return  (List<BookLoan>) read("select * from tbl_book_loans", null);
	}
	@SuppressWarnings("unchecked")
	public List<BookLoan> readAllByBorrower(int cardNo) throws SQLException {
		return  (List<BookLoan>) read("select * from tbl_book_loans where cardNo=?", new Object [] {cardNo});
		
	}

	/* (non-Javadoc)
	 * @see com.gcit.lms.dao.BaseDAO#mapResults(java.sql.ResultSet)
	 */
	@Override
	protected List<BookLoan> mapResults(ResultSet rs) throws SQLException {
		
      List<BookLoan> bookLoans = new ArrayList<BookLoan>();
      while(rs.next()){
    	 BookLoan bookLoan = new BookLoan();
    	 Book book = new BookDAO(conn).readOne(rs.getInt("bookId"));
    	 LibraryBranch libraryBranch = new LibraryBranchDAO(conn).getBranchById(rs.getInt("branchId"));
    	 Borrower borrower = new BorrowerDAO(conn).getBorrowerByCardNo(rs.getInt("cardNo"));
    	 bookLoan.setBook(book);
    	 bookLoan.setBorrower(borrower);
    	 bookLoan.setLibraryBranch(libraryBranch);
    	 bookLoan.setDateOut(rs.getDate("dateOut"));
    	 bookLoan.setDateDue(rs.getDate("dueDate"));
    	 bookLoans.add(bookLoan);
      }
		
		return bookLoans;
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
