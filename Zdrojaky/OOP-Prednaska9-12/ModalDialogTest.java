import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class CloseQuestion extends Dialog implements ActionListener {
	public boolean yes = false;
	
	public CloseQuestion (Frame f) {
		super(f, "Close", true);
		add("North", new Label("Naozaj chces zavriet okno?"));
		Panel p = new Panel();
		
		Button ano = new Button("Ano");
		ano.addActionListener(this);
		Button nie = new Button("Nie");
		nie.addActionListener(this);
		
		p.add(ano);
		p.add(nie);
		add("South",p);
		pack();
		setLocation(f.getLocation().x + 100, f.getLocation().y + 100);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand().equals("Ano")) {
			yes = true;
		}
		dispose();
	}
	

}

class TestFrame extends Frame {
	TestFrame() {
		final Frame f = this;
		setSize(300,300);
		setVisible(true);
		addWindowListener ( new WindowAdapter () {
			public void windowClosing (WindowEvent e) {
				CloseQuestion koniec = new CloseQuestion(f);
				if (koniec.yes) {
					dispose(); System.exit(0);
				}
			}
		});
	}
}



public class ModalDialogTest {	
	public static void main(String parametre[]) {
		new TestFrame();
		
	}
}