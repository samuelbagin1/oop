import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class TestFrame extends JFrame implements ActionListener {
	String zatvor;
	String novinka;
	JButton b;
	JButton dalsi;
	JButton iny;
	
	public static int pocetframeov = 0;
	public static int umiestnenie = 10;
	
	public TestFrame(){
		super("Test Frame");
			zatvor = "Close";
			novinka = "New";
		
		setSize(500,500);
		
		JPanel p = new JPanel();
		b = new JButton(zatvor);
		b.addActionListener(this);
		p.add(b);
		
		dalsi = new JButton("Exit");
		dalsi.addActionListener(this);
		p.add(dalsi);
		
		iny = new JButton(novinka);
		iny.addActionListener(this);
		p.add(iny);
		
		add(p);
		setLocation(umiestnenie,umiestnenie);
		setVisible(true);
		pocetframeov++;
		umiestnenie = umiestnenie + 50;
	}
	
	public void actionPerformed (ActionEvent e) {
		if (e.getActionCommand().equals(zatvor))
		{
			if (pocetframeov == 1) {
				System.exit(0);
			}
			else {
				dispose();
				pocetframeov--;
			}	
		}
		if (e.getActionCommand().equals(novinka))
		{
			new TestFrame();
		}
		if (e.getActionCommand().equals("Exit"))
		{
			System.exit(0);
		}
	}
}

public class TestEvent5
{	
	public static void main(String parametre[]) {
		new TestFrame();
	
	}
}