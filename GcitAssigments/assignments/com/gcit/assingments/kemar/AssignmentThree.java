/**
 * AssignmentThree.java
 */
package com.gcit.assingments.kemar;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author kemar Apr 14, 20159:58:30 PM
 */
public class AssignmentThree {
	NumberFormat formatter = new DecimalFormat("#0.0");     

	/**
	 * Calculates the standard deviation of the averages
	 * 
	 * @param averageSet the data set
	 * @param average the average the data set
	 * @param numOfvalues the number of values in the data set
	 * @return the standard deviation of the data set
	 */
//TODO fix standard deviation
	
	public String getStandardDeviation(ArrayList<Double> listOfAverages,
			double average, int numOfvalues) {
		double standardDeviation = 0.0;
		double sum = 0.0;
		Iterator<Double> averageListIterator = listOfAverages.iterator();

		ArrayList<Double> variances = new ArrayList<Double>();
		// third attempt
		while (averageListIterator.hasNext()) {
			variances.add(averageListIterator.next() - average);
		}

		Iterator<Double> variancesIterator = variances.iterator();

		while (variancesIterator.hasNext()) {
			sum += Math.pow(variancesIterator.next(), 2);
		}
		standardDeviation = Math.sqrt(sum / numOfvalues - 1);

		return formatter.format(standardDeviation);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		AssignmentThree assignmentThree = new AssignmentThree();
		
		ArrayList<String> nameToMarksList = new ArrayList<String>();//stores each line of the file
		
		Map<String, Integer> nameToNumOfMarksMap = new TreeMap<String, Integer>();//stores the name - number of marks(k,v)

		Map<String, Integer> nameToTotalMarksMap = new TreeMap<String, Integer>();//stores name - average (K,V)
		
		Map<Double, String> averageToNameMap = new TreeMap<Double, String>(Collections.reverseOrder());//stores average to name(K,V) in reversed order
		
		File studentMarkFile = new File("studentMarks.txt");

		int line = 0;//represents a line of the file
		Scanner sc; 

		try {
			
			sc = new Scanner(studentMarkFile);
			while (sc.hasNext()) {

				nameToMarksList.add(sc.nextLine().trim());//add each line to a list
				
				//get names and marks by reading each record in namesToMarksList and splitting by " "
				String name = nameToMarksList.get(line).split(" ")[0];
				int marks = Integer.parseInt(nameToMarksList.get(line).split(" ")[1]);
				
				//creating the nameToNumOfMarksMap
						//if name already in map add 1 to number of marks else add name and set number of marks to 1
				if (nameToNumOfMarksMap.containsKey(name)) {
					nameToNumOfMarksMap
							.replace(name, nameToNumOfMarksMap.get(name) + 1);
				} else {
					nameToNumOfMarksMap.put(name, 1);
				}
				

				//creating nameToTotalMap
						//if name already in map replace with name and addition of current sum of marks and new marks
				if (nameToTotalMarksMap.containsKey(name)) {
					nameToTotalMarksMap.replace(name, nameToTotalMarksMap.get(name)
							+ marks);
				} else {
					nameToTotalMarksMap.put(name, marks);

				}

				line++;//increment line so we read each line of the file
			}
			
			sc.close();


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		assignmentThree.getAlphabeticOrderList(nameToNumOfMarksMap, nameToTotalMarksMap, averageToNameMap);

		assignmentThree.getMeritList(assignmentThree, nameToNumOfMarksMap, averageToNameMap);
	}

	/**
	 * get a list of student organized by merit prints the general statistics
	 * @param assignmentThree class object
	 * @param nameToNumMarksMap the map of name(K)-number of marks(V)
	 * @param averageToNameMap the map of Average marks(K) to name(V) of each student
	 */
	public  void getMeritList(AssignmentThree assignmentThree,
			Map<String, Integer> nameToNumMarksMap, Map<Double, String> averageToNameMap) {
		System.out.println("Merit Order");
		Iterator<Double> setIterator2 = averageToNameMap.keySet().iterator();
		int position = 1;
		int totalStudentAverage = 0;

		while (setIterator2.hasNext()) {
			double average = setIterator2.next();
			String name = averageToNameMap.get(average);
			int numberOfMarks = nameToNumMarksMap.get(name);

			System.out.println(position + " " + name + " " + numberOfMarks
					+ " " + average);
			System.out.println(" ");
			position++;
			totalStudentAverage += average;
		}
		
		getGeneralStatistcs(assignmentThree, nameToNumMarksMap, averageToNameMap,
				totalStudentAverage);
	}

	/**
	 * prints the the number of students, the average of the student averages,
	 *  and the standard deviation of the student averages
	 * @param assignmentThree class object
	 * @param nameToNumMarksMap the map of name(K)-number of marks(V)
	 * @param averageToNameMap the map of Average marks(K) to name(V) of each student
	 * @param totalStudentAverage the total of all the students averages
	 */
	public void getGeneralStatistcs(AssignmentThree assignmentThree,
			Map<String, Integer> nameToNumMarksMap,
			Map<Double, String> averageToNameMap, int totalStudentAverage) {
		
		int numberOfStudents = nameToNumMarksMap.size();//get the number of students
		
		ArrayList<Double> listOfAverages = new ArrayList<Double>(averageToNameMap.keySet());

		//Set<Double> setOfAverages = averageToNameMap.keySet();//get a set of student averages
		
		
		double average = (double) totalStudentAverage / numberOfStudents;// calculate the average of the student averages
		System.out.println("Number of Students : " + numberOfStudents);
		System.out.println("The Average Student Mark is : " + formatter.format(average));
		System.out.println("The Stadard deviation of the Student Average is :"
				+ assignmentThree.getStandardDeviation(listOfAverages, average,
						numberOfStudents));
	}

	/**
	 * gets a list of student name and marks n alphabetic order and creates  Average marks(K) to name(V) map
	 * @param nameToNumMarksMap the map of name(K)-number of marks(V)
	 * @param nameToTotalMarksMap the map of name(K) to average(V) of each students
	 * @param averageToNameMap the map of Average marks(K) to name(V) of each student
	 * 
	 */
	public  void getAlphabeticOrderList(Map<String, Integer> nameToNumMarksMap,
			Map<String, Integer> nameToTotalMarksMap, Map<Double, String> averageToNameMap) {
		System.out.println("Alphabetic Order");
		Iterator<String> setIterator = nameToTotalMarksMap.keySet().iterator();
		while (setIterator.hasNext()) {
			String name = setIterator.next();//get next name
			int numberOfMarks = nameToNumMarksMap.get(name); //number of marks for that name
			double averageMarks = nameToTotalMarksMap.get(name) / numberOfMarks; //calculate the student average
			System.out.println(name + " " + numberOfMarks + " " + averageMarks); //print list
			System.out.println(" ");


			if (averageToNameMap.containsKey(averageMarks)){
				
				averageToNameMap.put(averageMarks, name);
				
				//averageToNameMap.put(averageMarks, name);//create the  Average marks(K) to name(V) map

			}
			else{
			averageToNameMap.put(averageMarks, name);//create the  Average marks(K) to name(V) map
			}
		}
	}

}
