import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TestFrame extends JFrame implements ItemListener {
	JCheckBox one;
	JCheckBox two;
	
	
	JTextField in;
	
	public TestFrame() {
		super ("Check boxes");
		
		one = new JCheckBox("one", true);
		add("North", one);
		one.addItemListener(this);
		
		two = new JCheckBox("two");
		add("Center", two);
		two.addItemListener(this);
		
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


public class CheckBoxTest {	
	public static void main(String parametre[]) {
		new TestFrame();
	
	}
}