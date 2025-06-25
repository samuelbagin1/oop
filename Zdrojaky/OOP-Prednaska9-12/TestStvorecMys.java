import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


class RectangleButton extends JButton implements ActionListener {
	public boolean chcem_stvorec;
	
	public RectangleButton (String label) {
		super (label);
		addActionListener(this);
		chcem_stvorec = false;
	}
	
	public void actionPerformed(ActionEvent e) {
		chcem_stvorec = true;
	}

}


class TestCanvas extends Canvas implements MouseListener {
	
	public RectangleButton gombik;
	
	public HashSet<Rectangle> nasestvorce = new HashSet<Rectangle>();
	
	public TestCanvas (RectangleButton gombik) {
		super();
		addMouseListener(this);
		this.gombik = gombik;
	}
	
	
	public void mouseClicked(MouseEvent e) {
		
		int x = e.getX();
		int y = e.getY();
		
		if (gombik.chcem_stvorec) {
			nasestvorce.add(new Rectangle(x, y, 20, 20));
			repaint();
			gombik.chcem_stvorec = false;
		}
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void paint (Graphics g) {
		for (Rectangle aktualny : nasestvorce) {
			g.drawRect(aktualny.x, aktualny.y, aktualny.width, aktualny.height);
		}
	}
	
	
}

public class TestStvorecMys {	
	public static void main(String parametre[]) {
		JFrame f = new JFrame("Stvorce");
		JPanel p = new JPanel();
		RectangleButton gombik = new RectangleButton("Chcem stvorec");
		p.add(gombik);
		f.add("North", p);
		
		
		TestCanvas can = new TestCanvas(gombik);
		f.add("Center", can);
		
		f.setSize(300,300);
		f.setVisible(true);
	
	}
}