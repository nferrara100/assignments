import java.awt.*;
import javax.swing.*;

/**
 * Class to define window in which attendance report is displayed.
 */
public class ReportFrame extends JFrame {
	
	//Main text area
	private JTextArea display;
	
	//Reference to logic from other classes
	private FitnessProgram program;
	
	
	//Sets up the window. Needs data from FitnessProgram class
	public ReportFrame (FitnessProgram program) {
		this.program = program;
		setTitle("Attendance Report");
		setSize(700, 200);
		display = new JTextArea();
		display.setFont(new Font("Courier", Font.PLAIN, 14));
		add(display, BorderLayout.CENTER);
		buildReport();
	}
	
	
	//Populates the main text area with text
	public void buildReport () {
		
		//Column titles
		String displayText = String.format("   %-6s%-12s%-12s%-24s%-10s%n   ", "Id", "Class", "Tutor", "Attendances", "Average Attendance");
		for (int i = 0; i < 80; i++) {
			displayText += "="; //Decorative
		}
		displayText += String.format("%n");
		
		//Prints data from each class
		FitnessClass[] classes = program.getSortedClassesList();
		for (FitnessClass fitClass : classes) {
			displayText += fitClass.generateAttendanceReportString();		
		}
		
		//Prints the bottom overall average
		displayText += String.format("%n %50s %9.2f%n", "Overall average", program.getAverageAttendance());
		display.setText(displayText);
	}
}
