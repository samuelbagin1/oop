import javax.swing.*;
import java.awt.*;


class TestCanvas extends Canvas {

public void paint (Graphics g) {
	Dimension d = getSize();
	int w = d.width;
	int h = d.height;
	
	int i = 0;
	Color c;
	
	while (i <= w-1-i && i<= h-1-i) {
		
		if (2*i < 256) {
			c = new Color(2*i,255-2*i,255-2*i);
		}
		else {
			c = new Color(0,255,0);
		}
	
	g.setColor(c);
	g.drawRect(i,i,w-1-2*i,h-1-2*i);
	i++;
	}
}

}

public class Test4
{	
	public static void main(String parametre[]) {
	
	JFrame f = new JFrame();
	
	f.setSize(500,500);
	
	f.setLayout(new BorderLayout());
	
	f.add("North", new JLabel("Priklad s farbami"));
	
	f.add("Center", new TestCanvas());
	
	JPanel p = new JPanel();
	
	p.add(new JButton("Change"));
	p.add(new JButton("Close"));
	
	f.add("South", p);
	
	f.setVisible(true);
	
	
	
	}
}