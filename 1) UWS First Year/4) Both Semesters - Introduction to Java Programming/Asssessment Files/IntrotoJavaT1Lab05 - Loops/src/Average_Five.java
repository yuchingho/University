public class Average_Five {

	public static void main(String[] args) {
		int total = 0;
		int input;
		TextIO.putln("Write down 5 random numbers: ");

		for (int i = 1; i <= 5; i++) {
			TextIO.put("Number " + i + ": ");
			input = TextIO.getlnInt();
			total += input;
		}
		TextIO.putln("Average = " + (total / 5));
	}
}