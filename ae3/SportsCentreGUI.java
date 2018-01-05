import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

/**
 * Defines a GUI that displays details of a FitnessProgram object
 * and contains buttons enabling access to the required functionality.
 */
public class SportsCentreGUI extends JFrame implements ActionListener {
	
	/** GUI JButtons */
	private JButton closeButton, attendanceButton;
	private JButton addButton, deleteButton;

	/** GUI JTextFields */
	private JTextField idIn, classIn, tutorIn;

	/** Display of class timetable */
	private JTextArea display;

	/** Display of attendance information */
	private ReportFrame report;

	/** Names of input text files */
	private final String classesInFile = "ClassesIn.txt";
	private final String classesOutFile = "ClassesOut.txt";
	private final String attendancesFile = "AttendancesIn.txt";
	
	//Main reference...
	private FitnessProgram program;
	
	/**
	 * Constructor for AssEx3GUI class
	 */
	public SportsCentreGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Boyd-Orr Sports Centre");
		setSize(700, 300);
		display = new JTextArea();
		display.setFont(new Font("Courier", Font.PLAIN, 14));
		add(display, BorderLayout.CENTER);
		layoutTop();
		layoutBottom();

		initLadiesDay();
		initAttendances();
	}

	/**
	 * Creates the FitnessProgram list ordered by start time
	 * using data from the file ClassesIn.txt
	 */
	public void initLadiesDay() {
		try {
			String entireInputFile = new Scanner(new File("ClassesIn.txt")).useDelimiter("\\A").next();
			program = new FitnessProgram(entireInputFile);	
		}
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "ClassesIn.txt Not Found");
		}
		updateDisplay();
	}

	/**
	 * Initialises the attendances using data
	 * from the file AttendancesIn.txt
	 */
	public void initAttendances() {
		try {
			String entireInputFile = new Scanner(new File("AttendancesIn.txt")).useDelimiter("\\A").next();
			program.addAttendanceData(entireInputFile);
		}
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "AttendancesIn.txt Not Found");
		}
	}

	/**
	 * Instantiates timetable display and adds it to GUI
	 */
	public void updateDisplay() {
		
		//Column titles
		String displayText = String.format("   %-12s%-12s%-12s%-12s%-12s%-12s%-12s%n   ", "9-10", "10-11", "11-12", "12-13", "13-14", "14-15", "15-16");
		
		//Prints class names
		for (int i = 0; i < 7; i++) {
			displayText += String.format("%-12s", (program.getClass(i) != null ? program.getClass(i).getName() : "Available"));
		}
		displayText += String.format("%n   ");
		
		//Prints tutor names
		for (int i = 0; i < 7; i++) {
			displayText += String.format("%-12s", (program.getClass(i) != null ? program.getClass(i).getTutorName() : ""));
		}
		
		//Populates changes
		display.setText(displayText);
	}

	/**
	 * adds buttons to top of GUI
	 */
	public void layoutTop() {
		JPanel top = new JPanel();
		closeButton = new JButton("Save and Exit");
		closeButton.addActionListener(this);
		top.add(closeButton);
		attendanceButton = new JButton("View Attendances");
		attendanceButton.addActionListener(this);
		top.add(attendanceButton);
		add(top, BorderLayout.NORTH);
	}

	/**
	 * adds labels, text fields and buttons to bottom of GUI
	 */
	public void layoutBottom() {
		// instantiate panel for bottom of display
		JPanel bottom = new JPanel(new GridLayout(3, 3));

		// add upper label, text field and button
		JLabel idLabel = new JLabel("Enter Class Id");
		bottom.add(idLabel);
		idIn = new JTextField();
		bottom.add(idIn);
		JPanel panel1 = new JPanel();
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		panel1.add(addButton);
		bottom.add(panel1);

		// add middle label, text field and button
		JLabel nmeLabel = new JLabel("Enter Class Name");
		bottom.add(nmeLabel);
		classIn = new JTextField();
		bottom.add(classIn);
		JPanel panel2 = new JPanel();
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		panel2.add(deleteButton);
		bottom.add(panel2);

		// add lower label text field and button
		JLabel tutLabel = new JLabel("Enter Tutor Name");
		bottom.add(tutLabel);
		tutorIn = new JTextField();
		bottom.add(tutorIn);

		add(bottom, BorderLayout.SOUTH);
	}

	/**
	 * Processes adding a class
	 */
	public boolean processAdding() {
	    if(idIn.getText().isEmpty()) {
	    	JOptionPane.showMessageDialog(null, "Please enter a class Id");
	    	return false;
	    }
	    if(program.getClass(idIn.getText()) != null) {
	    	JOptionPane.showMessageDialog(null, "There is already a class with that id");
	    	return false;
	    }
	    if(program.getFirstAvailableStart() == -1) {
	    	JOptionPane.showMessageDialog(null, "There are no available timeslots. Delete a class first.");
	    	return false;
	    }
	    FitnessClass addClass = new FitnessClass(idIn.getText(), classIn.getText(), tutorIn.getText(), program.getFirstAvailableStart());
	    program.addClass(addClass);
	    updateDisplay();
	    return true;
	}

	/**
	 * Processes deleting a class
	 */
	public boolean processDeletion() {
	    if(idIn.getText().isEmpty()) {
	    	JOptionPane.showMessageDialog(null, "Please enter a class Id");
	    	return false;
	    }
	    if(program.getClass(idIn.getText()) == null) {
	    	JOptionPane.showMessageDialog(null, "There is no class with that Id to delete");
	    	return false;
	    }
	    
	    program.deleteClass(idIn.getText());
	    updateDisplay();
	    return true;
	}

	/**
	 * Instantiates a new window and displays the attendance report
	 */
	public void displayReport() {
		report = new ReportFrame(program);
		report.setVisible(true);
	}

	/**
	 * Writes lines to file representing class name, 
	 * tutor and start time and then exits from the program
	 */
	public void processSaveAndClose() {
		String outputText = "";
	    FitnessClass[] FitClasses = program.getSortedClassesList();
	    
	    //Calculates each line of the file
	    for (FitnessClass FitClass : FitClasses) {
	    	outputText += FitClass.getId() + " " + FitClass.getName() + " " + FitClass.getTutorName() + " " + FitClass.getStartHour() + String.format("%n");
	    }
	    try {
	    	//Actually writes the file and then exits
		    FileWriter writer = new FileWriter("ClassesOut.txt");
		    writer.write(outputText);
		    writer.close();
		    System.exit(0);
	    }
	    catch (IOException e) {
	    	//This error should not occur under normal circumstances
	    	JOptionPane.showMessageDialog(null, "Unable to save file");
	    }
	}

	/**
	 * Process button clicks.
	 * @param ae the ActionEvent
	 */
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==attendanceButton) {
			displayReport();
		}
		if(ae.getSource()==addButton) {
			processAdding();
		}
		if(ae.getSource()==deleteButton) {
			processDeletion();
		}
		if(ae.getSource()==closeButton) {
			processSaveAndClose();
		}
	}
}
