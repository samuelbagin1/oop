class Trieda1 {
 int atribut = 0;
} 
class Trieda2 extends Trieda1 {
	int atribut = 0;
	
	void setAtribut(int atribut) {
		this.atribut = atribut;
	}
	
	void setAtributSuper(int atribut) {
		super.atribut = atribut;
	}
	
	int getAtribut() {
		return this.atribut;
	}
	
	int getAtributSuper() {
		return super.atribut;
	}
	
}

public class Priklad12
{	
	
	public static void main(String[] parametre) {
		Trieda2 objekt2 = new Trieda2();
		
		objekt2.setAtribut(5);
		objekt2.setAtributSuper(3);
		
	System.out.println("atribut zTriedy 1 " + objekt2.getAtributSuper() + " a atribut z Triedy2 " + objekt2.getAtribut());
	}
		
		
		
		
}