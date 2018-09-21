/**
*  How many random people do you have to select before you find a duplicate birthday?
*  (that is, two people who were born on the same day of the same month, but not necessarily in the same year).
*  This program simulates the process.
*  (It ignores the possibility of people born on a leap day.)
*/

public class BirthdayArray {

	public static void main(String[] args) {
		TextIO.putln("This program simulates taking people at random until two have been found who were");
		TextIO.putln("born on the same day of the year.\n");
       
		do {
			birthdayProblem();
			TextIO.put("\nAgain? (Y/N):  ");
		} 
		while (TextIO.getlnBoolean() );
		TextIO.putln("\nOk, goodbye!");
	}
   
/**
* Simulate choosing people at random and checking the day of the year they were born on.
* If the birthday is the same as one that was seen previously, stop, 
* and output the number of people who were checked.
*/
	private static void birthdayProblem() {
		boolean[] used;  
		// For recording the possible birthdays that have been seen so far.
		// A value of true in used[i] means that a person whose birthday is the i-th day of the year has been found.
		int count = 0;	// The number of people who have been checked.
		used = new boolean[365];  // Initially, all entries are false.

		while (true) {
        // Select a birthday at random, from 0 to 364.
		// If the birthday has already been used, quit.
		// Otherwise, record the birthday as used.
			int birthday;  // The selected birthday.
			birthday = (int)(Math.random()*365);
			count++;
			if (used[birthday])	// This day was found before; It's a duplicate.
				break;
			used[birthday] = true;
      }
      TextIO.putln("A duplicate birthday was found after " + count + " tries.");
   }
}