import java.io.*;

class Obsah {
		String meno = "";
		String priezvisko = "";
		String cislo = "";
	
}

class Prvok_zoznamu {
	Obsah obsah;
	Prvok_zoznamu dalsi;
	
}


class Zretazeny_zoznam_simple_disk {
	static Prvok_zoznamu prvy = null;
	static int pocetZaznamov = 0;
	static String meno_suboru = "zoznam.txt";
	
	public static Obsah readObsah() {
		Obsah obsah = new Obsah();
		
		obsah.meno = Zklavesnice.readString("Zadajte meno");
		obsah.priezvisko = Zklavesnice.readString("Zadajte priezvisko");
		obsah.cislo = Zklavesnice.readString("Zadajte cislo");
		
		return obsah;
	}
	
	public static void readObsah_zdisku(String meno_suboru)
	{
		try {
			FileInputStream subor = new FileInputStream(meno_suboru);
			InputStreamReader hadica = new InputStreamReader(subor);
			BufferedReader zo_suboru = new BufferedReader(hadica);
			
			int pocet_v_subore = Integer.parseInt(zo_suboru.readLine());
			
			for (int i = 0; i < pocet_v_subore; i++) {
			Obsah obsah = new Obsah();
			obsah.meno = zo_suboru.readLine();
			obsah.priezvisko = zo_suboru.readLine();
			obsah.cislo = zo_suboru.readLine();
			
			prvy = insertObsah(prvy,obsah);
			pocetZaznamov++;
		
			}
			zo_suboru.close();
			
		}
		catch(Exception e) {
			System.out.println("chyba");
		}
	}
	
	public static void printObsah(Obsah obsah) {
	System.out.println(obsah.meno + " " + obsah.priezvisko + " " + obsah.cislo);	
	}
	
	public static void printObsah_nadisk(Obsah obsah, PrintWriter p) {
	p.println(obsah.meno);	
	p.println(obsah.priezvisko);	
	p.println(obsah.cislo);	
	}
	
	public static void printPrvok_zoznamu(Prvok_zoznamu prvok) {
		if (prvok != null) {
			printObsah(prvok.obsah);
			printPrvok_zoznamu(prvok.dalsi);
		}
	}
	
	public static void printPrvok_zoznamu_nadisk(Prvok_zoznamu prvok, PrintWriter p) {
		if (prvok != null) {
			printObsah_nadisk(prvok.obsah, p);
			printPrvok_zoznamu_nadisk(prvok.dalsi, p);
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
	
	char c;
	
	readObsah_zdisku(meno_suboru);
	
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
		
	} while( c == '1' || c == '2' || c == '3');
	
	try {
		
		FileOutputStream subor = new FileOutputStream(meno_suboru);
		OutputStreamWriter out = new OutputStreamWriter(subor);
		PrintWriter do_suboru = new PrintWriter(out);
		do_suboru.println(pocetZaznamov);
		printPrvok_zoznamu_nadisk(prvy, do_suboru);
		do_suboru.close();
		
	} catch (Exception e) 
		{
			System.out.println("chyba");
		} 
	
}
	
	
}


 