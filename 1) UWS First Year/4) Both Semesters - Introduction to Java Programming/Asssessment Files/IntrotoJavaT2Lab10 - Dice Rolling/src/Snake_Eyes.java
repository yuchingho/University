public class Snake_Eyes {

	public static void main(String[] args) {
		try {
			int rolls = DiceTotal(2);
			TextIO.putln("It took " + rolls + " rolls to get snake eyes.");
		}
		catch (IllegalArgumentException iae) {
			TextIO.putln(iae.getLocalizedMessage());
		}
	} 
 
	public static int DiceTotal(int N) {
		if (N < 2 || N > 12) {
			throw new IllegalArgumentException("Impossible total for a pair of dice.");
		}
		int die1, die2, roll, rollCount = 0;  
		do {
			die1 = (int)(Math.random()*6) + 1;
			die2 = (int)(Math.random()*6) + 1;
			roll = die1 + die2;
			rollCount++;
		} 
		while (roll != N);
		return rollCount;
	}
}