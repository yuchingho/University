import javax.swing.JFrame;

public class ShowOlympicRings {

	// @param args
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(375, 200);
		frame.setTitle("Olympic Rings");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create the Olympic Rings
		OlympicRings rings = new OlympicRings();
		frame.add(rings);

		// Show Rings in the frame
		frame.setVisible(true);

	}
}
