class Trieda1 {
 int atribut = 0;
} 
class Trieda2 extends Trieda1 {
	int atribut = 0;
}

public class Priklad11
{	
	
	public static void main(String[] parametre) {
		Trieda1 objekt1 = new Trieda1();
		Trieda2 objekt2 = new Trieda2();
		
		objekt2.atribut = 5;
		
		objekt1 = objekt2;
		System.out.println("objekt1.atribut = " + objekt1.atribut + " a objekt2.atribut = " + objekt2.atribut);
	}
		
		
		
		
}