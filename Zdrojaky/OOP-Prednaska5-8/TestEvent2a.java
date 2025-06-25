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

public class TestEvent2a
{	
	public static void main(String parametre[]) {
	
	JFrame f = new JFrame();
	
	f.setSize(500,500);
	
	JPanel p = new JPanel();

	MyButton b = new MyButton("OK");
	
	JButton iny = new JButton("nie som action listener");
	iny.addActionListener(b);
	
	p.add(b);
	p.add(iny);
	
	f.add(p);
	
	
	f.setVisible(true);
	
	
	
	}
}