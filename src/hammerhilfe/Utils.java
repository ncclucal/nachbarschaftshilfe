package hammerhilfe;

import java.awt.image.BufferedImage;
import java.io.File;

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
	
}
