import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class TestFrame extends JFrame implements ActionListener {
	String zatvor;
	JButton b;
	JButton dalsi;
	JButton iny;
	
	public TestFrame(){
		super("Test Frame");
		zatvor = "Close";
		
		setSize(300,300);
		
		JPanel p = new JPanel();
		b = new JButton(zatvor);
		b.addActionListener(this);
		p.add(b);
		
		dalsi = new JButton("Close");
		dalsi.addActionListener(this);
		p.add(dalsi);
		
		iny = new JButton("nejaky iny napis");
		iny.addActionListener(this);
		p.add(iny);
		
		add(p);
		
		setVisible(true);
	}
	
	public void actionPerformed (ActionEvent e) {
		if ((e.getSource() == b) || (e.getSource() == iny))
		{
			dispose(); System.exit(0);
		}
		else {
			System.out.println(e.getActionCommand());
		}
	}
}

public class TestEvent3a
{	
	public static void main(String parametre[]) {
		new TestFrame();
	
	}
}