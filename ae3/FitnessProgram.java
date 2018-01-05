import java.io.File;
import java.util.*;
import java.io.*;

/**
 * Maintains a list of Fitness Class objects
 * The list is initialised in order of start time
 * The methods allow objects to be added and deleted from the list
 * In addition an array can be returned in order of average attendance
 */
public class FitnessProgram {
	
	//Stores data about each class
    private FitnessClass[] classInstance;
    
    //Avoids magic numbers
    private final int MAX_CLASSES = 7;
    private final int MAX_ATTENDANCES = 5;
    
    //Constructs class. Does not set up attendances.
    //@param inputString is the input file which must conform to the specification
    public FitnessProgram (String inputString) {
    	
    	//Creates main data classes
    	classInstance = new FitnessClass[MAX_CLASSES];
    	
    	//Converts the input into useful data
   		Scanner input = new Scanner(inputString);
   		for (int i = 0; i < MAX_CLASSES; i++) {
   			
   			//Must be checked in case less classes are present than the maximum
   			if(input.hasNextLine()) {
   				
   				//Stores the datapoint
   				String inputLine = input.nextLine();
   				//Extracts the correct place in the array sorted by start hour
   				FitnessClass dummieClass = new FitnessClass(inputLine);
   				int position = dummieClass.getStartHour() - 9;
   				//Creates the real array entry
   				classInstance[position] = new FitnessClass(inputLine);
   			}
   		}
   		input.close();
    }
    
    //Gets the class when the index is known
    public FitnessClass getClass (int x) {
    	return classInstance[x];
    }
    
    //Gets the class when only the id is known
    public FitnessClass getClass (String id) {
   		for (int i = 0; i < MAX_CLASSES; i++) {
   			if (classInstance[i] != null) {
   				if (classInstance[i].getId().equals(id)) {
	   				return classInstance[i];
	   			}
   			}
   		}
   		return null;
    }
    
    //Adds the @param class
    //Returns false if unsuccessful
    public boolean addClass (FitnessClass addClass) {
    	int startHour = addClass.getStartHour();
    	if (classInstance[startHour] == null) {
    		classInstance[startHour] = addClass;
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    //@param id is the class that is deleted
    //Returns false if unsuccessful
    public boolean deleteClass (String id) {
   		for (int i = 0; i < MAX_CLASSES; i++) {
   			if (classInstance[i] != null) {
   				if (classInstance[i].getId().equals(id)) {
	   				classInstance[i] = null;
	   				return true;
	   			}
   			}
   		}
   		return false;
    }
    
    //Returns the number of classes currently inputed
    public int getNumClasses () {
    	int i = 0;
    	for (FitnessClass instance : classInstance) {
    		if (instance != null) {
    			i++;
    		}
    	}
    	return i;
    }
    
    //Adds exact attendance data for the attendance report.
    //Without this method attendance would default to zero.
    //@param attendanceData is the text of the attendanceIn.txt file
    public void addAttendanceData (String attendanceData) {
   		Scanner input = new Scanner(attendanceData);
   		for (int i = 0; i < MAX_CLASSES; i++) {
   			if(input.hasNextLine()) {
   				FitnessClass instance = getClass(input.next());
   				int[] attendances = new int[5];
   				for (int x = 0; x < MAX_ATTENDANCES; x++) {
   					attendances[x] = input.nextInt();
   				}
   				instance.setAttendances(attendances);
   			}
   		}
   		input.close();
    }
    
    //Returns the average attendance of all classes
    public double getAverageAttendance () {
    	double total = 0.0;
    	for (int i = 0; i < MAX_CLASSES; i++) {
    		if (getClass(i) != null) {
    			total += getClass(i).getAverageAttendance();
    		}
    	}
    	return total / getNumClasses();
    }
    
    //Returns the first available start time for a class
    //Returns -1 if no time is available
    public int getFirstAvailableStart () {
    	for (int i = 0; i < MAX_CLASSES; i++) {
    		if (getClass(i) == null) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    //Returns array of all classes sorted by their start time
    public FitnessClass[] getSortedClassesList () {
    	//Creates a new array as to avoid sorting the main array
    	FitnessClass[] returnArray = new FitnessClass[getNumClasses()];
    	
    	//Removes null values
    	int total = 0;
    	for (int i = 0; i < MAX_CLASSES; i++) {
    		if(classInstance[i] != null) {
    			returnArray[total] = classInstance[i];
    			total++;
    		}
    	}
    	
    	//Sorts and returns the final result
    	Arrays.sort(returnArray);
    	return returnArray;
    }
}
