public class Vynechaj_negativne_03_122
{	
	public static void main(String[] parametre) {
		
		int n = parametre.length;
		
		for (int i = 0; i < n; i++) {
		int x = Integer.parseInt(parametre[i]);
		if (x < 0) 
			continue;
		System.out.println("parametre[" + i + "] : " + x);
		 
		}
		
	}
}
