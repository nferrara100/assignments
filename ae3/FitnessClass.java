/** Defines an object representing a single fitness class
 */

import java.io.*;
import java.util.*;

public class FitnessClass implements Comparable<FitnessClass> {
    
	//Data storage about the class
	private String id;
	private String name;
	private String tutorName;
	private int startHour;
	private int[] attendances;
	
	//Avoids magic numbers
	private final int WEEKS_RECORDED = 5;
	
	//Constructs the basics of the class when the data has been processed
    public FitnessClass (String id, String name, String tutorName, int startHour) {
    	this.id = id;
    	this.name = name;
    	this.tutorName = tutorName;
    	this.startHour = startHour;
    	setDefaultAttendances();
    }
    
    //Constructs the class when the data is unprocessed
    //@param inputString is a line of the input data text file
    public FitnessClass (String inputString) {
    	Scanner input = new Scanner(inputString);
    	id = input.next();
    	name = input.next();
    	tutorName = input.next();
    	startHour = input.nextInt();
    	input.close();
    	setDefaultAttendances();
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
	
	public int[] getAttendances () {
		return attendances;
	}
	
	public void setAttendances (int[] attendances) {
		this.attendances = attendances;
	}
	
	//Ensures classes without attendances data are not null
	public void setDefaultAttendances () {
		attendances = new int[5];
    	for (int i = 0; i < 5; i++) {
    		attendances[i] = 0;
    	}
	}

	//Returns the average attendance of the class
	public double getAverageAttendance () {
		int attendanceTotal = 0;
		for (int i = 0; i < WEEKS_RECORDED; i++) {
			attendanceTotal += attendances[i];
		}
		return (double) attendanceTotal / WEEKS_RECORDED;
	}
	
	//Returns a line for the class for the attendance report
	public String generateAttendanceReportString () {
		return String.format("   %-6s%-12s%-12s%-4s%-4s%-4s%-4s%-4s    %.2f%n",
				getId(), getName(), getTutorName(), attendances[0], attendances[1],
				attendances[2], attendances[3], attendances[4], getAverageAttendance());
	}
	
	//Compares the instance to others based on attendance
	//Is needed to allow sorting of instances
	public int compareTo(FitnessClass other) {
		return (int) (getAverageAttendance() - other.getAverageAttendance());
	}
}
