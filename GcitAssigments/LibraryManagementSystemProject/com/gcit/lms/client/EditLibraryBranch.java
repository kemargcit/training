/**
 * EditLibraryBranch.java
 *//*
package com.gcit.lms.client;

import java.sql.SQLException;
import java.util.Scanner;

import com.gcit.lms.domain.LibraryBranch;
import com.gcit.lms.service.LibraryBranchService;

*//**
 * @author kemar Apr 17, 20151:22:05 PM
 *//*
public class EditLibraryBranch {
	Scanner sc = new Scanner(System.in);
	LibraryBranchService libraryBranchService = new LibraryBranchService();
	
	public EditLibraryBranch() {
		System.out.println("****** Edit Library Branch******");

	}

	*//**
	 * Add/Update/Delete Library Branches
	 *//*
	public void editLibraryBranchMenu() {
		int menuSelection;
		System.out.println("1) Add Branch\n2)Update Branch\n3)Delete Branch");
		System.out.println("4 Main menu");
		System.out.println("Enter a number from menu");
		menuSelection = sc.nextInt();
		switch (menuSelection) {
		case 1:
			try {
				this.addLibraryBranch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				this.updateLibraryBranch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				this.deleteLibraryBranch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			MainMenu mainMenu = new MainMenu();
			mainMenu.mainMenuSetup();
			break;

		default:
			break;
		}

	}
	

	*//**
	 * adds library branch to library management system
	 * 
	 * @throws SQLException
	 *//*
	public void addLibraryBranch() throws SQLException {
		System.out.println("****Add Library Branch****");
		System.out.println("Enter a Branch name ");
		String branchName = sc.next();
		System.out.println("enter Branch Address");
		String branchAddress = sc.next();
		
		LibraryBranch libraryBranch = new LibraryBranch();
		  libraryBranch.setBranchName(branchName);
		  libraryBranch.setBranchAddress(branchAddress);
		  libraryBranchService.addBranch(libraryBranch);
		  System.out.println("new branch added ");
		  this.editLibraryBranchMenu();
	}
	
public void updateLibraryBranch() throws SQLException{
		
		LibraryBranch libraryBranch = null;
		
		System.out.println("****Update Library Branch****");
		int branchNameOrID = 0;
		System.out.println("Search for branch you want to update by id or name ");
		System.out.println("1)branchName \n2)branchId");
		branchNameOrID=sc.nextInt();
		if(branchNameOrID==1){
			System.out.println("enter the name of the branch you want to update");
			String branchName = sc.next();
			libraryBranch =libraryBranchService.getBranchByName(branchName);
		}else if(branchNameOrID==2){
			System.out.println("enter the Id of the branch you want to update");
			int branchId = sc.nextInt();
			libraryBranch = libraryBranchService.getBranchById(branchId);
		}
		
		System.out.println("you are about to update the following branch:");
        System.out.println(libraryBranch.getBranchId()+" "+libraryBranch.getBranchName()+" "+libraryBranch.getBranchAddress());
		System.out.println("do you want to proceed ? y/n");
		if(sc.next().equalsIgnoreCase("y")){
			System.out.println("enter branch name");
			String branchName = sc.next();
			System.out.println("enter branch address");
			String address = sc.next();
			
			
			libraryBranch.setBranchName(branchName);
			libraryBranch.setBranchAddress(address);          
			libraryBranchService.updateBranch(libraryBranch);
			System.out.println("library branch updated to "+libraryBranch.getBranchId()+" "+libraryBranch.getBranchName()+" "+libraryBranch.getBranchAddress());
			this.editLibraryBranchMenu();
		}else if(sc.next().equalsIgnoreCase("n")){
			this.editLibraryBranchMenu();
		}
			
		
	}

public void deleteLibraryBranch() throws SQLException{
	LibraryBranch libraryBranch = null;
	System.out.println("****Delete Library Branch****");
	System.out.println("Search for library branch you want to delete by name or branchId ");
	System.out.println("1)branch name \n2)branchId");
	int nameOrbranchId = sc.nextInt();
	if (nameOrbranchId==1){
		System.out.println("enter the branch name");
         String branchName = sc.next();
		 libraryBranch= libraryBranchService.getBranchByName(branchName);
	}else if(nameOrbranchId==2){
		System.out.println("enter the branch Id");
		int branchId = sc.nextInt();
		libraryBranch= libraryBranchService.getBranchById(branchId)	;		
	}

	System.out.println("You are about to delete this library branch: "+libraryBranch.getBranchName()+" are you sure");
	System.out.println("enter y/n");
	String deleteOrNot = sc.next();
	if (deleteOrNot.equals("y")){
		libraryBranchService.removeBranch(libraryBranch);
		System.out.println("library branch deleted ");
		this.editLibraryBranchMenu();

	}
	else{
		this.editLibraryBranchMenu();

	}
}
}
*/