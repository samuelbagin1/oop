import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class Obdlznik extends Rectangle implements Serializable {

	Color farba;
	public Obdlznik(int x, int y, int width, int height, Color c) {
		super(x,y,width,height);
		farba = c;
	}
	
	public void kresli (Graphics g) {
		g.setColor(farba);
		g.fillRect(x, y, width, height);
	}

}



class TestCanvas extends Canvas implements MouseListener, MouseMotionListener {
	
	public ArrayList<Obdlznik> naseobdlzniky = new ArrayList<Obdlznik>();
	
	Obdlznik aktualny_obdlznik;
	
	int xpos = 0, ypos = 0;
	
	Color farba;
	
	public TestCanvas () {
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
		farba = Color.blue;
		// loadObdlzniky();
	}
	
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		xpos = e.getX();
		ypos = e.getY();
		aktualny_obdlznik = new Obdlznik(xpos,ypos,1,1, farba);
	}
	public void mouseReleased(MouseEvent e) {
		naseobdlzniky.add(aktualny_obdlznik);
		repaint();
		aktualny_obdlznik = null;
		// saveObdlzniky();
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void mouseDragged(MouseEvent e) {
		int dx = e.getX();
		int dy = e.getY();
		if (aktualny_obdlznik != null) {
			
			if (dx > xpos && dy > ypos) {
				aktualny_obdlznik.width = dx - xpos;
				aktualny_obdlznik.height = dy - ypos;
			}
			
			if (dx < xpos && dy > ypos) {
				aktualny_obdlznik.x = dx;
				aktualny_obdlznik.width = xpos - dx;
				aktualny_obdlznik.height = dy - ypos;
			}
			
			if (dx > xpos && dy < ypos) {
				aktualny_obdlznik.y = dy;
				aktualny_obdlznik.width = dx - xpos;
				aktualny_obdlznik.height = ypos - dy;
			}
			
			if (dx < xpos && dy < ypos) {
				aktualny_obdlznik.x = dx;
				aktualny_obdlznik.y = dy;
				aktualny_obdlznik.width = xpos - dx;
				aktualny_obdlznik.height = ypos - dy;
			}
			
			
			
		}
		
		repaint();
	}
	
	public void mouseMoved(MouseEvent e) {}
	
	
	public void paint (Graphics g) {
		for (Obdlznik aktualny : naseobdlzniky) {
			aktualny.kresli(g);
		}
		if (aktualny_obdlznik != null) {
			aktualny_obdlznik.kresli(g);
		}
		
	}
	
	public void loadObdlzniky (FileDialog subor) {
		try {
			FileInputStream instream = new FileInputStream(subor.getDirectory() + subor.getFile());
			ObjectInputStream ois = new ObjectInputStream(instream);
			
			Object nacitany = ois.readObject();
			instream.close();
			
			if (nacitany instanceof Iterable<?>) {
				Iterable<?> nacitane = (Iterable<?>) nacitany;
				
				for (Object prvok : nacitane) {
					if (prvok instanceof Obdlznik) {
						naseobdlzniky.add((Obdlznik) prvok);
					}
				}
			
			}
			 
			
			
			repaint();
		}
		catch (Exception e) {
			System.out.println("Nepodarilo sa nacitat obdlzniky"); 
		}
	
	}
	
	public void saveObdlzniky(FileDialog subor) {
		try {
			FileOutputStream ostream = new FileOutputStream(subor.getDirectory() + subor.getFile());
			ObjectOutputStream oos = new ObjectOutputStream(ostream);
			
			oos.writeObject(naseobdlzniky);
			
			ostream.close();
			
		}
		catch (Exception e) {
			System.out.println("Nepodarilo sa ulozit obdlzniky"); 
		}
	
	
	}
	
	
}

class NasObdlznikEditor extends JFrame implements ActionListener {
	JMenuItem exit_item;
	JMenuItem open_file;
	JMenuItem save_as_file;
	TestCanvas can;
	
	ColorEdit ce = null;
	JButton color_button;
	
