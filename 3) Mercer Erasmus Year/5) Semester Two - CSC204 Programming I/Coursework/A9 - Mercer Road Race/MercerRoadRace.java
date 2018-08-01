// Yu-Ching Ho
// A9 Mercer Road Race

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;

public class MercerRoadRace {
	
	public static int userInput;
	
	// Asks for userInput. Loops if userInput not between 1 or 20
	public static void main(String[] args) {
		boolean ok = false;
		do {
			System.out.print("How many racers do you want? ");
			Scanner keyboard = new Scanner(System.in);
			userInput = keyboard.nextInt();
			if(userInput <= 0) {
				System.out.println("Please enter a valid number.\n");
			}
			else if(userInput >= 21) {
				System.out.println("There are too many MercMen! Please try again.\n");
			}
			else {
				ok = true;
				ArrayList<MercMan> racingMercMen = new ArrayList<MercMan>(userInput);
				for(int i = 0; i < userInput; i++) {
					racingMercMen.add(new MercMan(i, i, i, i));
				}
				drawWindow();
			}
			keyboard.close();
		}
		while(ok == false);
	}
	
	// drawWindow method
	public static void drawWindow() {
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setTitle("Yu-Ching's MercMens' Race");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RacerComponent component = new RacerComponent(userInput);
		frame.add(component);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
	}
}

