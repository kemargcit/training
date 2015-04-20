/**
 * StudentMarks.java
 */
package com.gcit.assingments.kemar;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author kemar
 *Apr 16, 20152:34:57 PM
 */
public class StudentMarks {

	Student student = null;
	Map<String, Integer> nameToNumOfMarksMap = new TreeMap<String, Integer>();//stores the name - number of marks(k,v)


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StudentMarks sm= new StudentMarks();
		sm.getAlphabeticalList();
		sm.getMeritLsit();
	}

	/**
	 * reads the file and create student objects from each line
	 */
	public ArrayList<Student> readFile(){
		File studentMarkFile = new File("studentMarks.txt");
		ArrayList<Student> studentList = new ArrayList<Student>();

		Scanner sc; 

		try {

			sc = new Scanner(studentMarkFile);
			while (sc.hasNext()) {
				String name = sc.next().trim().toLowerCase();
				int marks = sc.nextInt();
				student= new Student(name,marks);
				studentList.add(student);
				//creating the nameToNumOfMarksMap
				//if name already in map add 1 to number of marks else add name and set number of marks to 1
				if (nameToNumOfMarksMap.containsKey(name)) {
					nameToNumOfMarksMap
					.replace(name, nameToNumOfMarksMap.get(name) + 1);
				} else {
					nameToNumOfMarksMap.put(name, 1);
				}

				//System.out.println(student.getName()+student.getMarks());

			}



			sc.close();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
		return studentList;


	}
	public void getAlphabeticalList(){
		ArrayList<Student> studentList = new ArrayList<Student>();
		Map<String, Integer> alphabeticalMap = new TreeMap<String, Integer>();
		studentList=this.readFile();
		ListIterator<Student> studentListIterator = studentList.listIterator();
		while(studentListIterator.hasNext()){
			Student aStudent =studentListIterator.next();
			String name =aStudent.getName();
			int marks =aStudent.getMarks();
			///System.out.println("1 ");

			if(alphabeticalMap.containsKey(name)){
				alphabeticalMap.replace(name, alphabeticalMap.get(name)+marks);
			}
			else{
				alphabeticalMap.put(name, marks);
			}

		}

		Iterator<String> setIterator = alphabeticalMap.keySet().iterator();
		System.out.println("ALPHABETIC ORDERED LIST");
		System.out.println("Name      "+"NUMBER OF GRADES   " +"AVERAGE    ");

		while (setIterator.hasNext()) {
			String name2 = setIterator.next();//get next name
			int mark2  =alphabeticalMap.get(name2);
			int numberOfMarks = this.nameToNumOfMarksMap.get(name2);
			System.out.println(name2 + "            "+numberOfMarks+"               "+mark2/numberOfMarks);
		}

	}


	public void getMeritLsit(){
		System.out.println("MERIT ORDERED LIST");
		System.out.println("MERIT     "+ "Name      "+"NUMBER OF GRADES   " +"AVERAGE    ");

		ArrayList<Student> studentList = new ArrayList<Student>();
		Map<Integer, String> merritMap = new TreeMap<Integer, String>();


		studentList=this.readFile();
		ListIterator<Student> studentListIterator = studentList.listIterator();
		while(studentListIterator.hasNext()){
			Student aStudent =studentListIterator.next();
			String name =aStudent.getName();
			int marks =aStudent.getMarks();
			
			

			/*if(merritMap.containsValue(name)){
				int curentMarks = merritMap.keySet().iterator().next();
				merritMap.replace(curentMarks+marks,name );
			}
			else{
				merritMap.put(marks, name);
			}*/



		}


		System.out.println(merritMap);



	}


	public String getStandardDeviation(ArrayList<Double> listOfAverages,

			double average, int numOfvalues) {
		NumberFormat formatter = new DecimalFormat("#0.0");     

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
}
