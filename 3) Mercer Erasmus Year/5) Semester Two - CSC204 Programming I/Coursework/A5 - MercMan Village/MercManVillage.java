import java.util.Scanner;
import javax.swing.JFrame;

public class MercManVillage {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setTitle("MercMan Village");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		System.out.println("Pick a village:");
		System.out.println("\t1. Centered MercMan");
		System.out.println("\t2. Four Corners");
		System.out.println("\t3. Line of Four");
		System.out.println("\t4. Crowded");
		System.out.println("\t5. Four Colourful Inmates");
		System.out.print("Your Choice? ");
		
		Scanner keyboard = new Scanner(System.in);
		int choice = keyboard.nextInt();
		MercManComponent component = new MercManComponent(choice);
		frame.add(component);
		frame.setVisible(true);
		
		keyboard.close();
	}
}
