/** Defines an object representing a single fitness class
 */

import java.io.*;
import java.util.*;

public class FitnessClass implements Comparable<FitnessClass> {
    
	private String id;
	private String name;
	private String tutorName;
	private int startHour;
	private int[] attendance;
	private final int WEEKS_RECORDED = 5;
	
    public FitnessClass (String id, String name, String tutorName, int startHour) {
    	this.id = id;
    	this.name = name;
    	this.tutorName = tutorName;
    	this.startHour = startHour;
    }
    
    public FitnessClass (String inputString) {
    	System.out.println(inputString);
    	try {
    		Scanner input = new Scanner(inputString);
    		id = input.next();
    		name = input.next();
    		tutorName = input.next();
    		startHour = input.nextInt();
    	}
    	catch (InputMismatchException e) {
			// report input error to user
    		System.out.println(e.getMessage());
			System.out.println("Invalid Input");// Make popup
		}
    }
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTutorName() {
		return tutorName;
	}
	
	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}
	
	public int getStartHour() {
		return startHour;
	}
	
	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public double getAverageAttendance () {
		int attendanceTotal = 0;
		for (int i = 0; i < WEEKS_RECORDED; i++) {
			attendanceTotal += attendance[i];
		}
		return (double) attendanceTotal / WEEKS_RECORDED;
	}
	
	public String generateAttendanceReportString () {
		return ""; //stub
	}
	
	public int compareTo(FitnessClass other) {
		return 0; // replace with your code
	}
}
