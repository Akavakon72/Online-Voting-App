/*
 * EncryptString.java
 *
 * Created on December 4, 2011, 5:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package encdec;

/**
 * 
 * @author k
 */
public class EncDecString {

	/** Creates a new instance of EncryptString */
	public String Encrypt(String strValue) {
		return genNewString(strValue);
	}

	public String Decrypt(String strValue) {
		return genNewString(strValue);
	}

	private String genNewString(String strValue) {
		int i, j, k;
		String strNewStringValue = "";
		char[] chrValue = strValue.toCharArray();

		for (i = 0; i <= chrValue.length - 1; i++) {
			// take the next character in the string
			j = (int) chrValue[i];

			// find out the character code
			k = (int) j;

			if (k >= 97 && k <= 109) {
				// a ... m inclusive become n ... z
				k = k + 13;
			} else if (k >= 110 && k <= 122) {
				// n ... z inclusive become a ... m
				k = k - 13;
			} else if (k >= 65 && k <= 77) {
				// A ... m inclusive become n ... z
				k = k + 13;
			} else if (k >= 78 && k <= 90) {
				// N ... Z inclusive become A ... M
				k = k - 13;
			} else if (k >= 48 && k <= 52) {
				// 0 ... 5 inclusive become 6 ... 9
				k = k + 5;
			} else if (k >= 53 && k <= 57) {
				// 6 ... 9 inclusive become 0 ... 5
				k = k - 5;
			}
			// add the current character to the string returned by the function
			strNewStringValue = strNewStringValue + (char) k;

		}

		return strNewStringValue;
	}
}
