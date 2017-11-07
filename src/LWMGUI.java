import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LWMGUI extends JFrame implements ActionListener {
	
	private CustomerAccount customer;
	
	private JPanel top, lowerTop, center, lowerCenter;
	
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
		customer = theCustomer;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(600,400);
	    setLocation(100,100);
	    setTitle("Lilybank Wine Merchants");
	    setLayout(new GridLayout(4,0));
	    layoutComponents();
	    this.setVisible(true);
	}
	
	private void layoutComponents () {
		topComponents();
		lowerTopComponents();
		centerComponents();
		lowerCenterComponents();
	}
	
	private void topComponents () {
		top = new JPanel();
		
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
		winePurchasedValueLabel = new JLabel("The name of my wine");
		center.add(winePurchasedLabel);
		center.add(winePurchasedValueLabel);
		
		add(center);
	}
	
	private void lowerCenterComponents () {
		lowerCenter = new JPanel();
				
		transactionAmountLabel = new JLabel("Transaction Amount: ");
		transactionAmountBox = new JTextField(5);
		transactionAmountBox.setEditable(false);
		lowerCenter.add(transactionAmountLabel);
		lowerCenter.add(transactionAmountBox);
		
		currentBalanceLabel = new JLabel("Current Balance: ");
		currentBalanceBox = new JTextField(5);
		currentBalanceBox.setEditable(false);
		lowerCenter.add(currentBalanceLabel);
		lowerCenter.add(currentBalanceBox);
		
		add(lowerCenter);
	}
	
	public void actionPerformed (ActionEvent e) {
		if(e.getSource()==processSale) {
			System.out.println("Sold");
		}
		else if(e.getSource()==processReturn) {
			System.out.println("Returned");
		}
	}
	
}