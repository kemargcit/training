/**
 * BorrowerDAO.java
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

import com.gcit.lms.domain.Borrower;

/**
 * @author kemar
 *Apr 19, 20152:19:57 PM
 */
public class BorrowerDAO extends BaseDAO<Borrower> implements  Serializable {

	/**
	 * @param conn
	 */
	public BorrowerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -6162620077049608551L;
	
	
	public void addBorrower(Borrower borrower) throws SQLException{

		String updateQuery = "insert into tbl_borrower (name,address,phone) values (?,?,?)";
		save(updateQuery,new Object []{ borrower.getBorrowerName(), borrower.getBorrowerAddress(), borrower.getBorrowerPhone()});
	}
	public void updateBorrower(Borrower borrower) throws SQLException {
		String updateQuery = "update tbl_borrower set name = ? , address=?, phone=? where cardNo = ?";
		save(updateQuery, new Object []{borrower.getBorrowerName(), borrower.getBorrowerAddress(),  borrower.getBorrowerPhone(), borrower.getCardNo()});
	}
	public void removeBorrower(Borrower borrower) throws SQLException{
		String removeQuery = "delete from tbl_borrower where cardNo=?";
		save (removeQuery, new Object []{borrower.getCardNo()});
	}


	@SuppressWarnings("unchecked")
	public List<Borrower> readAll() throws SQLException {
		String select = "select * from tbl_borrower";
      return    (List<Borrower>) read(select,null);
	
	}
	public Borrower getBorrowerByName(String borrowerName) throws SQLException{
		String select = "select * from tbl_borrower where name=?";
      @SuppressWarnings("unchecked")
	List<Borrower> borrowers=(List<Borrower>) read(select,new Object[]{borrowerName});
  if(borrowers!=null&&borrowers.size()>0)
  {
		return borrowers.get(0);	
  }
  else return null;
	}
	public Borrower getBorrowerByCardNo(int cardNo) throws SQLException{
		String select = "select * from tbl_borrower where cardNo=?";
		 @SuppressWarnings("unchecked")
			List<Borrower> borrowers=(List<Borrower>) read(select,new Object[]{cardNo});
		  if(borrowers!=null&&borrowers.size()>0)
		  {
				return borrowers.get(0);	
		  }
		  else return null;
	}

	/* (non-Javadoc)
	 * @see com.gcit.lms.dao.BaseDAO#mapResults(java.sql.ResultSet)
	 */
	@Override
	protected List<Borrower> mapResults(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		while(rs.next()) {
			Borrower borrower = new Borrower();
		  borrower.setCardNo(rs.getInt("cardNo"));
		  borrower.setBorrowerName(rs.getString("name"));
		  borrower.setBorrowerAddress(rs.getString("address"));
		  borrower.setBorrowerPhone(rs.getString("phone"));
			borrowers.add(borrower);
		}
		

		return borrowers;	}

	/* (non-Javadoc)
	 * @see com.gcit.lms.dao.BaseDAO#mapResultsFirstLevel(java.sql.ResultSet)
	 */
	@Override
	protected List<?> mapResultsFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
