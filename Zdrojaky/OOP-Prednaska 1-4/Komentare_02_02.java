public class Komentare0202
{
	/**
	Toto je komentar do dokumentacie
	@param parametre obsahuje pole retazcov zadanych pri spusteni programu
	*/
	
	public static void main(String[] parametre) {
		
		int n = Integer.parseInt(parametre[0]);
		boolean b =   (5 / n >= 1) && (n != 0);
		
		System.out.println("b = " + b);
		
	}
}
