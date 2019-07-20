/**
* A simulation of the Towers of Hanoi puzzle with just 3 disks.
* The application describes the puzzle and allows the user to move
* the disks between the 3 Poles until the puzzle is solved.
*/
public class TowersOfHanoi {

	public static void main(String[] args) {
		// Constants naming the 3 Poles.
		final char Pole_A = 'A';
		final char Pole_B = 'B';
		final char Pole_C = 'C';
			
		// Constants naming the 3 disks.
		final char SMALL_DISK = 'S';
		final char MEDIUM_DISK = 'M';
		final char LARGE_DISK = 'L';
			
		final int OPTIMAL_MOVES = 7; // The minimum number of moves required for 3 disks.
		char smallDisk = Pole_A;	 // The current location of the small disk.
		char mediumDisk = Pole_A;	 // The current location of the medium disk.
		char largeDisk = Pole_A;	 // The current location of the large disk.
		char chosenDisk;			 // Used to record which disk it is requested to move.
		char chosenPole;			 // Used to record which Pole to move the disk to.
			
		boolean valid = false;	// Used in validating that a request conforms with the rules.
		int moves = 0;	// Used to count the number of moves.
		char currentPole = Pole_A;	// Used to record where a disk is moving from
		// To help validate that the requested move is to a different Pole.
			
		TextIO.putln("				Welcome to the puzzle of the mini-towers of Hanoi!\n");
		TextIO.putln("In this version of the puzzle, there are 3 disks and 3 Poles.");
		TextIO.putln("There is a small disk. a medium disk and a large disk.");
		TextIO.putln("The 3 Poles are arranged left to right and labelled 'A', 'B' and 'C'.\n");
		TextIO.putln("Initially, all 3 disks are on the left Pole 'A'.");
		TextIO.putln("Your task is to move the 3 disks to the right Pole 'C'.");
		TextIO.putln("You can only move 1 disk at a time and you cannot put a larger disk");
		TextIO.putln("on top of a smaller disk.\n");
		TextIO.putln("You can use Pole 'B' in the middle as a temporary Pole for the disks.");
		TextIO.putln("To solve the puzzle, all disks must end up on Pole 'C'.\n");
		TextIO.putln("Good luck!\n");
			
		do {
			TextIO.putln("The small disk is on Pole " + smallDisk + ".");
			TextIO.putln("The medium disk is on Pole " + mediumDisk + ".");
			TextIO.putln("The large disk is on Pole " + largeDisk + ".\n");

// Used to count attempts to enter the disk and the Pole details so that an error message can be displayed in they are not legal.
			int attempts = 0;	
			do {
				if (attempts > 0) {
					TextIO.putln("There is no such disk!\n");
				}
				TextIO.putln("Which disk would you like to move?");
				TextIO.putln("						 Press:");
				TextIO.putln("						'S' for Small.");
				TextIO.putln("						'M' for Medium.");
				TextIO.putln("						'L' for Large.\n");
							
				chosenDisk = TextIO.getlnChar();
				chosenDisk = Character.toUpperCase (chosenDisk);
				attempts ++;
			}
			while (chosenDisk != SMALL_DISK && chosenDisk != MEDIUM_DISK && chosenDisk != LARGE_DISK);
			// Get and validate the details of the Pole the user wishes to move the disk to.
			attempts = 0;
			do {
				if (attempts > 0) {
					TextIO.putln("");
				}
				TextIO.putln("Which Pole do you want to move the disk to?");
				TextIO.putln("						 Press:");
				TextIO.putln("						'A' for Pole A.");
				TextIO.putln("						'B' for Pole B.");
				TextIO.putln("						'C' for Pole C.\n");
				chosenPole = TextIO.getlnChar();
				chosenPole = Character.toUpperCase (chosenPole);
				attempts ++;
			} 
			while (chosenPole != Pole_A && chosenPole != Pole_B && chosenPole != Pole_C);		
/*
 * Note: the main loop contains 2 switch statements, 1 to validate that the request does not
 * break the rules and one to actually move the disk to its new Pole. It would have been possible
 * to have just a single switch statement that does both the validation and the updating, but
 * splitting these activities avoid some code duplication and separates the validation and updating concerns.
 * Now validate that the requested move does not break the puzzle's rules.
 * 
 * */
			switch (chosenDisk) {
// The small disk can always move to another Pole. The choice is only invalid if the disk is already on the Pole in question.
				case SMALL_DISK:
					valid = (chosenPole != smallDisk);
					currentPole = smallDisk;
					break;
// The medium disk can move if it not under the small disk on its current Pole and if the Pole it is moving to does not 
// contain the small disk. Again, we check that if the disk is not already on the chosen Pole.
				case MEDIUM_DISK:
					valid = (mediumDisk != smallDisk && chosenPole != smallDisk && chosenPole != mediumDisk);
					currentPole = largeDisk;
					break;
// The large disk can only move if it not under the small or medium disk on its current Pole and if neither of the other 
// disks are on the chosen Pole. Again, we check that the disk is not already on the chosen Pole.
					case LARGE_DISK:
						valid = (smallDisk != largeDisk &&
						mediumDisk != largeDisk &&
						chosenPole != smallDisk &&
						chosenPole != mediumDisk &&
						chosenPole != largeDisk);
						currentPole = largeDisk;
						break;
				}
				if (!valid) {
					if (chosenPole == currentPole) {
						TextIO.putln("That disk is already on Pole " + chosenPole + ".\n");
					}
/*
 * This is a generic error message that does not include the details
 * of which disk is in the way or which Pole the offending disk is on.
 * One could include another switch statement at this point to go through
 * the combinations and give the details, or change the previous switch
 * statement to capture this information explicitly.
 * 
 * */
					else {
						TextIO.putln("You cannot move that disk to Pole" + chosenPole +
							" because there is at least one smaller disk in the way!\n");
					}
				}
				else {
					// Request is valid so move the disk.
					moves ++;
					switch (chosenDisk)	{
						case SMALL_DISK:
							smallDisk = chosenPole;
							break;
						case MEDIUM_DISK:
							mediumDisk = chosenPole;
							break;
						case LARGE_DISK:
							largeDisk = chosenPole;
							break;
						}
					}
				}
				while (!(smallDisk == Pole_C && mediumDisk == Pole_C && largeDisk == Pole_C));
				// The puzzle has been solved once we get to this point so display the outcome.
				TextIO.putln("Well done, all 3 disks are on Pole C and you have solved the puzzle of the Towers of Hanoi!");
				if (moves > OPTIMAL_MOVES) {
						TextIO.putln("It took you " + moves + "moves!");
						TextIO.putln("Try again as it can be solved in fewer moves than that.");
				}
				else {
					TextIO.putln("You solved the puzzle in the optimal number of moves: " + moves + " moves.");
				}
		}
}