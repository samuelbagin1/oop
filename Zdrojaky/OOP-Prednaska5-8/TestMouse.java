import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class TestCanvas extends Canvas implements MouseListener, MouseMotionListener {
	
	public TestCanvas() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void mousePressed(MouseEvent e) {
		System.out.println(e);
	}
	public void mouseReleased(MouseEvent e) {
		System.out.println(e);
	}
	public void mouseClicked(MouseEvent e) {
		System.out.println(e + " klik na mys na suradniciach " + e.getX() + ", " + e.getY() );
	}
	public void mouseEntered(MouseEvent e) {
		System.out.println(e);
	}
	public void mouseExited(MouseEvent e) {
		System.out.println(e);
	}
	public void mouseDragged(MouseEvent e) {
		System.out.println(e + " som stlaceny a hybem sa " + e.getX() + ", " + e.getY());
	}public void mouseMoved(MouseEvent e) {
		System.out.println(e + " hybem sa " + e.getX() + ", " + e.getY());
	}
	
}

public class TestMouse
{	
	public static void main(String parametre[]) {
		JFrame f = new JFrame("Udalosti mysi");
		f.add(new TestCanvas());
		f.setSize(300,300);
		f.setVisible(true);
	
	}
}