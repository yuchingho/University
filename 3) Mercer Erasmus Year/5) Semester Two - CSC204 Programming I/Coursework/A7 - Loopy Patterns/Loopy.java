// Yu-Ching Ho
// Assignment07 - Loopy Patterns

import java.util.Scanner;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JColorChooser;

public class Loopy {

	public static void main(String[] args) {
		int dimensions;
		int count;
		int graphicNumber;
		Color colour1;
		Color colour2;

		Scanner input = new Scanner(System.in);
		System.out.println("Which graphic do you want to produce?\n" + 
						   "0 = Graph Paper\n" + 
						   "1 = Concentric Circles\n" + 
						   "2 = Grid of Circles\n" + 
						   "3 = Parabolic Curve\n" + 
						   "4 = Cross of Circles\n");
		
		System.out.print("Please chooose a number: ");
		graphicNumber = input.nextInt(); // Verify the input is in range

		System.out.print("What dimension would you like for the graphic: ");
		dimensions = input.nextInt(); // Verify the dim is 100 - 1000

		System.out.print("What count would you like for this graphic: ");
		count = input.nextInt(); // Verify the count is 1 - DIM
		
		// Color-picker program from Java Swing
		colour1 = JColorChooser.showDialog(null, "Choose First Colour", Color.black); // default color
		colour2 = JColorChooser.showDialog(null, "Choose Second Colour", Color.orange); // default color
		
		// Build the JFrame, create the LoopyPattern object, and put it into the frame.
		JFrame frame = new JFrame();
		frame.setSize(dimensions + 16, dimensions + 38); // These add-ons are the thickness of the frame's border
		frame.setTitle("Yu-Ching's Loopy Graphics!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		LoopyPattern component = new LoopyPattern(dimensions, count, graphicNumber, colour1, colour2);
		frame.add(component);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		input.close();
	}

}
