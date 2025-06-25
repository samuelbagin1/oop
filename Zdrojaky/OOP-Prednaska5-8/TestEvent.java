import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class ReagovacNaButton implements ActionListener {
	
	int pocet = 0;

public void actionPerformed(ActionEvent e) {
	pocet++;
	System.out.println(e.getActionCommand() + " " + pocet);

}

}

public class TestEvent
{	
	public static void main(String parametre[]) {
	
	JFrame f = new JFrame();
	
	f.setLayout(new GridLayout(0,2));
	
	JPanel p = new JPanel();

	JButton b = new JButton("OK");
	
	b.addActionListener(new ReagovacNaButton());
	
	p.add(b);
	
	f.add(p);
	
	f.pack();
	
	f.setVisible(true);
	
	
	
	}
}