import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

// This component draws two car shapes.

@SuppressWarnings("serial")
public class CarComponent extends JComponent {
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		Car car1 = new Car(0, 0);

		int x = getWidth() - 60;
		int y = getHeight() - 30;

		Car car2 = new Car(x, y);
		
		// Center car
		Car car3 = new Car((getWidth()/2) - 30, (getHeight()/2) - 15);
		Car car4 = new Car((getWidth() - 60), 0);
		Car car5 = new Car(0, (getHeight()-30));

		car1.draw(g2);
		car2.draw(g2);
		car3.draw(g2);
		car4.draw(g2);
		car5.draw(g2);
	}
}
