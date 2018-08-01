import java.awt.Color;
import java.util.Scanner;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

public class DemoMain {

	public static void main(String[] args) {
		int dim = 500;
		
		Scanner in = new Scanner(System.in);
		System.out.print("How many? ");
		int howMany = in.nextInt();
		
		Color theColors[] = new Color[howMany];
		for(int i = 0; i < howMany; i++) {
			theColors[i] = JColorChooser.showDialog(null, "Choose Second Color", Color.orange);
		}
		in.close();
		
		JFrame frame = new JFrame();
		frame.setSize(dim+16, dim+38);;
		frame.setTitle("Demo Arrays of Objects");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ArrayObjectComponent component = new ArrayObjectComponent(theColors);
		frame.add(component);
		
		frame.setVisible(true);
	}
}
