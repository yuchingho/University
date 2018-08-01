import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class MercManComponent extends JComponent {

	private int userChoice;

	public MercManComponent(int choice) {
		super();
		userChoice = choice;
	}

	// Part of the JComponent that draws things into the graphic window.
	public void paintComponent(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		int frameWidth = getWidth();
		int frameHeight = getHeight();

		switch (userChoice) {
		case 1: // Centered MercMan
			(new MercMan(frameWidth / 2 - 50, frameHeight / 2 - 100, 100, 200)).draw(graphics2D);
			break;

		case 2: // Four Corners
			(new MercMan(50, 0, 100, 200)).draw(graphics2D);
			(new MercMan(frameWidth - 100, 0, 100, 200)).draw(graphics2D);
			(new MercMan(frameWidth - 100, frameHeight - 200, 100, 200)).draw(graphics2D);
			(new MercMan(50, frameHeight - 200, 100, 200)).draw(graphics2D);
			break;

		case 3: // Line of Four
			int mmWidth = frameWidth / 4;
			(new MercMan(75, 0, mmWidth, frameHeight)).draw(graphics2D);
			(new MercMan(mmWidth+75, 0, mmWidth, frameHeight)).draw(graphics2D);
			(new MercMan((2 * mmWidth)+75, 0, mmWidth, frameHeight)).draw(graphics2D);
			(new MercMan((3 * mmWidth)+75, 0, mmWidth, frameHeight)).draw(graphics2D);
			break;

		case 4: // Crowded
			int tinyWidth = frameWidth / 10;
			int tinyHeight = frameHeight / 10;
			for (int x = 0; x < 10; x++)
				for (int y = 0; y < 10; y++)
					(new MercMan((x * tinyWidth)+25, y * tinyHeight, tinyWidth, tinyHeight)).draw(graphics2D);
			break;
			
		case 5: // Lines of Four with random colours
			int mmWidthRandom = frameWidth / 4;
			(new MercMan(75, 0, mmWidthRandom, frameHeight)).drawRandom(graphics2D);
			(new MercMan(mmWidthRandom+75, 0, mmWidthRandom, frameHeight)).drawRandom(graphics2D);
			(new MercMan((2 * mmWidthRandom)+75, 0, mmWidthRandom, frameHeight)).drawRandom(graphics2D);
			(new MercMan((3 * mmWidthRandom)+75, 0, mmWidthRandom, frameHeight)).drawRandom(graphics2D);
			break;
			
		default:
			System.out.println("Choice made is not correct. \nPlease try again!");
			break;

		}

	}
}
