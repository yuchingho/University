// Yu-Ching Ho
// Assignment07 - Loopy Patterns

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class LoopyPattern extends JComponent {
	
	private int dimensions;
	private int count;
	private int graphicNumber;
	private Color colour1; 
	private Color colour2; 
	private Color currentColour;

	public LoopyPattern(int d, int c, int g, Color c1, Color c2) {
		super();
		dimensions = d;
		count = c;
		graphicNumber = g;
		colour1 = c1;
		colour2 = c2;
		currentColour = c1;
	}
	
	private void switchColours() {
		// Code to alternate between color1 and color2, saving in currentColour
		if(currentColour == colour1)
			currentColour = colour2;
		else
			currentColour = colour1;
	}

	public void paintComponent(Graphics graphics) {
		// Recover Graphics2D
		Graphics2D g2 = (Graphics2D) graphics;
		g2.setColor(currentColour);
		
		int gap = dimensions/count;
		switch (graphicNumber) {
			case 0: // Graph Paper
				for(int i = 1; i <= count; i++) {
					g2.setColor(currentColour);
					g2.draw(new Line2D.Float(0, i*gap, dimensions, i*gap));
					g2.draw(new Line2D.Float(i*gap, 0, i*gap, dimensions));
					switchColours();
				}
				break;
			case 1: // Concentric Circles
				for(int i = 0; i < count; i++) {
					g2.setColor(currentColour);
					Ellipse2D.Double circle = new Ellipse2D.Double((i*gap)/2,(i*gap)/2, dimensions-(i*gap), dimensions-(i*gap));
					g2.fill(circle);
					switchColours();
				}
				break;
			case 2: // Grid of Circles
				for(int row = 0; row < count; row++) {
					for(int col = 0; col < count; col++) {
						g2.setColor(currentColour);
						Ellipse2D.Double Gcircle = new Ellipse2D.Double(row*gap, col*gap, gap, gap);
						g2.fill(Gcircle);
						switchColours();
					}
				}
				break;
			case 3: // Parabolic Curve
				for(int i = 0; i <= count; i++) {
					g2.setColor(currentColour);
					g2.draw(new Line2D.Float(5, i*gap, i*gap, dimensions-5));
					switchColours();
				}
				break;
			case 4: // Cross of Circles
				Ellipse2D.Double Scircle = new Ellipse2D.Double(0, 0, dimensions, dimensions);
				g2.draw(Scircle);
				for(int i = 0; i < count; i++) {
					g2.setColor(currentColour);
					Ellipse2D.Double Scircle1 = new Ellipse2D.Double((dimensions/2), i*gap, gap, gap);
					Ellipse2D.Double Scircle2 = new Ellipse2D.Double(i*gap, (dimensions/2), gap, gap);
					g2.fill(Scircle1);
					g2.fill(Scircle2);
					switchColours();
		    	}
				break;
			/*
			case 5: // Diagonal circle
				int size = dimensions/count;
				for(int i = 0; i < count; i++) {
					g2.setColor(currentColour);
					Ellipse2D.Double circle = new Ellipse2D.Double(i*size, i*size, size, size);
					g2.fill(circle);
					switchColours();
				}
				break;
			case 6: // Inverted circle
				int size2 = dimensions/count;
				for(int i = count; i > 0; i--) {
					Ellipse2D.Double circle2 = new Ellipse2D.Double(0, 0, i*size2, i*size2);
					g2.fill(circle2);
					switchColours();
					g2.setColor(currentColour);
				}
				break;
			*/
		}
	}
}
