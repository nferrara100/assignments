import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

/** 
 * Programming AE2
 * Class to display cipher GUI and listen for events
 */
public class CipherGUI extends JFrame implements ActionListener
{
	//instance variables which are the components
	private JPanel top, bottom, middle;
	private JButton monoButton, vigenereButton;
	private JTextField keyField, messageField;
	private JLabel keyLabel, messageLabel;

	//application instance variables
	//including the 'core' part of the textfile filename
	//some way of indicating whether encoding or decoding is to be done
	private MonoCipher mcipher;
	private VCipher vcipher;
	
	private boolean decode;
	private String key;
	private String filename;
	
	/**
	 * The constructor adds all the components to the frame
	 */
	public CipherGUI()
	{
		this.setSize(500,150);
		this.setLocation(100,100);
		this.setTitle("Cipher GUI");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.layoutComponents();
	}
	
	/**
	 * Helper method to add components to the frame
	 */
	public void layoutComponents()
	{
		//top panel is yellow and contains a text field of 10 characters
		top = new JPanel();
		top.setBackground(Color.yellow);
		keyLabel = new JLabel("Keyword : ");
		top.add(keyLabel);
		keyField = new JTextField(10);
		top.add(keyField);
		this.add(top,BorderLayout.NORTH);
		
		//middle panel is yellow and contains a text field of 10 characters
		middle = new JPanel();
		middle.setBackground(Color.yellow);
		messageLabel = new JLabel("Message file : ");
		middle.add(messageLabel);
		messageField = new JTextField(10);
		middle.add(messageField);
		this.add(middle,BorderLayout.CENTER);
		
		//bottom panel is green and contains 2 buttons
		
		bottom = new JPanel();
		bottom.setBackground(Color.green);
		//create mono button and add it to the top panel
		monoButton = new JButton("Process Mono Cipher");
		monoButton.addActionListener(this);
		bottom.add(monoButton);
		//create vigenere button and add it to the top panel
		vigenereButton = new JButton("Process Vigenere Cipher");
		vigenereButton.addActionListener(this);
		bottom.add(vigenereButton);
		//add the top panel
		this.add(bottom,BorderLayout.SOUTH);
	}
	
	/**
	 * Listen for and react to button press events
	 * (use helper methods below)
	 * @param e the event
	 */
	public void actionPerformed(ActionEvent e)
	{
		//Is inside if statements to make sure if something fails everything stops
		if (getKeyword()) {
			if (processFileName()) {
				if(e.getSource()==monoButton) {
					processFile(false);
				}
				if(e.getSource()==vigenereButton) {
					processFile(true);
				}
			}
		}
	}
	
	/** 
	 * Obtains cipher keyword
	 * If the keyword is invalid, a message is produced
	 * @return whether a valid keyword was entered
	 */
	private boolean getKeyword()
	{
		key = keyField.getText();
		//Checks that the key is not empty and only contains capitals. If it does it runs this error block.
		if(key.length() < 1 || key.matches(".*[^A-Z].*")) {
			JOptionPane.showMessageDialog(null, "Please enter a keyword in capital letters");
			keyField.setText("");
			return false;
		}
	    return true;
	}
	
	/** 
	 * Obtains filename from GUI
	 * The details of the filename and the type of coding are extracted
	 * If the filename is invalid, a message is produced 
	 * The details obtained from the filename must be remembered
	 * @return whether a valid filename was entered
	 */
	private boolean processFileName()
	{
		filename = messageField.getText();
		//Removes the final letter from the filename so that the old file is not overwritten
		char finalChar = filename.charAt(filename.length()-1);
		//Ensures the final character is something to be encrypted or decrypted
		if (finalChar != 'P' && finalChar != 'C') {
			JOptionPane.showMessageDialog(null, "The final character of the filename must be either 'P' or 'C'.");
			messageField.setText("");
			return false;
		}
		if (finalChar == 'P') {
			decode = false;
		}
		if (finalChar == 'C') {
			decode = true;
		}
	    return true;
	}
	
	/** 
	 * Reads the input text file character by character
	 * Each character is encoded or decoded as appropriate
	 * and written to the output text file
	 * @param vigenere whether the encoding is Vigenere (true) or Mono (false)
	 * @return whether the I/O operations were successful
	 */
	private boolean processFile(boolean vigenere)
	{
		//Tries to write the file, but catches all errors
		try {
			//Sets up the file writers
			FileReader reader = new FileReader(filename + ".txt");
			FileWriter writer = new FileWriter(filename.substring(0, filename.length()-1) + (decode?"D":"C") + ".txt");
			LetterFrequencies freqs = new LetterFrequencies();
			if(vigenere) {
				vcipher = new VCipher (key);
			}
			else {
				mcipher = new MonoCipher (key);
			}
			
			boolean done = false;
			//Continues to loop until the end of file value is given
			while (!done) {
				int next = reader.read();
				//-1 means the end of the file
				if(next == -1)
					done = true;
				else {
					char charToWrite = 'A';
					if (decode) {
						if(vigenere) {
							charToWrite = vcipher.decode((char) next);
						}
						else {
							charToWrite = mcipher.decode((char) next);
						}
					}
					else {
						if(vigenere) {
							charToWrite = vcipher.encode((char) next);
						}
						else {
							charToWrite = mcipher.encode((char) next);
						}
					}
					//Stores the char in this object so that it can be used later
					freqs.addChar(charToWrite);
					writer.write(charToWrite);
				}
					
			}
			
			reader.close();
			writer.close();
			
			//Writes the frequency file seperately
			FileWriter freqsWriter = new FileWriter(filename.substring(0, filename.length()-1) + "F.txt");
			freqsWriter.write(freqs.getReport());
			freqsWriter.close();
			return true;
		}
		catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Your file could not be found. Please check the filename and try again. ");
			messageField.setText("");
		}
		catch (Exception ex){
			JOptionPane.showMessageDialog(null, ex.toString());
			System.out.println(ex.fillInStackTrace());
			messageField.setText("");
		}
		return false;
	}
}
