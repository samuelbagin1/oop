class Zaznam {
	String meno = "";
	String priezvisko = "";
	String cislo = "";
}



public class Telefonny_zoznam_03_126 
{	
	public static Zaznam readZaznam() {
		Zaznam z = new Zaznam();
		z.meno = Zklavesnice.readString("Zadaj meno");
		z.priezvisko = Zklavesnice.readString("Zadaj priezvisko");
		z.cislo = Zklavesnice.readString("Zadaj telefonne cislo");
		return z;
	}
	
	public static void printZaznam(Zaznam z) {
		System.out.println(z.meno + " " + z.priezvisko + " " + z.cislo);
	}
	
	public static void main(String[] parametre) {
			Zaznam[] zoznam = new Zaznam[100];
			int pocetZaznamov = 0;
			
			char c;
			
			do {
				c = Zklavesnice.readChar("Zvol 1 na zadanie noveho zaznamu, 2 na vzhladanie podla priezviska alebo 3 na vypis vsetkych zaznamov");
				switch (c) {
					case '1' : if (pocetZaznamov < 100) {
						zoznam[pocetZaznamov] = readZaznam();
						pocetZaznamov++;
					}
					else {
						System.out.println("Telefonny zoznam je plny");
					}
					break;
					case '2' : String priezvisko = Zklavesnice.readString("Priezvisko:");
					boolean nasiel_som = false;
					for (int i = 0; i < pocetZaznamov; i++) {
						if (priezvisko.equals(zoznam[i].priezvisko)) {
							System.out.print("Najdeny zaznam ");
							printZaznam(zoznam[i]);
							nasiel_som = true;
						}
						}
						if (!nasiel_som) {
							System.out.println("V zozname nie je nikto s priezviskom " + priezvisko);
						
						
					}
					break;
					case '3' : System.out.println("Vypis telefonneho zoznamu");
					for (int i = 0; i < pocetZaznamov; i++) {
					printZaznam(zoznam[i]);
					}
					break;
				}
				
			} while (c == '1' || c == '2' || c == '3');
		
	}
}
