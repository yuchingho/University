import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JComponent;
// A component that draws an alien face 

@SuppressWarnings("serial")
public class FaceComponent extends JComponent {

	public void paintComponent(Graphics g) {
		// Recover Graphics2D
		Graphics2D g2 = (Graphics2D) g;

		// Draw the head
		Ellipse2D.Double head = new Ellipse2D.Double(15, 10, 100, 150);
		g2.draw(head);

		// Draw the eyes
		g2.setColor(Color.RED);
		Rectangle eye = new Rectangle(35, 70, 15, 15);
		g2.fill(eye);
		eye.translate(50, 0);
		g2.fill(eye);

		// Draw the mouth
		Line2D.Double mouth = new Line2D.Double(50, 110, 80, 110);
		g2.setColor(Color.RED);
		g2.draw(mouth);

		// Draw the greeting
		g2.setColor(Color.GREEN);
		g2.drawString("Hello, World!", 25, 175);
		
	}
}
