import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ArrayListColour {
	
	public static void main(String[] args) {
		ArrayList<Color> aColour = new ArrayList<Color>();
		Scanner keyboard = new Scanner(System.in);
		boolean done = false;
		Random random = new Random();
		while(!done) {
			aColour.add(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
			System.out.print("Another colour? (0 = No | 1 = Yes) ");
			int answer = keyboard.nextInt();
			if(answer == 1) {
				done = false;
			}
			else {
				done = true;
			}
		}
		keyboard.close();
		print(aColour);
	}
	
	private static void print(ArrayList<Color> aColour) {
		for(Color c: aColour) {
			System.out.println(c);
		}
	}
}
