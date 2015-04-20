/**
 * BookLoanDAO.java
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
import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.LibraryBranch;

/**
 * @author kemar
 *Apr 19, 20152:16:10 AM
 */
public class BookLoanDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -631970332837238218L;

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/library", "root", "stpatrick876");
		return conn;
	}
	
	public void  addBookLoan(BookLoan bookLoan) throws SQLException{
			Connection conn = getConnection();
			
			String updateQuery = "insert into tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate) values (?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            java.sql.Date outDate = new java.sql.Date(bookLoan.getDateOut().getTime());
             java.sql.Date dueDate = new java.sql.Date(bookLoan.getDateDue().getTime()); 
			pstmt.setInt(1, bookLoan.getBook().getBookId());
			pstmt.setInt(2, bookLoan.getLibraryBranch().getBranchId());
			pstmt.setInt(3, bookLoan.getBorrower().getCardNo());
			pstmt.setDate(4, outDate);
			pstmt.setDate(5,dueDate );
			
			
			pstmt.executeUpdate();
		

		
	}

	public List<BookLoan> getBookLoans() throws SQLException{
		String selectQuery = "select * from  tbl_book_loans ";
		PreparedStatement stmt = getConnection().prepareStatement(selectQuery);
		ResultSet rs = stmt.executeQuery();
		List<BookLoan> bookLoans = new ArrayList<BookLoan>();
		while(rs.next()) {
			int bookId= rs.getInt("bookId");
			int cardNo =rs.getInt("cardNo");
			int  branchId = rs.getInt("branchId");
			Book book = new BookDAO().getBookById(bookId);
			Borrower borrower = new BorrowerDAO().getBorrowerByCardNo(cardNo);
			LibraryBranch libraryBranch = new LibraryBranchDAO().getBranchById(branchId);
			BookLoan bookLoan = new BookLoan();
			bookLoan.setBook(book);
			bookLoan.setBorrower(borrower);
			bookLoan.setLibraryBranch(libraryBranch);
			
			bookLoans.add(bookLoan);
		}


	
		
		return bookLoans;
		
	}
	
	public void overideDueDate(BookLoan bookLoan) throws SQLException{
		Connection conn = getConnection();
        java.sql.Date dueDate = new java.sql.Date(bookLoan.getDateDue().getTime()); 

		String updateQuery = "update tbl_book_loans SET dueDate = ? where bookId =? and branchId=? and cardNo=?";
		PreparedStatement pstmt = conn.prepareStatement(updateQuery);
		pstmt.setDate(1, dueDate);
		pstmt.setInt(2, bookLoan.getBook().getBookId());
		pstmt.setInt(3, bookLoan.getLibraryBranch().getBranchId());
		pstmt.setInt(4, bookLoan.getBorrower().getCardNo());

		pstmt.executeUpdate();

		
	}
}
