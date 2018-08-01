import javax.swing.JFrame;

public class MercManViewer {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.pack();
		frame.setSize(416, 640);
		frame.setTitle(" Yu-Ching Ho's MercMan");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MercMan MercMan = new MercMan();
		frame.add(MercMan);
	}

}
