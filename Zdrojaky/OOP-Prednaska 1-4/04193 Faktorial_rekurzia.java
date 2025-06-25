public class Faktorial_rekurzia
{	
	static int fak(int n) {
	if (n >= 0) {
		if (n == 0) {
			return 1;
		}
		else {
			return n * fak(n-1);
		} 
	}
	else {
		return - 1;
	}
	}

	public static void main(String[] parametre) {
		
		int n = Zklavesnice.readInt("Zadaj n");
		
		int vysledok = fak(n);
		
		if (vysledok == -1) {
			System.out.println("Pre zaporne neviem faktorial, mimochodom funkcia fak nieco vratila,  - 1 ...");
		}
		else {
			System.out.println("Faktorial cisla " + n + " je " + vysledok);
		}
	}
}