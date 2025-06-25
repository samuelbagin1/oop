public class Tyzden_switch_03_125
{	
	public static void main(String[] parametre) {
		
		String den = "";
		int poradove_cislo = Integer.parseInt(parametre[0]);
		
		switch (poradove_cislo) {
		case 1: den = "pondelok"; break;
		case 2: den = "utorok"; break;
		case 3: den = "streda"; break;
		case 4: den = "stvrtok"; break;
		case 5: den = "piatok"; break;
		case 6:
		case 7: den = "Vikend"; break;
		default: den = "nepoznam";
		}
		
		System.out.println("Je to " + den);
		
	}
}
