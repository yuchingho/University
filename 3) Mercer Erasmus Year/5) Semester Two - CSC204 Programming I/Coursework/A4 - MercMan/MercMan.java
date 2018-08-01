import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

// Yu-Ching Ho
// Full-body Mug shot
// Completion date: 12/02/17

@SuppressWarnings("serial")
public class MercMan extends JComponent {

	public void paintComponent(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;

		int x = getWidth(); // 400
		int y = getHeight(); // 600

		// Ears
		graphics2D.setColor(Color.pink);
		Ellipse2D.Double ear = new Ellipse2D.Double((x / 2 - x / 5), (y / 4 - y / 6.3), x / 10, y / 15);
		graphics2D.fill(ear);
		Ellipse2D.Double ear2 = new Ellipse2D.Double((x / 2 + x / 8.8), (y / 4 - y / 6.3), x / 10, y / 15);
		graphics2D.fill(ear2);

		graphics2D.setColor(Color.black);
		Ellipse2D.Double earOutline = new Ellipse2D.Double((x / 2 - x / 5), (y / 4 - y / 6.3), x / 10, y / 15);
		graphics2D.draw(earOutline);
		Ellipse2D.Double earOutline2 = new Ellipse2D.Double((x / 2 + x / 8.8), (y / 4 - y / 6.3), x / 10, y / 15);
		graphics2D.draw(earOutline2);

		// Neck
		graphics2D.setColor(Color.pink);
		Rectangle neck = new Rectangle(x / 2, (y / 4 - y / 34), x / 30, y / 20);
		graphics2D.fill(neck);
		neck.translate(-(x / 30), 0);
		graphics2D.fill(neck);

		// Arms
		graphics2D.setColor(Color.pink);
		Ellipse2D.Double arm = new Ellipse2D.Double((x / 2 - x / 5.5), (y / 4), x / 10, y / 4);
		graphics2D.fill(arm);
		Ellipse2D.Double arm2 = new Ellipse2D.Double((x / 2 + x / 12.5), (y / 4), x / 10, y / 4);
		graphics2D.fill(arm2);

		// Hands
		graphics2D.setColor(Color.black);
		Ellipse2D.Double handOutline = new Ellipse2D.Double((x / 2 - x / 6), (y / 2 - y / 24), x / 10, y / 15);
		graphics2D.draw(handOutline);
		Ellipse2D.Double handOutline2 = new Ellipse2D.Double((x / 2 + x / 13), (y / 2 - y / 24), x / 10, y / 15);
		graphics2D.draw(handOutline2);

		graphics2D.setColor(Color.pink);
		Ellipse2D.Double hand = new Ellipse2D.Double((x / 2 - x / 6), (y / 2 - y / 24), x / 10, y / 15);
		graphics2D.fill(hand);
		Ellipse2D.Double hand2 = new Ellipse2D.Double((x / 2 + x / 13), (y / 2 - y / 24), x / 10, y / 15);
		graphics2D.fill(hand2);

		// Leg2
		graphics2D.setColor(Color.pink);
		Ellipse2D.Double leg2 = new Ellipse2D.Double((x / 2 + x / 60), (y / 2 + y / 5), x / 8, y / 3.5);
		graphics2D.fill(leg2);
		Ellipse2D.Double leg22 = new Ellipse2D.Double((x / 2 - x / 7), (y / 2 + y / 5), x / 8, y / 3.5);
		graphics2D.fill(leg22);

		// Leg1
		graphics2D.setColor(Color.red);
		Rectangle leg = new Rectangle((x / 2 + x / 40), (y / 2 + y / 12), x / 7, y / 5);
		graphics2D.fill(leg);
		leg.translate((int) -(x / 5.5), 0);
		graphics2D.fill(leg);

		// Feet
		graphics2D.setColor(Color.blue);
		Rectangle feet = new Rectangle((x / 2 + x / 40), (y / 2 + y / 4 + y / 8 + y / 13), x / 6, y / 17);
		graphics2D.fill(feet);
		feet.translate((int) -(x / 5.5), 0);
		graphics2D.fill(feet);

		// Body
		graphics2D.setColor(Color.gray);
		Rectangle body = new Rectangle(x / 2, (y / 4 + y / 400), x / 8, y / 3);
		graphics2D.fill(body);
		body.translate(-(x / 8), 0);
		graphics2D.fill(body);

		// Head
		graphics2D.setColor(Color.pink);
		Ellipse2D.Double head = new Ellipse2D.Double((x / 2 - x / 6), 0, x / 3, y / 4.5);
		graphics2D.fill(head);

		// Eyes
		graphics2D.setColor(Color.white);
		Ellipse2D.Double eye = new Ellipse2D.Double((x / 2 - x / 12), (y / 4 - y / 6.8), x / 20, y / 30);
		graphics2D.fill(eye);
		Ellipse2D.Double eye2 = new Ellipse2D.Double((x / 2 + x / 24), (y / 4 - y / 6.8), x / 20, y / 30);
		graphics2D.fill(eye2);

		// Iris
		graphics2D.setColor(Color.orange);
		Ellipse2D.Double iris = new Ellipse2D.Double((x / 2 - x / 14), (y / 4 - y / 7.3), x / 40, y / 60);
		graphics2D.fill(iris);
		Ellipse2D.Double iris2 = new Ellipse2D.Double((x / 2 + x / 18.4), (y / 4 - y / 7.3), x / 40, y / 60);
		graphics2D.fill(iris2);

		// Pupils
		graphics2D.setColor(Color.black);
		Ellipse2D.Double pupil = new Ellipse2D.Double((x / 2 - x / 15.5), (y / 4 - y / 7.5), x / 80, y / 120);
		graphics2D.fill(pupil);
		Ellipse2D.Double pupil2 = new Ellipse2D.Double((x / 2 + x / 16.5), (y / 4 - y / 7.5), x / 80, y / 120);
		graphics2D.fill(pupil2);

		// Hair
		graphics2D.setColor(Color.green);
		Ellipse2D.Double hair = new Ellipse2D.Double((x / 2 - x / 6), 0, x / 3, y / 9);
		graphics2D.fill(hair);

		// Nose
		graphics2D.setColor(Color.black);
		Line2D.Double nose = new Line2D.Double(x / 2, (y / 4.8 - y / 16), (x / 2 - x / 60), y / 6);
		graphics2D.draw(nose);
		Line2D.Double nose2 = new Line2D.Double(x / 2, (y / 4.8 - y / 22), (x / 2 - x / 60), y / 6);
		graphics2D.draw(nose2);

		// Mouth
		graphics2D.setColor(Color.black);
		Line2D.Double mouth = new Line2D.Double((x / 2 + x / 16), (y / 4.8 - y / 30), (x / 2 - x / 30), (y / 6 + y / 30));
		graphics2D.draw(mouth);

	}

}
