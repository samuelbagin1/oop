import javax.swing.*;
import java.awt.*;

public class Test1
{	
	public static void main(String parametre[]) {
	
	JFrame f = new JFrame();
	
	f.setSize(500,500);
	
	f.setVisible(true);
	
	Graphics g = f.getGraphics();
	
	g.drawString("Hello World!", 50,50);
	
	}
}