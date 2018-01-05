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
    private FitnessClass[] classInstance;
    private final int MAX_CLASSES = 7;
    
    public FitnessProgram (String inputString) {
    	//System.out.println(inputString);
    	classInstance = new FitnessClass[MAX_CLASSES-1];
   		Scanner input = null;
   		input = new Scanner(inputString);
   		for (int i = 0; i < MAX_CLASSES; i++) {
   			if(input.hasNextLine()) {
   				classInstance[i] = new FitnessClass(input.nextLine());
   			}
   		}
   		input.close();
    }
    
    FitnessClass getClass (int x) {
    	return classInstance[x];
    }
    
    //Build list ordered
    
    //return num classes
    
    //return each member of list
}
