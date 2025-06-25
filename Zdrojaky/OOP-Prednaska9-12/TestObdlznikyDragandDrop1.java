import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Obdlznik extends Rectangle {

	Color farba;
	public Obdlznik(int x, int y, int width, int height, Color c) {
		super(x,y,width,height);
		farba = c;
	}
	
	public void kresli (Graphics g) {
		g.setColor(farba);
		g.fillRect(x, y, width, height);
	}

}



class TestCanvas extends Canvas implements MouseListener, MouseMotionListener {
	
	public ArrayList<Obdlznik> naseobdlzniky = new ArrayList<Obdlznik>();
	
	Obdlznik aktualny_obdlznik;
	
	int xpos = 0, ypos = 0;
	
	public TestCanvas () {
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		xpos = e.getX();
		ypos = e.getY();
		aktualny_obdlznik = new Obdlznik(xpos,ypos,1,1, Color.blue);
	}
	public void mouseReleased(MouseEvent e) {
		naseobdlzniky.add(aktualny_obdlznik);
		repaint();
		aktualny_obdlznik = null;
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void mouseDragged(MouseEvent e) {
		int dx = e.getX();
		int dy = e.getY();
		if (aktualny_obdlznik != null) {
			
			if (dx > xpos && dy > ypos) {
				aktualny_obdlznik.width = dx - xpos;
				aktualny_obdlznik.height = dy - ypos;
			}
			
			if (dx < xpos && dy > ypos) {
				aktualny_obdlznik.x = dx;
				aktualny_obdlznik.width = xpos - dx;
				aktualny_obdlznik.height = dy - ypos;
			}
			
			if (dx > xpos && dy < ypos) {
				aktualny_obdlznik.y = dy;
				aktualny_obdlznik.width = dx - xpos;
				aktualny_obdlznik.height = ypos - dy;
			}
			
			if (dx < xpos && dy < ypos) {
				aktualny_obdlznik.x = dx;
				aktualny_obdlznik.y = dy;
				aktualny_obdlznik.width = xpos - dx;
				aktualny_obdlznik.height = ypos - dy;
			}
			
			
			
		}
		
		repaint();
	}
	
	public void mouseMoved(MouseEvent e) {}
	
	
	public void paint (Graphics g) {
		for (Obdlznik aktualny : naseobdlzniky) {
			aktualny.kresli(g);
		}
		if (aktualny_obdlznik != null) {
			aktualny_obdlznik.kresli(g);
		}
		
	}
	
	
}

public class TestObdlznikyDragandDrop1 {	
	public static void main(String parametre[]) {
		JFrame f = new JFrame("Stvorce");
		
		
		TestCanvas can = new TestCanvas();
		f.add("Center", can);
		
		f.setSize(300,300);
		f.setVisible(true);
	
	}
}