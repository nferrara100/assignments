//This is the main class of the application

import javax.swing.*;
import java.awt.event.*;

public class AssEx1 {
	
	public static void main (String args[]) {
		String 	customerName = JOptionPane.showInputDialog("Please enter the customer's name: ");
		if ((customerName == null) || (customerName.length() < 1)) {
			System.exit(0);
		}
		//The application exits if no data or invalid data is inputed
		
		boolean notNumeric = true;
		double startingBalance = 0;
		while (notNumeric) {
		//This loop will keep going if invalid data is entered. No data will close the application just like before, however
			try {
				String startingBalanceString = JOptionPane.showInputDialog("What is the customer's starting balance? (£)");
				if ((startingBalanceString == null) || (startingBalanceString.length() < 1)) {
					System.exit(0);
					//Exits if no input was entered before clicking okay or if the box window is closed
				}
				startingBalance = Double.parseDouble(startingBalanceString);
				//Will throw an error if the input is invalid which causes the loop to repeat
				notNumeric = false;
			}
			catch (NumberFormatException e) {
				//Is called if what was inputed could not be converted to a double
				JOptionPane.showMessageDialog(null, "Please enter a number in pounds");
			}
		}	
		CustomerAccount customer = new CustomerAccount(customerName, startingBalance);
		//Saves the collected data
		LWMGUI gui = new LWMGUI (customer);
		//Starts the main GUI
	}
}
