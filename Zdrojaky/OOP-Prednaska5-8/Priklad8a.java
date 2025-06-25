class Trieda1 {
 int atribut1 = 0;
 
Trieda1(int atribut1) {
	this.atribut1 = atribut1;	
} 



}

class Trieda2 extends Trieda1 {
	int atribut2 = 0;
	
	Trieda2(int atribut1, int atribut2) {
	super(atribut1);
	this.atribut2 = atribut2;
	}
}

public class Priklad8a
{	
	
	public static void main(String[] parametre) {
		Trieda2 objekt2 = new Trieda2(5,3);
		
		System.out.println("objekt2.atribut1 = " + objekt2.atribut1 + " a objekt2.atribut2 = " + objekt2.atribut2);
	}
		
		
		
		
}