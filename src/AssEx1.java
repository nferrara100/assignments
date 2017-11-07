import javax.swing.*;
import java.awt.event.*;

public class AssEx1 {
	
	public static void main (String args[]) {
		String customerName = JOptionPane.showInputDialog("What is the customer's name?");
		int startingBalance = Integer.parseInt(JOptionPane.showInputDialog("What is the customer's starting balance?"));
		CustomerAccount customer = new CustomerAccount(customerName, startingBalance);
		LWMGUI gui = new LWMGUI (customer);
	}
}
