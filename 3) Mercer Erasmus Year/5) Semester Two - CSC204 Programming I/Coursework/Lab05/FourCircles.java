import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class FourCircles extends JComponent {

	public void paintComponent(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		int width = getWidth()/2; 
		int height = getHeight()/2; 

		graphics2D.setColor(Color.YELLOW);
		Ellipse2D.Double yellowCircle = new Ellipse2D.Double(0, 0, width, height);
		graphics2D.fill(yellowCircle);
		
		graphics2D.setColor(Color.BLACK);
		Ellipse2D.Double blackCircle = new Ellipse2D.Double(0, height, width, height);
		graphics2D.fill(blackCircle);
		
		graphics2D.setColor(Color.RED);
		Ellipse2D.Double redCircle = new Ellipse2D.Double(width, 0, width, height);
		graphics2D.fill(redCircle);
		
		graphics2D.setColor(Color.BLUE);
		Ellipse2D.Double blueCircle = new Ellipse2D.Double(width, height, width, height);
		graphics2D.fill(blueCircle);
		
	}
}
