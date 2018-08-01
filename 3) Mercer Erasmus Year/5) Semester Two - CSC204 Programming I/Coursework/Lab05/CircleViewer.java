import javax.swing.JFrame;

public class CircleViewer {

	// @param args
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		// These numbers give you a frame with 500 x 500
		// (i.e. 8 pixel borders left, bottom, and right, and 31 pixel title bar at the top of the frame).
		frame.setTitle("Centered Circle");
		frame.setSize(516, 539); 
									
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create the Centered Circle
		CenteredCircle cc = new CenteredCircle();
		frame.add(cc);

		// Show Circle in the frame
		frame.setVisible(true);

	}
}
