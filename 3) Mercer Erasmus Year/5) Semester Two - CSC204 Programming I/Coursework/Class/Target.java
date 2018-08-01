import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Target extends JComponent {

	public void paintComponent(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		int width = getWidth(); 
		int height = getHeight(); 

		graphics2D.setColor(Color.RED);
		Ellipse2D.Double yellowCircle = new Ellipse2D.Double(0, 0, width, height);
		graphics2D.fill(yellowCircle);
		
		graphics2D.setColor(Color.WHITE);
		Ellipse2D.Double blackCircle = new Ellipse2D.Double(width/6, height/6, 2*width/3, 2*height/3);
		graphics2D.fill(blackCircle);
		
		graphics2D.setColor(Color.RED);
		Ellipse2D.Double redCircle = new Ellipse2D.Double(width/3, height/3, width/3, height/3);
		graphics2D.fill(redCircle);
		
		// adding lines in center circle
		graphics2D.setColor(Color.BLACK);
		Line2D.Double line = new Line2D.Double(width/2, height/3, width/2, 2*height/3);
		graphics2D.draw(line);
		
		Line2D.Double verticle = new Line2D.Double(width/3, height/2, 2*width/3, height/2);
		graphics2D.draw(verticle);
		
		// adding squares
		graphics2D.setColor(Color.BLUE);
		Rectangle square = new Rectangle(width/3, height/3, width/8, height/8);
		graphics2D.fill(square);
		
		// translate function
		
	}
}
