import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;

// A component that draws a centered circle.

@SuppressWarnings("serial")
public class CenteredCircle extends JComponent {

	public void paintComponent(Graphics g) {
		// Recover Graphics2D
		Graphics2D g2 = (Graphics2D) g;
		int width = getWidth(); // width of the Component
		int height = getHeight(); // height of the Component

		// Draw a Solid Orange Circle
		g2.setColor(Color.ORANGE);
		Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, width, height);
		g2.fill(circle);

	}
}
