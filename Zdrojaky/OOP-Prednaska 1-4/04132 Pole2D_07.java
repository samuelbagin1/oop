public class Pole2D_07
{	
	public static void main(String[] parametre) {
		
		int pocet_riadkov = Zklavesnice.readInt("Zadaj pocet riadkov matice");
		
		System.out.println("toto je pocet riadkov " + pocet_riadkov);
		
		int pocet_stlpcov = Zklavesnice.readInt("Zadaj pocet stlpcov matice");
		
		System.out.println("toto je pocet stlpcov " + pocet_riadkov);
		
		if (pocet_riadkov > 0 && pocet_stlpcov > 0) {
		
			int matica[][] = new int[pocet_riadkov][pocet_stlpcov];
			
			for (int i = 0; i < matica.length; i++) // matica.length == pocet_riadkov
			{
				for (int j = 0; j < matica[i].length; j++) { // matica[i].length == pocet_stlpcov
					matica[i][j] = Zklavesnice.readInt("zadaj matica[" + i + "][" + j + "]");
				}
			}
			
		System.out.println("Vypis matice");	
		
		for (int i = 0; i < matica.length; i++) {
			for (int j = 0; j < matica[i].length; j++) {
				System.out.print(matica[i][j] + " ");
			}
			System.out.println();
		}
		
		
		System.out.println();
		System.out.println("obsah premennej matica je " + matica);
		System.out.println("obsah premennej matica[0] je " + matica[0]);
		}
		else {
			System.out.println("Pocet riadkov alebo stlpcov nie je kladny");
		}
		
		
		
		
		
	}
}