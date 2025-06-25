public class Pole_04_130
{	
	public static void main(String[] parametre) {
		
		int pocet = Integer.parseInt(parametre[0]);
		
		System.out.println("toto je pocet prvkov pola " + pocet);
		
		int pole[] = new int[pocet];
		
		for (int i = 0; i < pole.length; i++)
		{
			pole[i] = Zklavesnice.readInt("Zadaj prvok pole[" + i + "]");
		}
		
		System.out.println("Vypis pole");
		
		for (int i = 0; i < pole.length; i++) {
			System.out.print(pole[i] + ", ");
		}
		System.out.println();
		System.out.println("obsah premennej pole je " + pole);
		
	}
}
