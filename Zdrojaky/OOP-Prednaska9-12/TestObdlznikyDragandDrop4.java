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
	
	public TestCanvas () {
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
		// loadObdlzniky();
	}
	
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		xpos = e.getX();
		ypos = e.getY();
		aktualny_obdlznik = new Obdlznik(xpos,ypos,1,1, Color.blue);
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
	
	public NasObdlznikEditor() {
		super("Moj Editor");
		can = new TestCanvas();
		add(can);
		
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
		

		
	}
	
}

public class TestObdlznikyDragandDrop4 {	
	public static void main(String parametre[]) {
		new NasObdlznikEditor();
	
	}
}