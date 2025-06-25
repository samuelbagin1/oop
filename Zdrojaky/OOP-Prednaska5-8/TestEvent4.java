import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class TestFrame extends JFrame implements ActionListener {
	String zatvor;
	String novinka;
	JButton b;
	JButton dalsi;
	JButton iny;
	
	public TestFrame(){
		super("Test Frame");
			zatvor = "Close";
			novinka = "New";
		
		setSize(500,500);
		
		JPanel p = new JPanel();
		b = new JButton(zatvor);
		b.addActionListener(this);
		p.add(b);
		
		dalsi = new JButton("Close");
		dalsi.addActionListener(this);
		p.add(dalsi);
		
		iny = new JButton(novinka);
		iny.addActionListener(this);
		p.add(iny);
		
		add(p);
		
		setVisible(true);
	}
	
	public void actionPerformed (ActionEvent e) {
		if (e.getActionCommand().equals(zatvor))
		{
			dispose(); System.exit(0);
		}
		if (e.getActionCommand().equals(novinka))
		{
			new TestFrame();
		}
	}
}

public class TestEvent4
{	
	public static void main(String parametre[]) {
		new TestFrame();
	
	}
}