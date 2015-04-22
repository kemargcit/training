/**
 * LibraryBranchDAO.java
 */
package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.LibraryBranch;

/**
 * @author kemar
 *Apr 19, 20153:45:26 PM
 */
public class LibraryBranchDAO extends BaseDAO<LibraryBranch> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7751294459992321796L;
	/**
	 * @param conn
	 */
	public LibraryBranchDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public void addBranch(LibraryBranch libraryBranch) throws SQLException {

		String updateQuery = "insert into tbl_library_branch (branchName,branchAddress) values (?,?)";
        save(updateQuery, new Object[]{libraryBranch.getBranchName(),libraryBranch.getBranchAddress()});
	}


	public void updateBranch(LibraryBranch libraryBranch) throws SQLException {
		String updateQuery = "update tbl_library_branch set branchName = ?,branchAddress =? where branchId = ?";
		save(updateQuery, new Object []{libraryBranch.getBranchName(),libraryBranch.getBranchAddress(),libraryBranch.getBranchId()});
	}

	public void removeBranch(LibraryBranch libraryBranch) throws SQLException {
		String removeQuery = "delete from tbl_library_branch where branchId=?";
		save(removeQuery,new Object[]{libraryBranch.getBranchId()});
	}

	@SuppressWarnings("unchecked")
	public List<LibraryBranch> readAll() throws SQLException {
		String select = "select * from tbl_library_branch";
		return (List<LibraryBranch>) read(select,null);
		
	}

	public LibraryBranch getBranchByName(String branchName) throws SQLException{
		String select = "select * from tbl_library_branch where branchName=?";
		@SuppressWarnings("unchecked")
		List<LibraryBranch> libraryBranchs =(List<LibraryBranch>) read(select, new Object[]{branchName});
          if(libraryBranchs!=null&&libraryBranchs.size()>0){
      		return libraryBranchs.get(0);	

          }
          else return null;
		
	}
	
	public LibraryBranch getBranchById(int branchId) throws SQLException{
		String select = "select * from tbl_library_branch where branchId=?";
		@SuppressWarnings("unchecked")
		List<LibraryBranch> libraryBranchs =(List<LibraryBranch>) read(select, new Object[]{branchId});
          if(libraryBranchs!=null&&libraryBranchs.size()>0){
      		return libraryBranchs.get(0);	

          }
          else return null;
		
	}
	/* (non-Javadoc)
	 * @see com.gcit.lms.dao.BaseDAO#mapResults(java.sql.ResultSet)
	 */
	@Override
	protected List<LibraryBranch> mapResults(ResultSet rs) throws SQLException {
		List<LibraryBranch> branches = new ArrayList<LibraryBranch>();
		while(rs.next()) {
			LibraryBranch libraryBranch = new LibraryBranch();
			libraryBranch.setBranchId(rs.getInt("branchId"));
			libraryBranch.setBranchName(rs.getString("branchName"));
			libraryBranch.setBranchAddress(rs.getString("branchAddress"));
			branches.add(libraryBranch);
		}

		return branches;
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
