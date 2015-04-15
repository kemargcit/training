/**
 * MyList.java
 */
package com.gcit.assingments.kemar;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author kemar Apr 14, 201510:11:11 PM
 */
public class MyList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<String> myList = new ArrayList();

		String fruit1 = "pear", fruit2 = "banana", fruit3 = "tangerine", fruit4 = "strawberry", fruit5 = "blackberry", fruit6="banana";
		myList.add(fruit1);

		myList.add(fruit2);

		myList.add(fruit3);
		myList.add(fruit4);
		myList.add(fruit5);
		
		Iterator<String> itr = myList.iterator();
	      while(itr.hasNext()) {
	         System.out.print(itr.next() + " ");
	      }
	      System.out.println();
			ListIterator<String> listitr = myList.listIterator(myList.size());

	      
	      while (listitr.hasPrevious()) {
		         System.out.print(listitr.previous()+ " ");

		}
	      myList.add(myList.indexOf("strawberry"), fruit6);
	      System.out.println();
			Iterator<String> itr2 = myList.iterator();

	      while(itr2.hasNext()) {
	         System.out.print(itr2.next() + " ");
	      }
	}

}
