public class Do_While_Loop {

	public static void main(String[] args) {
		int number;
		double total = 0;
		double count = 0;
		double average;

		do {
			TextIO.put("Enter your number or press '0' to end calculation: ");
			number = TextIO.getlnInt();
			total += number;
			count++;
			average = total / (count - 1);
		}
		while (number != 0);
		TextIO.putln("\nYou have entered " + (int)(count - 1) + " numbers.");
		TextIO.putf("The average is %1.2f. \n", average);
	}
}