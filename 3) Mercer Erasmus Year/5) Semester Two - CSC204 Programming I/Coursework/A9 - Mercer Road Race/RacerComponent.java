// Yu-Ching Ho
// A9 Mercer Road Race

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JComponent;

// JComponent calling all of MercMan's draw methods
@SuppressWarnings("serial")
public class RacerComponent extends JComponent {
	
	public static int userInput;
	public ArrayList<MercMan> racingMercMen;

	// Passing userInput of racers into RacerComponent
	public RacerComponent(int MercMen) {
		super();
		userInput = MercMen;
	}
	
	// Passing racingMercMen into RacerComponent
	public RacerComponent(ArrayList<MercMan> ArrayRacingMercMen) {
		super();
		racingMercMen = ArrayRacingMercMen;
	}
	
	public void paintComponent(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		int lanes = getHeight()/userInput;
		int racer_height = lanes;
		int racer_width = lanes/2;
		int x = racer_width/4;
		racingMercMen = new ArrayList<MercMan>();
		for(int i = 0; i < userInput; i++) {
			racingMercMen.add(new MercMan(x, i*racer_height, racer_width, lanes));
			racingMercMen.get(i).draw(graphics2D);
		}
	}
}
