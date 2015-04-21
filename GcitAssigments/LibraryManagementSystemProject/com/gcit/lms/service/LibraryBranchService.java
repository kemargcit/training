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
	
	LibraryBranchDAO libraryBranchDAO = new LibraryBranchDAO();
	public void addBranch(LibraryBranch libraryBranch) throws SQLException {
		libraryBranchDAO.addBranch(libraryBranch);
	}

	public void updateBranch(LibraryBranch libraryBranch) throws SQLException {
		libraryBranchDAO.updateBranch(libraryBranch);
	}

	public void removeBranch(LibraryBranch libraryBranch) throws SQLException {
		libraryBranchDAO.removeBranch(libraryBranch);
	}
	
	public List<LibraryBranch> readAll() throws SQLException {
		List<LibraryBranch> libraryBranchs = libraryBranchDAO.readAll();
		return libraryBranchs;
		
				
	}
	public LibraryBranch getBranchByName(String branchName) throws SQLException{
		LibraryBranch libraryBranch = libraryBranchDAO.getBranchByName(branchName);
		return libraryBranch;
	}
	
	public LibraryBranch getBranchById(int branchId) throws SQLException{
		LibraryBranch libraryBranch = libraryBranchDAO.getBranchById(branchId);
		return libraryBranch;
	}



}
