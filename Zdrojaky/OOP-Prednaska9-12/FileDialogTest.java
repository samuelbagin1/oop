import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class NasMalyEditor extends JFrame implements ActionListener {
	JMenuItem exit_item;
	JMenuItem open_file;
	JMenuItem save_as_file;
	TextArea edit;
	
	public NasMalyEditor() {
		super("Moj Editor");
		edit = new TextArea();
		add(edit);
		
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
				try {
					FileInputStream fis = new FileInputStream(op_dialog.getDirectory() + op_dialog.getFile());
					InputStreamReader in = new InputStreamReader(fis);
					BufferedReader b = new BufferedReader(in);
					String obsah_suboru = "";
					while (true) {
						String nacitane = b.readLine();
						if (nacitane == null) break;
						obsah_suboru = obsah_suboru + nacitane + "\n";
					}
					edit.setText(obsah_suboru);
					b.close();
				}
				catch (IOException exc) {
					System.out.println(exc);
				}
			}
		}
		if (e.getSource() == save_as_file) {
			FileDialog save_dialog = new FileDialog(this, "Uloz ako", FileDialog.SAVE);
			save_dialog.setVisible(true);
			if (save_dialog.getFile() != null) {
				try {
					FileOutputStream fos = new FileOutputStream(save_dialog.getDirectory() + save_dialog.getFile());
						OutputStreamWriter out = new OutputStreamWriter(fos);
						PrintWriter p = new PrintWriter(out);
						p.println(edit.getText());
						p.close();
				}
				catch(IOException exc) {
					System.out.println("Nie je taky subor");
				}
			}
		}
		

		
	}


}

public class FileDialogTest {
	public static void main (String args[]) {
		new NasMalyEditor();
	}
}