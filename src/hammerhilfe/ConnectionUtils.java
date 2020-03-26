package hammerhilfe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class ConnectionUtils {
	
	public static void main(String[] args) {
		init();
	}

	public static String ip = "http://127.0.0.1/";
	
	public static void init() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("IP.txt")));
			ip = br.readLine();
			br.close();
			System.out.println("IP: "+ip);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean canConnect(String ip) {
		try {
			new URL(ip).openStream();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @return null if connection fails
	 */
	public static String getWebpageContent(String adress) {
		if(ip == null) {
			throw new NullPointerException("ConnectionUtils not initialized!");
		}
		try {
			URL url = new URL(ip+adress);
			
			Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
			
			String str = "";
			
			boolean a = false;
			while(scanner.hasNextLine()) {
				if(a) {
					str += "\n";
				}
				str += scanner.nextLine();
				a = true;
			}
			
			scanner.close();
			
			return str;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<AngebotInfo> get(String type){
		ArrayList<AngebotInfo> list = new ArrayList<>();
		
		String cont = getWebpageContent("query_articles.php?typ="+type);
		String[] arr = cont.split("<br>");
		for(String str : arr) {
			//TODO: Split only at first -
			String[] s = str.split("-");
			
			AngebotInfo ai = new AngebotInfo(s[1], s[0]);
			list.add(ai);
		}
		
		return list;
	}
	
}
