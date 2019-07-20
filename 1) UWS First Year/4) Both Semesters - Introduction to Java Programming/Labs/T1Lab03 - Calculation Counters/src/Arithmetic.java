public class Arithmetic {

	public static void main(String[] args) {
		int input, divide, remainder, squared;
		double divideDouble;

		TextIO.put("Input a number from 1 - 26: ");
		input = TextIO.getlnInt();
		divide = (input / 2);
		remainder = (input % 2);
		divideDouble = ((double) input / 2);
		squared = (input * input);

		TextIO.putln("The number you entered is " + input);
		TextIO.putln(input + " divided by 2 is " + divide + " remainder " + remainder);
		TextIO.putln((double) input + " divided by 2 is " + divideDouble);
		TextIO.putln(input + " squared is " + squared);
		TextIO.put("The letter that is number " + input + " in the alphabet is ");

		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		for (int i = 0; i <= input; i++) {
			if (i == input) {
				TextIO.put(alphabet[i - 1]);
			}
		}
	}
}