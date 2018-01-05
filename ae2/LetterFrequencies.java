/**
 * Programming AE2
 * Processes report on letter frequencies
 */
public class LetterFrequencies
{
	/** Size of the alphabet */
	private final int SIZE = 26;
	
	/** Count for each letter */
	private int [] alphaCounts;
	
	/** The alphabet */
	private char [] alphabet; 
												 	
	/** Average frequency counts */
	private double [] avgCounts = {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0,
							       0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0,  
								   6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1};

	/** Character that occurs most frequently */
	private char maxCh;

	/** Total number of characters encrypted/decrypted */
	private int totChars;
	
	/**
	 * Instantiates a new letterFrequencies object.
	 */
	public LetterFrequencies()
	{
		//create alphabet
		alphabet = new char [SIZE];
		for (int i = 0; i < SIZE; i++)
			alphabet[i] = (char)('A' + i);
		
		alphaCounts = new int [SIZE];
	}
		
	/**
	 * Increases frequency details for given character
	 * @param ch the character just read
	 */
	public void addChar(char ch)
	{
		if (ch >= 'A' && ch <= 'Z') {
		    alphaCounts[(int)(ch - 'A')]++;
		}
	}
	
	/**
	 * Gets the maximum frequency
	 * @return the maximum frequency
	 */
	private double getMaxPC()
        {
		int highest = -1; //Only stays like that in an error
		for (int i = 0; i < SIZE; i++) {
			if (alphaCounts[i] > highest) {
				highest = alphaCounts[i];
				maxCh = (char) i;
				maxCh += 'A';
			}
			totChars += alphaCounts[i];
		}
		return (double) highest / (double) totChars;
	}
	
	/**
	 * Returns a String consisting of the full frequency report
	 * @return the report
	 */
	public String getReport()
	{
		totChars = 0;
		double max = getMaxPC();
		
		//The top column
		String returnString = "LETTER ANALYSIS\r\n" + 
				"\r\n" + 
				"Letter\tFreq\tFreq%\tAvgFreq%\tDiff\r\n" +
				"\r\n";
		//The data rows
		for(int i = 0; i < SIZE; i++) {
			double freq = Math.round(1000 * ((float)alphaCounts[i] / (float)totChars)) / 10.0;
			double diff = Math.round(10 * (freq - avgCounts[i])) / 10.0;
			returnString += String.format("%-10c%-10s%-10s%-10s%-10s", alphabet[i], alphaCounts[i], freq, avgCounts[i], diff);
			returnString += "\r\n";
		}
		
		//The last row
		returnString += "\r\n";
		returnString += String.format("The most frequent letter is %s at %s", maxCh, (Math.round(1000* ((float)alphaCounts[maxCh-'A'] / (float)totChars)) / 10.0));
		returnString += "%.\r\n";
	    return returnString;
	}
}
