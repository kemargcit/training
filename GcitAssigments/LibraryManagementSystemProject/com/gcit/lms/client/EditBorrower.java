/**
 * Editborrower.java
 */
package com.gcit.lms.client;

import java.sql.SQLException;
import java.util.Scanner;

import com.gcit.lms.domain.Borrower;
import com.gcit.lms.service.BorrowerService;

/**
 * @author kemar
 *Apr 17, 20153:49:04 PM
 */
public class EditBorrower {

	Scanner sc = new Scanner(System.in);
	BorrowerService borrowerService = new BorrowerService();



	public  EditBorrower(){
		System.out.println("****** Edit borrower******");


	}
	/**
	 * Add/Update/Delete Borrowers menu
	 */
	public void editborrowerMenu(){
		int menuSelection;
		System.out.println("1) Add borrower\n2)Update borrower\n3)Delete Borrower");
		System.out.println("4 Main menu");
		System.out.println("Enter a number from menu");
		menuSelection=sc.nextInt();
		switch (menuSelection) {
		case 1:
			try {
				this.addborrower();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				this.updateBorrower();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			case 3:
			try {
				this.deleteBorrower();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			 case 4:
				 MainMenu mainMenu=new MainMenu();
				 mainMenu.mainMenuSetup();
				 break;
			 default:
				 break;
		}
	}

	
	/**
	 * @throws SQLException 
	 * Adds new borrower to library management system
	 */
	private void addborrower() throws SQLException {
		Borrower borrower = new Borrower();

		System.out.println("****Add borrower****");
		System.out.println("Enter a borrower name ");
		String borrowerName = sc.next();
		System.out.println("enter borrower  Address");
		String borrowerAddress = sc.next();
		System.out.println("Enter borrower Phone Number");
		String borrowerPhone = sc.next();

		borrower.setBorrowerName(borrowerName);
		borrower.setBorrowerAddress(borrowerAddress);
		borrower.setBorrowerPhone(borrowerPhone);

		borrowerService.addBorrower(borrower);
		System.out.println("New borrower Added");
		this.editborrowerMenu();

	}


	/**
	 * @throws SQLException 
	 * 
	 */
	private void updateBorrower() throws SQLException {
		Borrower borrower = null;

		System.out.println("****Update Borrower****");
		int borrowerNameOrCardNo = 0;
		System.out.println("Search for borrower you want to update by name ord cardNo ");
		System.out.println("1)Borrower Name \n2)Card No");
		borrowerNameOrCardNo=sc.nextInt();
		if(borrowerNameOrCardNo==1){
			System.out.println("enter the name of the Borrower you want to update");
			String borrowerName = sc.next();
			borrower = borrowerService.getBorrowerByName(borrowerName);
		}else if(borrowerNameOrCardNo==2){
			System.out.println("enter the card No of the Borrower you want to update");
			int cardNo = sc.nextInt();
			borrower = borrowerService.getBorrowerByCardNo(cardNo);
					}

		System.out.println("you are about to update the following Borrower:");
		System.out.println(borrower.getCardNo()+" "+borrower.getBorrowerName()+" "+borrower.getBorrowerAddress()+" "+borrower.getBorrowerPhone());
		System.out.println("do you want to proceed ? y/n");
		if(sc.next().equalsIgnoreCase("y")){
			System.out.println("enter Borrower name");
			String borrowerName = sc.next();
			System.out.println("enter borrower address");
			String borrowerAddress = sc.next();
			System.out.println("enter borrower phone number");
			String borrowerPhone = sc.next();
			
			borrower.setBorrowerName(borrowerName);
			borrower.setBorrowerAddress(borrowerAddress);
			borrower.setBorrowerPhone(borrowerPhone);
			
		
			borrowerService.updateBorrower(borrower);
			System.out.println("borrower updated to "+borrower.getCardNo()+" "+borrower.getBorrowerName()+" "+borrower.getBorrowerAddress()+" "+borrower.getBorrowerPhone());
            this.editborrowerMenu();
		}else if(sc.next().equalsIgnoreCase("n")){
            this.editborrowerMenu();
		}		
	}
	
	public void deleteBorrower() throws SQLException {
		Borrower borrower = null;
		System.out.println("****Delete Borrower****");
		System.out.println("Search for borrower you want to delete by name or cardNo ");
		System.out.println("1)Borrower name \n2)Card No");
		int nameOrCardNo = sc.nextInt();
		if (nameOrCardNo==1){
			System.out.println("enter the Borrower name");
             String borrowerName = sc.next();
			 borrower= borrowerService.getBorrowerByName(borrowerName);
		}else if(nameOrCardNo==2){
			System.out.println("enter the card No");
			int cardNo = sc.nextInt();
			borrower= borrowerService.getBorrowerByCardNo(cardNo);			
		}

		System.out.println("You are about to delete this borrower : "+borrower.getBorrowerName()+" are you sure");
		System.out.println("enter y/n");
		String deleteOrNot = sc.next();
		if (deleteOrNot.equals("y")){
			borrowerService.removeBorrower(borrower);
			System.out.println("borrower deleted ");
			this.editborrowerMenu();
		}
		else{
			this.editborrowerMenu();
		}	
	}
}
