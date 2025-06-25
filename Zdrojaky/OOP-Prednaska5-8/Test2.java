import javax.swing.*;
import java.awt.*;


class TestCanvas extends Canvas {

public void paint (Graphics g) {
	Dimension d = getSize();
	int w = d.width;
	int h = d.height;
	
	g.drawString("Hello World! - sirka " + w + " a vyska " + h, 50,50);
	
	g.drawLine(0,0,w-1,h-1);
	g.drawLine(0,h-1,w-1,0);
	g.drawRect(0,0,w-1,h-1);

}

}

public class Test2
{	
	public static void main(String parametre[]) {
	
	JFrame f = new JFrame();
	
	f.setSize(500,500);
	
	f.add("Center", new TestCanvas());
	
	f.setVisible(true);
	
	
	
	}
}