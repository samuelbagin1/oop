import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TestFrame extends JFrame implements ItemListener {
	List zoznam;
	Choice rozbalovacie_polozky;
	
	String polozky_pre_zoznam[] = {"Jablko", "Hruska", "Ceresna", "Melon", "Nejake ine ovocie"};;
	
	String polozky_pre_rozbalovacie[]= {"Jablko", "Hruska", "Ceresna", "Melon", "Ananas"};
	
	public TestFrame() {
		super ("Choice list");
		
		zoznam = new List();
		zoznam.addItemListener(this);
		
		rozbalovacie_polozky = new Choice();
		rozbalovacie_polozky.addItemListener(this);
		
		for (int i = 0; i < polozky_pre_zoznam.length; i++) {
			zoznam.add(polozky_pre_zoznam[i]);
		}
		
		for (int i = 0; i < polozky_pre_rozbalovacie.length; i++) {
			rozbalovacie_polozky.add(polozky_pre_rozbalovacie[i]);
		}
		
		add("Center", zoznam);
		add("South", rozbalovacie_polozky);
		
		
		setSize(400,400);
		setVisible(true);
		
	}
	
	public void itemStateChanged (ItemEvent e) {
		if (e.getSource() == zoznam) {
			rozbalovacie_polozky.select(zoznam.getSelectedIndex());
		} else {
			if (e.getSource() == rozbalovacie_polozky) {
				zoznam.select(rozbalovacie_polozky.getSelectedIndex());
			}
		}
	}

}


public class ChoiceListTest {	
	public static void main(String parametre[]) {
		new TestFrame();
	
	}
}