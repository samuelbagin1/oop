import javax.swing.*;
import java.awt.*;
import java.util.*;



class TestCanvas extends Canvas {
	
	public HashSet<Rectangle> nasestvorce = new HashSet<Rectangle>();
	
	public void mojeStvorce() {
		
		int j = 20;
		
		for (int i = 0; i < 5; i++) {
			nasestvorce.add(new Rectangle(10, j, 20, 20));
			j = j + 30;
		}
	}
	
	public void paint (Graphics g) {
		Rectangle aktualny;
		
		Iterator it = nasestvorce.iterator();
		
		while (it.hasNext()){
			Object dalsi = it.next();
			if  (dalsi instanceof Rectangle) {
				aktualny = (Rectangle) dalsi;	
				System.out.println(aktualny.y);
				g.drawRect(aktualny.x, aktualny.y, aktualny.width, aktualny.height);
			}
		}
	}
	
	
}

public class TestStvorec {	
	public static void main(String parametre[]) {
		JFrame f = new JFrame("Stvorce");
		f.setSize(300,300);
		TestCanvas can = new TestCanvas();
		can.mojeStvorce();
		f.add(can);
		f.setVisible(true);
	
	}
}