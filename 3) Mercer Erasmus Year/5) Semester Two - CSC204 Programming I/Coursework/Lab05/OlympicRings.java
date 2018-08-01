import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
// A component that draws an alien face.

@SuppressWarnings("serial")
public class OlympicRings extends JComponent {

	public void paintComponent(Graphics g) {
		int ringDiameter = 100; // how big of rings we want to use

		// Recover Graphics2D
		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(10));

		// Draw a Ring
		g2.setColor(Color.BLUE);
		Ellipse2D.Double ring1 = new Ellipse2D.Double(10, 10, ringDiameter, ringDiameter);
		g2.fill(ring1);

		// Draw a Ring
		g2.setColor(Color.YELLOW);
		Ellipse2D.Double ring2 = new Ellipse2D.Double(70, 60, ringDiameter, ringDiameter);
		g2.fill(ring2);

		// Draw a Ring
		g2.setColor(Color.BLACK);
		Ellipse2D.Double ring3 = new Ellipse2D.Double(130, 10, ringDiameter, ringDiameter);
		g2.fill(ring3);

		// Draw a Ring
		g2.setColor(Color.GREEN);
		Ellipse2D.Double ring4 = new Ellipse2D.Double(190, 60, ringDiameter, ringDiameter);
		g2.fill(ring4);

		// Draw a Ring
		g2.setColor(Color.RED);
		Ellipse2D.Double ring5 = new Ellipse2D.Double(250, 10, ringDiameter, ringDiameter);
		g2.fill(ring5);

	}
}
