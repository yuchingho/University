// Yu-Ching Ho
// Assignment 8 - Dice Simulation

import java.util.Random;

public class Dice {
	
	private int sides;
	private int last;
	
	// Default constructor passed zero parameters
	public Dice() {
		sides = 6;
		last = 1;
	}
	
	// Constructor that passed side integer
	public Dice(int numOfSides) {
		sides = numOfSides;
		last = 1;
	}
	
	// Roll method passed nothing
	// Returns last as random number between 1 and sides
	public int roll() {
		Random random = new Random();
		last = random.nextInt(sides) + 1;
		return last;
	}
	
	// getLastRoll passed nothing
	public int getLastRoll() {
		return last;
	}
}
