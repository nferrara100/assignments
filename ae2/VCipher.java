/**
 * Programming AE2
 * Class contains Vigenere cipher and methods to encode and decode a character
 */
public class VCipher
{
	private char [] alphabet;   //the letters of the alphabet
	private final int SIZE = 26;
	private char [][] cipher;
	private int cycle = -1;
	private int keywordLength;
     
	
	/** 
	 * The constructor generates the cipher
	 * @param keyword the cipher keyword
	 */
	public VCipher(String keyword)
	{
		keywordLength = keyword.length();
		
		alphabet = new char [SIZE];
		for (int i = 0; i < SIZE; i++)
			alphabet[i] = (char)('A' + i);
		
		cipher = new char [keyword.length()][SIZE];
		
		//The first for loop creates each row in the cipher
		for (int i = 0; i < keyword.length(); i++) {
			char ch = keyword.charAt(i);
			cipher[i][0] = ch;
			
			//The second writes teh columns
			for (int x = 1; x < SIZE; x++) {
				if ((int)(ch + x - 'A') >= SIZE) {
					cipher[i][x] = (char)(ch + x - SIZE);
				}
				else {
					cipher[i][x] = (char)(ch + x);
				}
			}
		}
		
		//Can be used to see the cipher
//		for (int i = 0; i < keyword.length(); i++) {
//			for (int x = 0; x < SIZE; x++) {
//				System.out.print(cipher[i][x]);
//			}
//			System.out.println("");
//		}
	}
	/**
	 * Encode a character
	 * @param ch the character to be encoded
	 * @return the encoded character
	 */	
	public char encode(char ch)
	{
		//Checks that the text should be changed
		if (ch >= 'A' && ch <= 'Z') {
			if (cycle < keywordLength-1)
				cycle++;
			else
				cycle = 0;
			
			//Subtracts A so that it is a number equal to the array
			int index = (char) (ch - 'A');
			char returnValue = cipher[cycle][index];
		    return returnValue;
		}
		else {
			return ch;
		}
	}
	
	/**
	 * Decode a character
	 * @param ch the character to be decoded
	 * @return the decoded character
	 */  
	public char decode(char ch)
	{
		//Checks that the text should be changed
		if (ch >= 'A' && ch <= 'Z') {
			if (cycle < keywordLength-1)
				cycle++;
			else
				cycle = 0;
			
			int index = -1;
			for (int i = 0; i < SIZE; i++) {
				if (ch == cipher[cycle][i]) {
					index = i;
				}
			}
		    return alphabet[index];
		}
		else {
			return ch;
		}
	}
}
