import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



 

class TestFrame extends JFrame {

public TestFrame (String napis) {
 super(napis);
 setSize(300,300);
 addWindowListener(new WindowAdapter() {
	public void windowClosing (WindowEvent e) {
		System.exit(0);
	}
});
 setVisible(true);
}

}

public class TestWindow2 {	
	public static void main(String parametre[]){
	TestFrame f = new TestFrame("okno ktore ked zatvorim program sa ukonci");
	
	}
		
}