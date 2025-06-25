import java.io.*;

public class FileTest1a {	
	public static void main(String parametre[]){
	
	String meno_suboru = Zklavesnice.readString("Zadaj meno hladaneho suboru");
	
	File file = new File(meno_suboru);
	
	if (file.exists())
	{
			System.out.println("Subor " + file.getName() + " bol najdeny");
			System.out.println("Cela cesta: " + file.getAbsolutePath());
	}
	else {
		System.out.println("Nenasiel som subor");
	}
	
	
	}
		
}