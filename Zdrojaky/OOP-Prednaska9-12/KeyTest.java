import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyCanvas extends Canvas implements KeyListener {

	public MyCanvas() {
		addKeyListener(this);
	
	}
	
	public void keyPressed(KeyEvent e) {
		System.out.println("key Pressed zavolane - key code " + e.getKeyCode());
		if (e.isActionKey()) {
			System.out.println("action key");
			}
		else {
			System.out.println("not action key");
		}
	}
	
	public void keyReleased(KeyEvent e) {
		System.out.println("key Released zavolane - key code " + e.getKeyCode());
	}
	
	public void keyTyped(KeyEvent e) {
		System.out.println("key Typed zavolane - key code: " + e.getKeyChar());
		
	}

}

public class KeyTest {	
	public static void main(String parametre[]) {
		
		JFrame f = new JFrame();
		
		MyCanvas papier = new MyCanvas();
		
		f.add(papier);
		
		f.setSize(300,300);
		f.setVisible(true);
		
	
	}
}