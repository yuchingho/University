public class Average_Infinite {

	public static void main(String[] args) {
		int inputNumber;
		int sum;
		int count;
		double average;

		sum = 0;
		count = 0;
		TextIO.putln("Enter in a list of numbers and press '0' to end calculation.");
		TextIO.put("Enter a number: ");
		inputNumber = TextIO.getlnInt();
		
		while (inputNumber != 0) {
			sum += inputNumber;
			count++;
			TextIO.putln("Total = " + sum + ", number = " + inputNumber + ", count = " + count);
			TextIO.put("Enter a number: ");
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