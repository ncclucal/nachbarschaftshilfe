package hammerhilfe;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Library {
	
	private static ArrayList<NamedImage> namedImages;
	private static ArrayList<Entry> entries;
	
	public static void load() {
		System.out.println("Loading Images");
		namedImages = new ArrayList<NamedImage>();
		File folder = new File("img");
		File[] fs = folder.listFiles();
		for(File f : fs) {
			namedImages.add(NamedImage.load(f));
		}
		
		System.out.println("Images: "+namedImages);
		
		System.out.println("Loading Image Name Library");
		entries = new ArrayList<>();
		
		String str = Utils.loadFile("lib.txt");
		String[] lines = str.split("\n");
		
		for(String line : lines) {
			String[] a = line.split("=");
			
			NamedImage namedImage = getNamedImage(a[0]);
			
			if(namedImage == null) {
				System.out.println("Image not found: "+a[0]);
			}
			
			entries.add(new Entry(namedImage, a[1]));
		}
	}
	
	private static NamedImage getNamedImage(String str) {
		for(NamedImage namedImage : namedImages) {
			if(namedImage.getName().equals(str))return namedImage;
		}
		return null;
	}
	
	public static BufferedImage getImageForString(String string) {
		for(Entry e : entries) {
			if(Utils.stringContainsAnyWord(string, e.getAliases())) {
				return e.getNamedImage().getImage();
			}
		}
		return null;
	}
	
	public static class Entry{
		
		private NamedImage namedImage;
		private ArrayList<String> aliases;
		
		public Entry(NamedImage namedImage, String entries){
			this.namedImage = namedImage;
			this.aliases = Utils.toList(entries.split(","));
		}
		
		public NamedImage getNamedImage() {
			return namedImage;
		}
		
		public ArrayList<String> getAliases() {
			return aliases;
		}
		
	}
	
	public static class NamedImage{
		
		private String name;
		private BufferedImage image;
		
		public NamedImage(String name, BufferedImage img) {
			this.name = name;
			this.image = img;
		}
		
		public BufferedImage getImage() {
			return image;
		}
		
		public String getName() {
			return name;
		}
		
		public static NamedImage load(File file) {
			File folder = file.getParentFile();
			
			BufferedImage img;
			try {
				img = ImageIO.read(file);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			
			String name = file.getAbsolutePath().substring(folder.getAbsolutePath().length() + 1);
			
			return new NamedImage(name, img);
		}
		
		public String toString() {
			return name;
		}
		
	}
	
}
