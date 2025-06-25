public class Odmocnina_03_102
{	
	public static void main(String[] parametre) {
		
		double x = Double.parseDouble(parametre[0]);
		
		double d = 0;
		double h = x + 1;
		
		double eps = 0.0000000001;
		double s = (d + h) / 2;
		
		if (x >= 0) {
			do {
				if (s * s >= x) {
					h = s;
				}
				else {
					d = s;
				}
				s = (d + h) / 2;	
      } while ( (h-d) / s > eps);
			
      System.out.println("Odmocnina cisla " + x + " je rovna " + s);
		}
		else {
			System.out.println("Neviem vypocitat odmocninu zo zaporneho cisla");
		}
		
	}
}
