/**
 * Student.java
 */
package com.gcit.assingments.kemar;

/**
 * @author kemar
 *Apr 16, 20152:37:26 PM
 */
public class Student {
	private String name;
	/**
	 * @param name
	 * @param marks
	 */
	public Student(String name, int marks) {
		super();
		this.name = name;
		this.marks = marks;
	}
	private int marks;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the marks
	 */
	public int getMarks() {
		return marks;
	}
	/**
	 * @param marks the marks to set
	 */
	public void setMarks(int marks) {
		this.marks = marks;
	}

}
