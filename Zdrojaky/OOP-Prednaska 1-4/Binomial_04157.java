public class Binomial_04157
{	
  static int pocitadlo = 0;

	static int fak(int n) {
	  int vysledok = 1;
	  for (int i = 1; i <= n; i++) {
		  vysledok = vysledok * i;
	  }
	  pocitadlo++;
    return vysledok;
    }

	public static void main(String[] parametre) {
		
		int n = Integer.parseInt(parametre[0]);
		int k = Integer.parseInt(parametre[1]);
		
		int vysledok = fak(n) / ( fak(k) * fak(n-k));
		
		System.out.println("Vysledok: " + vysledok + " a funkcia fak bola volana " + pocitadlo + " krat");
		
	}
}
