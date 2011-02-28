package net.matmas.util;

import java.io.File;

/**
 * This class contains some simple string management functions.
 * @author matmas
 */
public class StringTools {
	
	/**
	 * This method converts strings like "deCamelCase" to "De camel case"
	 * @param string string to be converted
	 */
	public static String deCamelCase(String string) {
		StringBuffer sb = new StringBuffer(string);
		sb.setCharAt(0, string.substring(0, 1).toUpperCase().charAt(0)); //first letter -> uppercase
		for (int i = 1; i < sb.length(); i++) {
			String currentChar = sb.substring(i, i + 1); //for each char in string:
			
			if (currentChar.equals(currentChar.toUpperCase())) { //if current char is uppercase
				sb.insert(i++, " "); //insert space
				sb.setCharAt(i, currentChar.toLowerCase().charAt(0)); //current char -> lowercase
			}
		}
		return sb.toString();
	}
	
	/**
	 * Returns extension of a file
	 * i.e. from File("example.html.txt") returns "txt"
	 * @param file file to get the extension from
	 * @return extension of the file
	 */
	public static String getExtension(File file) {
		return getExtension(file.getName());
	}

	private static String getExtension(String filename) {
		String ext = null;
        String s = filename;
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }

		if (ext != null) {
			return ext;
		}
		else {
			return "";
		}
	}

	public static String getExtensionCutOut(String filename) {
		String extension = getExtension(filename);
		String result = filename.substring(0, filename.length() - 1 - extension.length());
		return result;
	}
}
