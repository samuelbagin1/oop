class Trieda {
 int atribut = 0;
 
 private Trieda() { atribut = 1; }

 public Trieda (int nieco) {
	  Trieda();
	 atribut = nieco;
 }

 public Trieda(double nieco) {
	atribut = (int) nieco;
 }

}

public class Priklad6
{	
	
	public static void main(String[] parametre) {
		Trieda objekt1 = new Trieda(7);

		objekt1 = new Trieda(2.5);

		double y = 2.5;
		objekt1 = new Trieda(y);
		
		
		System.out.println("objekt1.atribut = " + objekt1.atribut);
	}
}