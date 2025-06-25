class Trieda {
 int atribut = 0;
 
 Trieda (int atribut) {
	 this.atribut = atribut;
 }
 
 Trieda () {
 }

}

public class Priklad6b
{	
	
	public static void main(String[] parametre) {
		Trieda objekt1 = new Trieda(7);
		Trieda objekt2 = new Trieda();
		objekt2.atribut = objekt1.atribut;
		
		System.out.println("objekt1.atribut = " + objekt1.atribut);
		System.out.println("objekt2.atribut = " + objekt2.atribut);
	}
}