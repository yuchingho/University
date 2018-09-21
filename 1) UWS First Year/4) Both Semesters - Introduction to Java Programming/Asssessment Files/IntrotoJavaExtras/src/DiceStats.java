/** Simulates dice throw using a pseudo-random number generator and computes the frequency of each dice value (1-6) */
import java.util.Scanner;

public class DiceStats {
	
	public static void main(String[] args) {
		// Array for counting the frequency of each dice value.
		int[] frequency = new int[6];
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the number of times to throw the dice: ");
		int noOfThrows = keyboard.nextInt();
		for (int i = 1; i <= noOfThrows; i++) {
			int diceValue = (int)(6.0*Math.random()) + 1;
			System.out.println(i + ". dice value: " + diceValue);
			frequency[diceValue-1]++;
		}
		for (int diceValue = 1; diceValue <= 6; diceValue++) {
			System.out.println("No. of times the dice value is " + diceValue + ": " + frequency[diceValue-1]);
		}
		keyboard.close();
	}
}