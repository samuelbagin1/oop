public class Parne_neparne
{	
	public static boolean parne(int n) {
		if (n == 0) {
			return true;
		}
		else {
			if (n == 1) {
				return false;
			}
			else {
				return neparne (n - 1);
			}
		}
	}
	
	public static boolean neparne(int n) {
		if (n == 0) {
			return false;
		}
		else {
			if (n == 1) {
				return true;
			}
			else {
				return parne (n - 1);
			}
		}
	}

	public static void main(String[] parametre) {
		int n = Zklavesnice.readInt("Zadaj nezaporne cele cislo");
		if (n >= 0) {
			System.out.println("Vyrok Cislo " + n + " je parne je " + parne(n));
			System.out.println("Vyrok Cislo " + n + " je neparne je " + neparne(n));
	
		}
		else {
			System.out.println("Cislo je zaporne");
		}
		
	}
}