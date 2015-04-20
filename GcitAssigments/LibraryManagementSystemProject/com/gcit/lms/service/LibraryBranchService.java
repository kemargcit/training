/**
 * LibraryBranchService.java
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.domain.LibraryBranch;

/**
 * @author kemar
 *Apr 19, 201511:17:53 PM
 */
public class LibraryBranchService {
	public void addBranch(LibraryBranch libraryBranch) throws SQLException {
		new LibraryBranchDAO().addBranch(libraryBranch);
	}

	public void updateBranch(LibraryBranch libraryBranch) throws SQLException {
		new LibraryBranchDAO().updateBranch(libraryBranch);
	}

	public void removeBranch(LibraryBranch libraryBranch) throws SQLException {
		new LibraryBranchDAO().removeBranch(libraryBranch);
	}
	
	public List<LibraryBranch> readAll() throws SQLException {
		List<LibraryBranch> libraryBranchs = new LibraryBranchDAO().readAll();
		return libraryBranchs;
		
				
	}
	public LibraryBranch getBranchByName(String branchName) throws SQLException{
		LibraryBranch libraryBranch = new LibraryBranchDAO().getBranchByName(branchName);
		return libraryBranch;
	}
	
	public LibraryBranch getBranchById(int branchId) throws SQLException{
		LibraryBranch libraryBranch = new LibraryBranchDAO().getBranchById(branchId);
		return libraryBranch;
	}



}
