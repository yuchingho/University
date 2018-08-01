// Yu-Ching Ho
// Assignment 8 - Dice Simulation

public class DiceSim {
	public int sidesOfDice;
	public int numOfDice;
	public int numOfRolls;
	public int[] countOfRolls;
	public Dice[] theDice;

	public DiceSim() {
		sidesOfDice = 6;
		numOfDice = 1;
		numOfRolls = 100;

		// countOfRolls = sidesOfDice x numOfDice + 1
		// Then initalize countOfRolls to zero
		countOfRolls = new int[sidesOfDice * numOfDice + 1];
		for(int i = 0; i < sidesOfDice * numOfDice + 1; i++) {
			countOfRolls[i] = 0;
		}
		
		// theDice holds numOfDice with correct number of sides
		theDice = new Dice[numOfDice];
		for(int i = 0; i < numOfDice; i++) {
			theDice[i] = new Dice();
		}
	}

	// Constructor with one argument
	public DiceSim(int arg1) {
		numOfDice = 1;
		sidesOfDice = 6;
		numOfRolls = arg1;
		for(int i = 0; i < sidesOfDice * numOfDice + 1; i++) {
			countOfRolls[i] = 0;
		}
		theDice = new Dice[numOfDice];
		for(int i = 0; i < numOfDice; i++) {
			theDice[i] = new Dice();
		}
	}

	// Constructor with two arguments
	public DiceSim(int arg1, int arg2) {
		numOfDice = arg2;
		sidesOfDice = 6;
		numOfRolls = arg1;
		for(int i = 0; i < sidesOfDice * numOfDice + 1; i++) {
			countOfRolls[i] = 0;
		}
		theDice = new Dice[numOfDice];
		for(int i = 0; i < numOfDice; i++) {
			theDice[i] = new Dice();
		}
	}

	// Constructor with three arguments
	public DiceSim(int arg1, int arg2, int arg3) {
		numOfDice = arg2;
		sidesOfDice = arg3;
		numOfRolls = arg1;
		countOfRolls = new int[sidesOfDice * numOfDice + 1];
		for(int i = 0; i < sidesOfDice * numOfDice + 1; i++) {
			countOfRolls[i] = 0;
		}
		theDice = new Dice[numOfDice];
		for(int i = 0; i < numOfDice; i++) {
			theDice[i] = new Dice();
		}
	}

	public void runSimulation() {
		Dice dice = new Dice();
		for(int i = 0; i < numOfDice; i++) {
			for(int k = 0; k < 13; k++) {
				int sum = dice.roll() + dice.roll();
				countOfRolls[sum]++;
			}
		}
	}

	public void displayCount() {
		for(int i = 2; i < 13; i++) {
			if(i <= 9) {
				System.out.print(" ");
			}
			System.out.println(i + ": " + countOfRolls[i]);
		}
	}

	public void graphCount() {
		for(int i = 2; i < 13; i++) {
			if(i <= 9) {
				System.out.print(" ");
			}
			System.out.print(i + ": ");
			for(int j = 0; j < countOfRolls[i]; j++)
				System.out.print("*");
			System.out.println();
		}
	}
}