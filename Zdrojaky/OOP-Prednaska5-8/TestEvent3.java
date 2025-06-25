import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class TestFrame extends JFrame implements ActionListener {
	String zatvor;
	
	public TestFrame(){
		super("Test Frame");
		zatvor = "Close";
		
		setSize(500,500);
		
		JPanel p = new JPanel();
		JButton b = new JButton(zatvor);
		b.addActionListener(this);
		p.add(b);
		
		JButton dalsi = new JButton("Close");
		dalsi.addActionListener(this);
		p.add(dalsi);
		
		JButton iny = new JButton("nejaky iny napis");
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
		else {
			System.out.println(e.getActionCommand());
		}
	}
}

public class TestEvent3
{	
	public static void main(String parametre[]) {
		new TestFrame();
	
	}
}