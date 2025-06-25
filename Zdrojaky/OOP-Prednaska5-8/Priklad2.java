class Trieda {
 int atribut = 0;

}

public class Priklad2
{	
	public static void funkcia(Trieda parameter1, int parameter2) {
		parameter1.atribut = parameter2;
	}
		
	public static void main(String[] parametre) {
		Trieda objekt;
		
		objekt = new Trieda();
		
		funkcia(objekt, 5);
		
		System.out.println("objekt.atribut = " + objekt.atribut);
	}
}