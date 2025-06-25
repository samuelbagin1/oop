public class Trojuholnik_03_117
{	
	public static void main(String[] parametre) {
		
		int vyska = Integer.parseInt(parametre[0]);
a: 
		for (int riadok = 1; riadok <= vyska; riadok++) {
			for (int stlpec = 1; stlpec <= riadok; stlpec++) {
				if (riadok > 15)
					break a;
				System.out.print("*");
				if (stlpec >= 10)
					break;
			}
			System.out.println("Toto bol riadok " + riadok);
		}
		
	}
}
