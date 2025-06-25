class Trieda {
 int atribut = 0;
 static int sta_atribut = 0;
 

 Trieda () {
	 atribut++;
	 sta_atribut++;
 }

}

public class Priklad7
{	
	
	public static void main(String[] parametre) {
		Trieda objekt1 = new Trieda();
		
		System.out.println("objekt1.atribut = " + objekt1.atribut + " a objekt1.sta_atribut = " + objekt1.sta_atribut);
		
		
		Trieda objekt2 = new Trieda();
		
		System.out.println("objekt2.atribut = " + objekt2.atribut  + " a objekt2.sta_atribut = " + objekt2.sta_atribut);
	}
}