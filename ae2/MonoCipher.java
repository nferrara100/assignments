/**
 * Programming AE2
 * Contains monoalphabetic cipher and methods to encode and decode a character.
 */
public class MonoCipher
{
	/** The size of the alphabet. */
	private final int SIZE = 26;

	/** The alphabet. */
	private char [] alphabet;
	
	/** The cipher array. */
	private char [] cipher;

	/**
	 * Instantiates a new mono cipher.
	 * @param keyword the cipher keyword
	 */
	public MonoCipher(String keyword)
	{
		//create alphabet
		alphabet = new char [SIZE];
		for (int i = 0; i < SIZE; i++)
			alphabet[i] = (char)('A' + i);
		
		// create first part of cipher from keyword
		cipher = new char [SIZE];
		for (int i = 0; i < keyword.length(); i++)
			cipher[i] = keyword.charAt(i);
		
		// create remainder of cipher from the remaining characters of the alphabet
		
		int letterIndex = 25;
		for (int i = keyword.length(); i < SIZE; i++) {
			while (keyword.indexOf((char)('A' + letterIndex)) != -1)
				letterIndex--;
			cipher[i] = (char)('A' + letterIndex);
			letterIndex--;
		}
		
		// print cipher array for testing and tutors
//		for (int i = 0; i < SIZE; i++) {
//			System.out.print(cipher[i]);
//		}
		
	}
	
	/**
	 * Encode a character
	 * @param ch the character to be encoded
	 * @return the encoded character
	 */
	public char encode(char ch)
	{
		//Checks that the text is something to be encrypted
		if (ch >= 'A' && ch <= 'Z') {
			int index = (char) (ch - 'A');
		    return cipher[index];
		}
		else {
			return ch;
		}
	}

	/**
	 * Decode a character
	 * @param ch the character to be encoded
	 * @return the decoded character
	 */
	public char decode(char ch)
	{
		//Checks that the text is something to be decrypted
		if (ch >= 'A' && ch <= 'Z') {
			int index = -1; //Only stays that way if there is an error
			//Searches for the char in the alphabet
			for (int i = 0; i < SIZE; i++) {
				if (ch == cipher[i]) {
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
