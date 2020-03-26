package hammerhilfe;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public abstract class Utils {
	
	public static boolean stringContainsAnyChar(String string, String chars) {
		char[] ca = chars.toCharArray();
		
		int i=0;
		while(i < ca.length) {
			if(stringContainsChar(string, ca[i]))return true;
			i++;
		}
		return false;
	}
	
	public static boolean stringContainsChar(String string, char c) {
		char[] ca = string.toCharArray();
		int i=0;
		while(i < ca.length) {
			if(ca[i] == c) {
				return true;
			}
			i++;
		}
		return false;
	}
	
	public static BufferedImage loadImage(String path) {
		return loadImage(new File(path));
	}
	
	public static BufferedImage loadImage(File file) {
		try {
			return ImageIO.read(file);
		}catch (Exception e) {
			return null;
		}
	}
	
	public static String toString(char[] arr) {
		String str = "";
		int i=0;
		while(i < arr.length) {
			str += arr[i];
			i++;
		}
		return str;
	}
	
	public static final String invalidCharacters = "&=";
	
	public static void checkInvalid(String str) {
		if(stringContainsAnyChar(str, invalidCharacters)) {
			throw new InvalidInputException("Die Eingabe Enthält ungültige Zeichen");
		}
	}
	
	public static String loadFile(String path) {
		return loadFile(new File(path));
	}
	
	public static String loadFile(File file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String out = "";
			String next = null;
			while(true) {
				next = br.readLine();
				if(next == null) {
					break;
				}
				out += next + "\n";
			}
			br.close();
			return out;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static ArrayList<String> toList(String[] entries) {
		ArrayList<String> list = new ArrayList<String>();
		for(String str : entries) {
			list.add(str);
		}
		return list;
	}
	
	public static boolean stringContainsAnyWord(String string, ArrayList<String> words) {
		for(String word : words) {
			if(stringContainsWord(string, word))return true;
		}
		return false;
	}
	
	public static boolean stringContainsWord(String string, String word) {
		String[] arr = string.split(" ");
		for(String str : arr) {
			if(str.equalsIgnoreCase(word))return true;
		}
		return false;
	}
	
}
