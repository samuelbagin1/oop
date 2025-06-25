import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class MyButton extends JButton implements ActionListener {
	
	int pocet;
	
	public MyButton(String napis) {
		super (napis);
		pocet = 0;
		addActionListener(this);
	}
	
	public void actionPerformed (ActionEvent e) {
		pocet++;
		System.out.println(e.getActionCommand() + " " + pocet);
	}
	
}

public class TestEvent2
{	
	public static void main(String parametre[]) {
	
	JFrame f = new JFrame();
	
	f.setSize(1000,1000);
	
	JPanel p = new JPanel();

	MyButton b = new MyButton("OK");
	
	p.add(b);
	
	f.add(p);
	
	
	f.setVisible(true);
	
	
	
	}
}