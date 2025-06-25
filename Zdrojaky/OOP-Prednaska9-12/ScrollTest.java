import javax.swing.*;
import java.awt.*;

class MyCanvas extends Canvas {

	public void paint (Graphics g) {
	
	g.drawLine(0,0,400,400);
	
	}

}

public class ScrollTest {	
	public static void main(String parametre[]) {
		
		JFrame f = new JFrame();
		
		ScrollPane ms = new ScrollPane();
		MyCanvas papier = new MyCanvas();
		
		papier.setSize(500,500);
		ms.add(papier);
		f.add(ms);
		
		f.setSize(300,300);
		f.setVisible(true);
		
	
	}
}