package hammerhilfe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public abstract class ConnectionUtils {
	
	public static void main(String[] args) {
		System.out.println("\""+getWebpageContent("http://127.0.0.1/login.php?email=me@bla.com&password=myPw")+"\"");
	}

	public static String ip;
	
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
	
	public static String getWebpageContent(String adress) {
		try {
			URL url = new URL(adress);
			
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
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
