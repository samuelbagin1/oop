import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TestFrame extends JFrame implements ItemListener {
	JRadioButton one;
	JRadioButton two;
	
	ButtonGroup bg;
	
	JTextField in;
	
	public TestFrame() {
		super ("Button group");
		bg = new ButtonGroup();
		
		one = new JRadioButton("one");
		add("North", one);
		one.addItemListener(this);
		bg.add(one);
		
		two = new JRadioButton("two");
		add("Center", two);
		two.addItemListener(this);
		bg.add(two);
		
		add("South", in = new JTextField());
		in.setText("One is " + one.isSelected() + " and two is " + two.isSelected());
		
		
		pack();
		setVisible(true);
		
	}
	
	public void itemStateChanged (ItemEvent e) {
		if (e.getSource() == one) {
			in.setText("One is " + one.isSelected() + " and two is " + two.isSelected());
		
		} else {
			if (e.getSource() == two) {
				in.setText("Two is " + two.isSelected() + " and one is " + one.isSelected());
		
			}
		}
	}

}


public class ButtonGroupTest {	
	public static void main(String parametre[]) {
		new TestFrame();
	
	}
}