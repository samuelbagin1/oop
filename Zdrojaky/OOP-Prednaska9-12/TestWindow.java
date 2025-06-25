import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



class Zatvarac extends WindowAdapter {
	public void windowClosing (WindowEvent e) {
		System.exit(0);
	}
}

class TestFrame extends JFrame {

public TestFrame (String napis) {
 super(napis);
 setSize(300,300);
 addWindowListener(new Zatvarac());
 setVisible(true);
}

}

public class TestWindow {	
	public static void main(String parametre[]){
	TestFrame f = new TestFrame("okno ktore ked zatvorim program sa ukonci");
	
	}
		
}