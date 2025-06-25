public class Bubble_sort
{	

	public static void sort(int[] a){
		boolean sorted;
		
		do {
			sorted = true;
			for ( int i = 0; i < a.length - 1; i++) {
				if (a[i] > a[i+1]) {
					int h = a[i];
					a[i] = a[i+1];
					a[i+1] = h;
					sorted = false;
				}
					
			}
		}
		while (!sorted);
	}
	

	public static void main(String[] parametre) {
		
		int pocet = Zklavesnice.readInt("Zadaj pocet prvkov pola");
		int[] b = new int[pocet];
		
		for (int i = 0; i < b.length; i++) {
			b[i] = Zklavesnice.readInt("Zadaj b[" + i + "] ");
		}
		
		sort(b);
		
		System.out.println("Zoradene pole");
		for (int i = 0; i < b.length; i++) {
			System.out.println("b[" + i + "] = " + b[i]);
		}
		
		
		
			
	}
}