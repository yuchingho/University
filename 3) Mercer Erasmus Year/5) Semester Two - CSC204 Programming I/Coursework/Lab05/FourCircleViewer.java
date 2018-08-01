import javax.swing.JFrame;

public class FourCircleViewer {

	// @param args
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.setTitle("Four Circles");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		FourCircles circles = new FourCircles();
		frame.add(circles);
		frame.setVisible(true);

	}
}
