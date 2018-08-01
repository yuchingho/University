import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class ArrayObjectComponent extends JComponent {

	Color theColors[];
	
	public ArrayObjectComponent(Color c[]) {// constructor, same name as class
		theColors = c;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		double width = getWidth();
		double height = getHeight();
		
		int howMany = theColors.length;
		double circleWidth = width/howMany;
		double circleHeight = height/howMany;
		double x = 0;
		double y = 0;
		
		for(int i = 0; i < howMany; i++) {
			g2.setColor(theColors[i]); //setting the colors
			Ellipse2D.Double circle = new Ellipse2D.Double(x, y, circleWidth, circleHeight);
			g2.fill(circle);
			x += circleWidth;
			y += circleHeight;
		}
	}
}
