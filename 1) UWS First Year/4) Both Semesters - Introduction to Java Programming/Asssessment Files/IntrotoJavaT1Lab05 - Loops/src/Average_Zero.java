public class Average_Zero {

	public static void main(String[] args) {
		int inputNumber;
		int sum;
		int count;
		double average;

		sum = 0;
		count = 0;
		TextIO.put("Enter your first number: ");
		inputNumber = TextIO.getlnInt();
		TextIO.putln("Press '0' to end calculation.");
		
		while (inputNumber != 0) {
			sum += inputNumber;
			count++;
			TextIO.put("Enter your next number: ");
			inputNumber = TextIO.getlnInt();
		}

		if (count == 0) {
			TextIO.putln("No data entered.");
		} 
		else {
			average = ((double) sum) / count;
			TextIO.putln("You have entered " + count + " numbers.");
			TextIO.putf("The average is %1.2f. \n", average);
		}
	}
}