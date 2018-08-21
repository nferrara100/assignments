//This class implements the main GUI for the application

import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

public class LWMGUI extends JFrame implements ActionListener {
	
	//This is where data processing occurs
	private CustomerAccount customer;
	
	//Each is a separate row of the GUI
	private JPanel top, lowerTop, center, lowerCenter;
	
	//Each further paragraph of instance variables contains the components for the next line of the GUI
	private JLabel nameLabel;
	private JTextField nameBox;
	private JLabel quantityLabel;
	private JTextField quantityBox;
	private JLabel priceLabel;
	private JTextField priceBox;
	
	private JButton processSale;
	private JButton processReturn;
	
	private JLabel winePurchasedLabel;
	private JLabel winePurchasedValueLabel;
	
	private JLabel transactionAmountLabel;
	private JTextField transactionAmountBox;
	private JLabel currentBalanceLabel;
	private JTextField currentBalanceBox;
	
	public LWMGUI(CustomerAccount theCustomer) {
		//This allows the information collected in the previous screen to be used here
		customer = theCustomer;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(600,200);
	    setLocation(100,100);
	    setTitle("Lilybank Wine Merchants: " + customer.getName());
	    setLayout(new GridLayout(4,0));
	    layoutComponents();
	    this.setVisible(true);
	}
	
	//Each method in this function is a different line
	private void layoutComponents () {
		topComponents();
		lowerTopComponents();
		centerComponents();
		lowerCenterComponents();
	}
	
	private void topComponents () {
		top = new JPanel();
		
		//Collects the name of the wine
		nameLabel = new JLabel("Name: ");
		nameBox = new JTextField(15);
		top.add(nameLabel);
		top.add(nameBox);
		
		quantityLabel = new JLabel("Quantity: ");
		quantityBox = new JTextField(5);
		top.add(quantityLabel);
		top.add(quantityBox);
		
		priceLabel = new JLabel("Price: £");
		priceBox = new JTextField(5);
		top.add(priceLabel);
		top.add(priceBox);
		
		add(top);
	}
	
	private void lowerTopComponents () {
		lowerTop = new JPanel();
		
		processSale = new JButton("Process Sale");
		processSale.addActionListener(this);		
		processReturn = new JButton("Process Return");
		processReturn.addActionListener(this);
		lowerTop.add(processSale);
		lowerTop.add(processReturn);
		
		add(lowerTop);
	}
	
	private void centerComponents () {
		center = new JPanel();
		
		winePurchasedLabel = new JLabel("Wine Purchased: ");
		winePurchasedValueLabel = new JLabel("");
		center.add(winePurchasedLabel);
		center.add(winePurchasedValueLabel);
		
		add(center);
	}
	
	private void lowerCenterComponents () {
		lowerCenter = new JPanel();
				
		transactionAmountLabel = new JLabel("Transaction Amount: ");
		//Presumes 10 digits will be enough
		transactionAmountBox = new JTextField(10);
		//It cannot be edited because the rest of the interface does that
		transactionAmountBox.setEditable(false);
		lowerCenter.add(transactionAmountLabel);
		lowerCenter.add(transactionAmountBox);
		
		currentBalanceLabel = new JLabel("Current Balance: ");
		currentBalanceBox = new JTextField(10);
		currentBalanceBox.setEditable(false);
		currentBalanceBox.setText(customer.getBalance());
		lowerCenter.add(currentBalanceLabel);
		lowerCenter.add(currentBalanceBox);
		
		add(lowerCenter);
	}
	
	public void actionPerformed (ActionEvent e) {
		
		String wineName = nameBox.getText();
		double winePrice;
		int wineQuantity;
		try { //This is in case none numeric values were inputed
			winePrice = Double.parseDouble(priceBox.getText());
			wineQuantity = Integer.parseInt(quantityBox.getText());
			if (winePrice < 0 || wineQuantity < 0) {
			//This prevents negative sales
				JOptionPane.showMessageDialog(this, "Please enter valid input");
			}
			else {
				//Creates the object used to store information about the wine
				Wine localWine = new Wine (wineName, winePrice, wineQuantity);
				
				String transactionTotal = "";
				//This code allows us to see what button was clicked
				if(e.getSource()==processSale) {
					transactionTotal = customer.sale(localWine.getQuantity(), localWine.getPriceInt());
				}
				else if (e.getSource() == processReturn) {
					transactionTotal = customer.refund(localWine.getQuantity(), localWine.getPriceInt());
				}
				
				//Resets the displayed transaction information
				winePurchasedValueLabel.setText(localWine.getName());
				currentBalanceBox.setText(customer.getBalance());
				transactionAmountBox.setText(transactionTotal);
			}
		}
		catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Please enter valid input");
		}
		//Resets the rest of the GUI regardless of whether the transaction was successful
		nameBox.setText("");
		quantityBox.setText("");
		priceBox.setText("");
	}
	
}