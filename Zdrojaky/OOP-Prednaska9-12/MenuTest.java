import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TestFrame extends JFrame implements ActionListener {
	JRadioButtonMenuItem ignore;
	PopupMenu pm;
		
	public TestFrame() {
		JMenuBar m = new JMenuBar();
		setJMenuBar(m);
		
		JMenu test = new JMenu("Zatvarac okna");
		m.add(test);
		
		JMenuItem item = new JMenuItem("Close");
		item.addActionListener(this);
		test.add(item);
		
		ignore = new JRadioButtonMenuItem("Do not close");
		test.add(ignore);
		ignore.setSelected(true);
		
		pm = new PopupMenu();
		MenuItem mi = new MenuItem("Close");
		mi.addActionListener(this);
		pm.add(mi);
		
		add(pm);
		
		addMouseListener(
			new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getButton() == 3) {
						pm.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			});
			
		setSize(400,400);
		setVisible(true);
		
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Close") && !ignore.isSelected()) {
			dispose(); System.exit(0);
		}
	}
	

}


public class MenuTest {	
	public static void main(String parametre[]) {
		new TestFrame();
	
	}
}