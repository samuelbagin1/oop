public class Suma_01_09
{	static int x;
	public static void main(String[] parametre) {
		int n = Integer.parseInt(parametre[0]);
		
		if (x >=0) {
			x = 0;
		if (n > 0) {
			x = n * (n + 1) / 2;
		}
		
		System.out.println("Suma je  " + x);
		}
		else {
			System.out.println("Pls ziadne zaporne cisla");
		}
		
		
	}
}
