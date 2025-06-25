public class Faktorial_03_98
{	
	public static void main(String[] parametre) {
		int n = Integer.parseInt(parametre[0]);
		int prod = 1;
		if (n > 0) {
			for (int i = 1; i <= n; i++) {
				prod = prod * i;
				
			}
		System.out.println("Faktorial cisla " + n + " je cislo " + prod);			
		}
		else {
			System.out.println("Pls nabuduce zadaj kladne cislo");
		}
		
		
	}
}

