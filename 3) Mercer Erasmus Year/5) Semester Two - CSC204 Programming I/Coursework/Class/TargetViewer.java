import javax.swing.JFrame;

public class TargetViewer {

	// @param args
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.setTitle("Target");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Target circles = new Target();
		frame.add(circles);
		frame.setVisible(true);

	}
}
