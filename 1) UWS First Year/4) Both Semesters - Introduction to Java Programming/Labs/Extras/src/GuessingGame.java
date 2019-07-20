public class GuessingGame {
	
	static int gamesWon; // The number of games won by the user.
	
	public static void main(String[] args) {
		gamesWon = 0; // This is actually redundant, since 0 is the default initial value.
		TextIO.putln("Let's play a game. I'll pick a number between 1 and 100, and you try to guess it.");
		boolean playAgain;
		do {
			playGame(); // call subroutine to play one game
			TextIO.put("Would you like to play again? ");
			playAgain = TextIO.getlnBoolean();
		}
		while (playAgain);
		TextIO.putln("\nYou won " + gamesWon + " games.");
		TextIO.putln("Thanks for playing. Goodbye.");
	} // end of main()
	
	static void playGame() {
		int computersNumber; // A random number picked by the computer.
		int usersGuess; // A number entered by user as a guess.
		int guessCount; // Number of guesses the user has made.
		computersNumber = (int)(100 * Math.random()) + 1;
		// The value assigned to computersNumber is a randomly
		// chosen integer between 1 and 100, inclusive.
		guessCount = 0;
		TextIO.put("\nWhat is your first guess? ");
		while (true) {
			usersGuess = TextIO.getInt(); // Get the user’s guess.
			guessCount++;
			if (usersGuess == computersNumber) {
				TextIO.putln("You got it in " + guessCount + " guesses! My number was " + computersNumber);
				gamesWon++; // Count this game by incrementing gamesWon.
				break; // The game is over; the user has won.
			}
			if (guessCount == 6) {
				TextIO.putln("You didn't get the number in 6 guesses.");
				TextIO.putln("You lose. My number was " + computersNumber);
				break; // The game is over; the user has lost.
			}
			// If we get to this point, the game continues.
			// Tell the user if the guess was too high or too low.
			if (usersGuess < computersNumber)
				TextIO.put("That's too low. Try again: ");
			else if (usersGuess > computersNumber)
				TextIO.put("That's too high. Try again: ");
		}
	} // end of playGame()
} // end of class GuessingGame2