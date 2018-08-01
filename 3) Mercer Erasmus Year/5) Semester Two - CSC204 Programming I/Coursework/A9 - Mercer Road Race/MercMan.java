// Yu-Ching Ho
// A9 Mercer Road Race

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class MercMan extends JComponent {
	
	public boolean smile;
	public int xCoord = getWidth()/2;
	public int yCoord = getHeight();
	public int width = getWidth();
	public int height = getHeight();
	
	public void draw(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		// Ears
		graphics2D.setColor(Color.pink);
		Ellipse2D.Double ear = new Ellipse2D.Double((xCoord - width / 5), yCoord + (height/4 - height/ 6.3), width / 10, height / 15);
		graphics2D.fill(ear);
		Ellipse2D.Double ear2 = new Ellipse2D.Double((xCoord + width / 8.8), yCoord + (height/4 - height/ 6.3), width / 10, height / 15);
		graphics2D.fill(ear2);

		graphics2D.setColor(Color.black);
		Ellipse2D.Double earOutline = new Ellipse2D.Double((xCoord - width / 5), yCoord + (height/4 - height / 6.3), width / 10, height / 15);
		graphics2D.draw(earOutline);
		Ellipse2D.Double earOutline2 = new Ellipse2D.Double((xCoord + width / 8.8), yCoord + (height/4 - height / 6.3), width / 10, height / 15);
		graphics2D.draw(earOutline2);

		// Neck
		graphics2D.setColor(Color.pink);
		Rectangle neck = new Rectangle(xCoord, yCoord + (height/4 - height / 34), width / 30, height / 20);
		graphics2D.fill(neck);
		neck.translate(-(width / 30), 0);
		graphics2D.fill(neck);

		// Arms
		graphics2D.setColor(Color.pink);
		Ellipse2D.Double arm = new Ellipse2D.Double((xCoord - width / 5.5), yCoord + height/4, width / 10, height / 4);
		graphics2D.fill(arm);
		Ellipse2D.Double arm2 = new Ellipse2D.Double((xCoord + width / 12.5), yCoord + height/4, width / 10, height / 4);
		graphics2D.fill(arm2);

		// Hands
		graphics2D.setColor(Color.black);
		Ellipse2D.Double handOutline = new Ellipse2D.Double((xCoord - width / 6), yCoord + (height/2 - height / 24), width / 10, height / 15);
		graphics2D.draw(handOutline);
		Ellipse2D.Double handOutline2 = new Ellipse2D.Double((xCoord + width / 13), yCoord + (height/2 - height / 24), width / 10, height / 15);
		graphics2D.draw(handOutline2);

		graphics2D.setColor(Color.pink);
		Ellipse2D.Double hand = new Ellipse2D.Double((xCoord - width / 6), yCoord + (height/2 - height / 24), width / 10, height / 15);
		graphics2D.fill(hand);
		Ellipse2D.Double hand2 = new Ellipse2D.Double((xCoord + width / 13), yCoord + (height/2 - height / 24), width / 10, height / 15);
		graphics2D.fill(hand2);

		// Leg2
		graphics2D.setColor(Color.pink);
		Ellipse2D.Double leg2 = new Ellipse2D.Double((xCoord + width / 60), yCoord + (height/2 + height / 5), width / 8, height / 3.5);
		graphics2D.fill(leg2);
		Ellipse2D.Double leg22 = new Ellipse2D.Double((xCoord - width / 7), yCoord + (height/2 + height / 5), width / 8, height / 3.5);
		graphics2D.fill(leg22);

		// Leg1
		graphics2D.setColor(Color.red);
		Rectangle leg = new Rectangle((xCoord + width / 40), yCoord + (height/2 + height / 12), width / 7, height / 5);
		graphics2D.fill(leg);
		leg.translate((int) -(width / 5.5), 0);
		graphics2D.fill(leg);

		// Feet
		graphics2D.setColor(Color.blue);
		Rectangle feet = new Rectangle((xCoord + width / 40), yCoord + (height/2 + height / 4 + height / 8 + height / 13), width / 6, height / 17);
		graphics2D.fill(feet);
		feet.translate((int) -(width / 5.5), 0);
		graphics2D.fill(feet);

		// Body
		graphics2D.setColor(Color.gray);
		Rectangle body = new Rectangle(xCoord, yCoord + (height/4 + height / 400), width / 8, height / 3);
		graphics2D.fill(body);
		body.translate(-(width / 8), 0);
		graphics2D.fill(body);

		// Head
		graphics2D.setColor(Color.pink);
		Ellipse2D.Double head = new Ellipse2D.Double((xCoord - width / 6), yCoord, width / 3, height / 4.5);
		graphics2D.fill(head);

		// Eyes
		graphics2D.setColor(Color.white);
		Ellipse2D.Double eye = new Ellipse2D.Double((xCoord - width / 12), yCoord + (height/4 - height / 6.8), width / 20, height / 30);
		graphics2D.fill(eye);
		Ellipse2D.Double eye2 = new Ellipse2D.Double((xCoord + width / 24), yCoord + (height/4 - height / 6.8), width / 20, height / 30);
		graphics2D.fill(eye2);

		// Iris
		graphics2D.setColor(Color.orange);
		Ellipse2D.Double iris = new Ellipse2D.Double((xCoord - width / 14), yCoord + (height/4 - height / 7.3), width / 40, height / 60);
		graphics2D.fill(iris);
		Ellipse2D.Double iris2 = new Ellipse2D.Double((xCoord + width / 18.4), yCoord + (height/4 - height / 7.3), width / 40, height / 60);
		graphics2D.fill(iris2);

		// Pupils
		graphics2D.setColor(Color.black);
		Ellipse2D.Double pupil = new Ellipse2D.Double((xCoord - width / 15.5), yCoord + (height/4 - height / 7.5), width / 80, height / 120);
		graphics2D.fill(pupil);
		Ellipse2D.Double pupil2 = new Ellipse2D.Double((xCoord + width / 16.5), yCoord + (height/4 - height / 7.5), width / 80, height / 120);
		graphics2D.fill(pupil2);

		// Hair
		graphics2D.setColor(Color.green);
		Ellipse2D.Double hair = new Ellipse2D.Double((xCoord - width / 6), yCoord, width / 3, height / 9);
		graphics2D.fill(hair);

		// Nose
		graphics2D.setColor(Color.black);
		Line2D.Double nose = new Line2D.Double(xCoord, yCoord + (height/4.8 - height / 16), (xCoord - width / 60), yCoord + height / 6);
		graphics2D.draw(nose);
		Line2D.Double nose2 = new Line2D.Double(xCoord, yCoord + (height/ 4.8 - height / 22), (xCoord - width / 60), yCoord + height / 6);
		graphics2D.draw(nose2);

		// Mouth
		graphics2D.setColor(Color.black);
		Line2D.Double mouth = new Line2D.Double((xCoord + width / 16), yCoord + (height/ 4.8 - height / 30), (xCoord - width / 30), yCoord + (height / 6 + height / 30));
		graphics2D.draw(mouth);
	}
	
	public int getxCoord() { 
		return xCoord; 
	}
	
	public int getyCoord() { 
		return yCoord; 
	}
	
	public void setxCoord(int xCoordinate) { 
		this.xCoord = xCoordinate; 
	}
	
	public void setyCoord(int yCoordinate) { 
		this.yCoord = yCoordinate; 
	}
	
	public MercMan(int startX, int startY, int xWidth, int yHeight) {
		xCoord = startX;
		yCoord = startY;
		width = xWidth;
		height = yHeight;
	}

	public boolean setSmile(boolean s) {
		// winning = smile
		// losing = no smile
		return smile;
	}
}