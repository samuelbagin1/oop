class Obsah {
		String meno = "";
		String priezvisko = "";
		String cislo = "";
	
}

class Prvok_zoznamu {
	Obsah obsah;
	Prvok_zoznamu dalsi;
	
}


class Zretazeny_zoznam_simple {
	
	public static Obsah readObsah() {
		Obsah obsah = new Obsah();
		
		obsah.meno = Zklavesnice.readString("Zadajte meno");
		obsah.priezvisko = Zklavesnice.readString("Zadajte priezvisko");
		obsah.cislo = Zklavesnice.readString("Zadajte cislo");
		
		return obsah;
	}
	
	public static void printObsah(Obsah obsah) {
	System.out.println(obsah.meno + " " + obsah.priezvisko + " " + obsah.cislo);	
	}
	
	public static void printPrvok_zoznamu(Prvok_zoznamu prvok) {
		if (prvok != null) {
			printObsah(prvok.obsah);
			printPrvok_zoznamu(prvok.dalsi);
		}
	}
	
	public static void printPriezvisko(Prvok_zoznamu prvok, String priezvisko) {
		if (prvok != null) {
			if (priezvisko.equals(prvok.obsah.priezvisko)) {
				printObsah(prvok.obsah);
			}
			printPriezvisko(prvok.dalsi, priezvisko);
		}
	}
	
	public static Prvok_zoznamu insertObsah(Prvok_zoznamu prvok, Obsah obsah) {
		if (prvok == null) {
			Prvok_zoznamu vkladany = new Prvok_zoznamu();
			vkladany.obsah = obsah;
			return vkladany;
		}
		else {
			Prvok_zoznamu vkladany = new Prvok_zoznamu();
			vkladany.obsah = obsah;
			vkladany.dalsi = prvok;
			return vkladany;
		}
	}
	
	
public static void main(String[] parametre) {
	Prvok_zoznamu prvy = null;
	int pocetZaznamov = 0;
	char c;
	
	do {
		c = Zklavesnice.readChar("Zvol 1 na zadanie noveho prvku, 2 na vzhladanie prvku a 3 na vypis celeho zoznamu");
		switch (c) {
		case '1': 	prvy = insertObsah(prvy, readObsah());
					pocetZaznamov++;
					break;
		case '2': 	String priezvisko = Zklavesnice.readString("Zadajte priezvisko, podla ktoreho chcete vzhladat");
					printPriezvisko(prvy, priezvisko);
					break;
		case '3':   System.out.println("Vypis celeho telefonneho zoznamu");
					printPrvok_zoznamu(prvy); 
					break;
		}
		
	} while( c == '1' || c == '2' || c == '1');
	
}
	
	
}


 