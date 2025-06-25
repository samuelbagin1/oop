import java.io.*;

public class FileTest2 {	
	public static void main(String parametre[]){
	
	File dir = new File(".");
	
	String[] list = dir.list();
	
	for (int i = 0; i < list.length; i++) {
		System.out.println(list[i]);
	}
	System.out.println("--------------------------------------------");
	for (int i = 0; i < list.length; i++) {
		if (list[i].endsWith(".java")) {
			File tempor = new File(dir, list[i]);
			long dlzka = tempor.length();
			System.out.println(list[i] + " [" + dlzka + "]");
		}
	}
	
	}
		
}