class Trieda {
	int atribut = 0; // TBD make it private attribute

}

public class Priklad1 {

	public static void main(String[] parametre) {
		Trieda objekt;

		objekt = new Trieda();

		objekt.atribut = 5;

		System.out.println("objekt.atribut = " + objekt.atribut);
	}
}