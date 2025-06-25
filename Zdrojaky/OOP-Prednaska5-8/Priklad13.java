class Trieda1 {
 int atribut = 0;
} 
class Trieda2 extends Trieda1 {
	int atribut = 0;
}

public class Priklad13
{	
	
	public static void main(String[] parametre) {
		Trieda1 objekt1 = new Trieda1();
		Trieda2 objekt2 = new Trieda2();
		
		objekt2.atribut = 5;
		
		Trieda1[] pole = new Trieda1[2];
		pole[0] = objekt1;
		pole[1] = objekt2;
		if (pole[1] instanceof Trieda2) {
		objekt2 = (Trieda2) pole[1];
		}
		System.out.println("pole[0].atribut = " + pole[0].atribut + " a pole[1].atribut = " + pole[1].atribut);
	}
		
		
		
		
}