/**
 * EditPublisher.java
 */
package com.gcit.lms.client;

import java.sql.SQLException;
import java.util.Scanner;

import com.gcit.lms.domain.Publisher;
import com.gcit.lms.service.PublisherService;

/**
 * @author kemar
 *Apr 17, 201512:06:32 PM
 */
public class EditPublisher {
	
    PublisherService publisherService = new  PublisherService();
	Scanner sc = new Scanner(System.in);
	public  EditPublisher(){
		System.out.println("****** Edit Publisher******");


	}
/**
 * edit publisher information menu
 */
	public void editPublisherMenu(){
		int menuSelection;
		System.out.println("1) Add Publisher\n2)Update Publisher\n3)Delete Publsher");
		System.out.println("4 Main menu");
		System.out.println("Enter a number from menu");
		menuSelection=sc.nextInt();
		switch (menuSelection) {
		case 1:
			try {
				this.addPublisher();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				this.updatePublisher();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			case 3:
			try {
				this.deletePublisher();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
	 * add new publisher to library management system
	 * @throws SQLException
	 */
	public void addPublisher() throws SQLException{
		Publisher publisher = new  Publisher();

		System.out.println("****Add Publisher****");
		System.out.println("Enter a Publisher name ");
		String publisherName = sc.next();
		System.out.println("enter Publisher Address");
		String publisherAddress = sc.next();
		System.out.println("Enter Publisher Phone Number");
		String publisherPhone = sc.next();
		publisher.setName(publisherName);
		publisher.setAddress(publisherAddress);
		publisher.setPhoneNumber(publisherPhone);
		
		publisherService.addPublisher(publisher);
		System.out.println("Publisher added");
		this.editPublisherMenu();

	}
	
	
	public void updatePublisher() throws SQLException{
		
		Publisher publisher = null;
		
		System.out.println("****Update Publisher****");
		int publisherNameOrID = 0;
		System.out.println("Search for publisher you want to update by id or name ");
		System.out.println("1)PublisherName \n2)publisheId");
		publisherNameOrID=sc.nextInt();
		if(publisherNameOrID==1){
			System.out.println("enter the name of the publisher you want to update");
			String publisherName = sc.next();
			publisher = publisherService.getPublisherByName(publisherName);
			
		}else if(publisherNameOrID==2){
			
			System.out.println("enter the Id of the publisher you want to update");
			int publisherId = sc.nextInt();
			publisher = publisherService.getPublisherById(publisherId);
		}
		
		System.out.println("you are about to update the following publisher:");
        System.out.println(publisher.getId()+" "+publisher.getName()+" "+publisher.getAddress()+" "+publisher.getPhoneNumber());
		System.out.println("do you want to proceed ? y/n");
		if(sc.next().equalsIgnoreCase("y")){
			System.out.println("enter publisher name");
			String publisherName = sc.next();
			System.out.println("enter publisher address");
			String address = sc.next();
			System.out.println("enter publisher phone");
			String phoneNumber = sc.next();
			
			publisher.setName(publisherName);
			publisher.setAddress(address);
			publisher.setPhoneNumber(phoneNumber);
          
			publisherService.updatePublisher(publisher);
			System.out.println("publisher updated to "+publisher.getId()+" "+publisher.getName()+" "+publisher.getAddress()+" "+publisher.getPhoneNumber());
			
		}else if(sc.next().equalsIgnoreCase("n")){
			
		}	
	}
	/**
	 * deletes publisher from library management system
	 * @throws SQLException
	 */
	public void deletePublisher() throws SQLException{
		Publisher publisher = null;
		System.out.println("****Delete Publisher****");
		System.out.println("Search for publisher you want to delete by name or pubId ");
		System.out.println("1)name \n2)PublisherId");
		int nameOrpublisherId = sc.nextInt();
		if (nameOrpublisherId==1){
			System.out.println("enter the publisher name");
             String publisherName = sc.next();
			 publisher= publisherService.getPublisherByName(publisherName);
		}else if(nameOrpublisherId==2){
			System.out.println("enter the publisher Id");
			int publisherId = sc.nextInt();
			publisher= publisherService.getPublisherById(publisherId);				
		}

		System.out.println("You are about to delete this publisher : "+publisher.getName()+" are you sure");
		System.out.println("enter y/n");
		String deleteOrNot = sc.next();
		if (deleteOrNot.equals("y")){
			publisherService.removePublisher(publisher);
			System.out.println("publisher deleted ");
			this.editPublisherMenu();
		}
		else{
			this.editPublisherMenu();

		}	
	}
}
