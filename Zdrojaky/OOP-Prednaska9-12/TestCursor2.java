import java.awt.*;
import java.awt.event.*;

class TestFrame extends Frame implements ActionListener {
	
	Panel ButtonPanel;
	
	Button crosshair;
	Button wait;
	Button custom_cursor;
	Button text;
	Button hand;
	
	Cursor crosshair_c;
	Cursor wait_c;
	Cursor custom_cursor_c;
	Cursor text_c;
	Cursor hand_c;
	
	Cursor panel_cursor;
	Cursor frame_cursor;
	
	public TestFrame(String napis) {
	super(napis);
	setSize(700,400);
	
	crosshair = new Button("CROSSHAIR_CURSOR");
	wait = new Button("WAIT_CURSOR");
	custom_cursor = new Button("???????");
	text = new Button("TEXT_CURSOR");
	hand = new Button("HAND_CURSOR");
	
	crosshair.addActionListener(this);
	wait.addActionListener(this);
	custom_cursor.addActionListener(this);
	text.addActionListener(this);
	hand.addActionListener(this);
	
	Panel p = new Panel();
	p.add(crosshair);
	p.add(wait);
	p.add(custom_cursor);
	p.add(text);
	p.add(hand);
	
	panel_cursor = new Cursor(Cursor.MOVE_CURSOR);
	frame_cursor = new Cursor(Cursor.WAIT_CURSOR);
	
	crosshair_c = new Cursor(Cursor.CROSSHAIR_CURSOR);
	wait_c = new Cursor(Cursor.WAIT_CURSOR);
	
	Point size = new Point(15,15);
	
	custom_cursor_c = getToolkit().createCustomCursor(getToolkit().getImage("mycursor.gif"),size,"My cursor");

	text_c = new Cursor(Cursor.TEXT_CURSOR);
	hand_c = new Cursor(Cursor.HAND_CURSOR);
	
	
	p.setCursor(panel_cursor);
	this.setCursor(frame_cursor);
	
	add("North", p);
	
	setVisible(true);
	
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == crosshair) {
			frame_cursor = crosshair_c;
		}
		if (e.getSource() == wait) {
			frame_cursor = wait_c;
		}
		if (e.getSource() == custom_cursor) {
			frame_cursor = custom_cursor_c;
		}
		if (e.getSource() == text) {
			frame_cursor = text_c;
		}
		if (e.getSource() == hand) {
			frame_cursor = hand_c;
		}
		this.setCursor(frame_cursor);
	}
	
}


 



public class TestCursor2 {	
	public static void main(String parametre[]){
	TestFrame f = new TestFrame("okno s kurzormi");
	
	}
		
}