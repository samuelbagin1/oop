public class Inkrement_03
{
	public static void main(String[] parametre) {
		
		int vstup = Integer.parseInt(parametre[0]);
		int n = vstup;
		int r = n++ * n;
		
		System.out.println("po vykonani r = n++ * n");
		System.out.println("vstup = " + vstup);
		System.out.println("n = " + n);
		System.out.println("r = " + r);
	
	}
}