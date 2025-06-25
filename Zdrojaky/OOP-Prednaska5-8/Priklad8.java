class Trieda1 {
 int atribut1 = 0;
 
Trieda1(int atribut1) {
	this.atribut1 = atribut1;	
} 

Trieda1 () {
}

}

class Trieda2 extends Trieda1 {
	int atribut2 = 0;
}

public class Priklad8
{	
	
	public static void main(String[] parametre) {
		Trieda2 objekt2 = new Trieda2();
		
		System.out.println("objekt2.atribut1 = " + objekt2.atribut1 + " a objekt2.atribut2 = " + objekt2.atribut2);
	}
		
		
		
		
}