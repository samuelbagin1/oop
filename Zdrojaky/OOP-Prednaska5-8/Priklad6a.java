class Trieda {
 int atribut = 0;
 
 Trieda (int atribut) {
	 this.atribut = atribut;
 }

}

public class Priklad6a
{	
	
	public static void main(String[] parametre) {
		Trieda objekt1 = new Trieda(7);
		
		
		System.out.println("objekt1.atribut = " + objekt1.atribut);
	}
}