	public NasObdlznikEditor() {
		super("Moj Editor");
		can = new TestCanvas();
		add("Center", can);
		
		color_button = new JButton("Nastav farbu");
		color_button.addActionListener(this);
		add("North", color_button);
		
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		
		JMenu file = new JMenu("File");
		
		open_file = new JMenuItem("Open");
		open_file.addActionListener(this);
		file.add(open_file);
		
		save_as_file = new JMenuItem("Save as");
		save_as_file.addActionListener(this);
		file.add(save_as_file);
		
		exit_item = new JMenuItem("Exit");
		exit_item.addActionListener(this);
		file.add(exit_item);
		
		bar.add(file);
		
		addWindowListener(new WindowAdapter() {
							public void windowClosing (WindowEvent e) {
								System.exit(0);
								}
							}
						);
						
		setSize(800,600);
		setVisible(true);
	
	}
	
	public void actionPerformed (ActionEvent e) {
		if (e.getSource() == exit_item) {
			System.exit(0);
		}
		if (e.getSource() == open_file) {
			FileDialog op_dialog = new FileDialog(this,"Otvor", FileDialog.LOAD);
			op_dialog.setVisible(true);
			if (op_dialog.getFile() != null) {
				can.loadObdlzniky(op_dialog);
			}
		}
		if (e.getSource() == save_as_file) {
			FileDialog save_dialog = new FileDialog(this, "Uloz ako", FileDialog.SAVE);
			save_dialog.setVisible(true);
			if (save_dialog.getFile() != null) {
				can.saveObdlzniky(save_dialog);
			}
		}
		
		if (e.getSource() == color_button) {
			if (ce == null) {
				ce = new ColorEdit(this, can.farba);
			}
		}
		

		
	}
	
}

class CollorScrollbar extends Panel implements AdjustmentListener {
	public int value;
	ColorEdit ce;
	Scrollbar sb;
	
	public CollorScrollbar (ColorEdit ce, String s, int value) {
		this.ce  = ce;
		this.value = value;
		setLayout (new GridLayout(1,0));
		add(new Label(s));
		sb = new Scrollbar(Scrollbar.HORIZONTAL, value, 40,0,255+40);
		add(sb);
		sb.addAdjustmentListener(this);
	}
	
	public void adjustmentValueChanged(AdjustmentEvent e) {
		value = sb.getValue();
		ce.setColor();
	}
	
	public Dimension getPrefferedSize() {
		return new Dimension(400,40);
	}
}

class ColorEdit extends Dialog implements ActionListener {
	CollorScrollbar red, green, blue;
	Label redlabel,greenlabel, bluelabel;
	
	Color c, old;
	Panel cp;
	Label lb;
	String name;
	NasObdlznikEditor cl;
	Button ok;
	Button cancel;
	
	public ColorEdit (NasObdlznikEditor f, Color c) {
		super(f, "EditColor", false);
		cl = f;
		old = this.c = c;
		
		Panel p = new Panel();
		p.setLayout(new GridLayout(0,1));
		red = new CollorScrollbar(this, "Red", this.c.getRed());
		p.add(red);
		green = new CollorScrollbar(this, "Green", this.c.getGreen());
		p.add(green);
		blue = new CollorScrollbar(this, "Blue", this.c.getBlue());
		p.add(blue);
		add("Center", p);
		
		Panel pb = new Panel();
		ok = new Button("OK");
		pb.add(ok);
		ok.addActionListener(this);
		cancel = new Button("Cancel");
		pb.add(cancel);
		cancel.addActionListener(this);
		add("South", pb);
		
		cp = new Panel();
		
		cp.add(new Label ("Your original color"));
		lb = new Label("Your new color");
		cp.add(lb);
		cp.setBackground(this.c);
		add("North", cp);
		
		pack();
		setVisible(true);
	}
	
	public void actionPerformed (ActionEvent e) {
		if (e.getSource() == cancel) {
			c = old;
			cl.can.farba = c;
		}
		cl.ce = null;
		dispose();
	}
	
	public void setColor() {
		c = new Color (red.value, green.value, blue.value);
		cp.setBackground(c);
		cp.repaint();
		lb.setBackground(c);
		lb.repaint();
		cl.can.farba = c;
	}
	
}

public class TestObdlznikyDragandDrop5 {	
	public static void main(String parametre[]) {
		new NasObdlznikEditor();
	
	}
}