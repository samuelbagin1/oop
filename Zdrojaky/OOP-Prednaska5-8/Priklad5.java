class Trieda {
 int atribut = 0;
 

}

public class Priklad5
{	
	
	public static void main(String[] parametre) {
		Trieda objekt1 = new Trieda();
		Trieda objekt2 = new Trieda();
		
		objekt1.atribut = 5;
		
		objekt2.atribut = objekt1.atribut;
		
		objekt1.atribut = 3;
		
		System.out.println("objekt2.atribut = " + objekt2.atribut);
	}
}