class Trieda {
 int atribut = 0;
 static int sta_atribut = 0;
 

 Trieda () {
	 atribut++;
	 sta_atribut++;
 }

}

public class Priklad7a
{	
	
	public static void main(String[] parametre) {
		Trieda[] objekt1 = new Trieda[10];
		
		for (int i = 0; i < 10; i++) {
		objekt1[i] = new Trieda();
		System.out.println("objekt1[" + i + "].atribut = " + objekt1[i].atribut + " a objekt1[" + i + "].sta_atribut = " + objekt1[i].sta_atribut);
		System.out.println("Trieda.sta_atribut = " + Trieda.sta_atribut);
		}
		
		
		
		}
}