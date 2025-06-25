import javax.swing.*;
import java.awt.*;

public class Test5
{	
	public static void main(String parametre[]) {
	
	JFrame f = new JFrame();
	
	f.setLayout(new GridLayout(0,2));
	
	f.add(new JLabel("prvy"));
	f.add(new JLabel("druhy"));
	f.add(new JLabel("treti"));
	f.add(new JLabel("stvrty"));
	f.add(new JLabel("piaty"));
	f.add(new JLabel("siesty"));
	f.add(new JLabel("siedmy"));

	f.pack();
	f.setVisible(true);
	
	
	
	}
}