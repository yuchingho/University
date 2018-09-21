public class Simple_Calculator {

	public static void main(String[] args) {
		TextIO.put("Enter a number: ");
		int x = TextIO.getlnInt();
		TextIO.put("Enter another number: ");
		int y = TextIO.getlnInt();
		int operation;

		do {
			TextIO.putln("\n1: add");
			TextIO.putln("2: subtract");
			TextIO.putln("3: multiply");
			TextIO.putln("4: integer division");
			TextIO.putln("5: floating point division");
			TextIO.putln("6: quit\n");
			TextIO.put("Enter your operation: ");
			operation = TextIO.getlnInt();

			switch (operation) {
			case 1:
				TextIO.putln(x + " + " + y + " = " + (x + y));
				break;
			case 2:
				TextIO.putln(x + " - " + y + " = " + (x - y));
				break;
			case 3:
				TextIO.putln(x + " * " + y + " = " + (x * y));
				break;
			case 4:
				TextIO.putln(x + " / " + y + " = " + (x / y));
				break;
			case 5:
				TextIO.putln(x + " / " + y + " = " + ((double) x / (double) y));
				break;
			case 6:
				break;
			default:
				TextIO.putln("Invalid number!");
				break;
			}
		} 
		while (operation != 6);
		TextIO.putln("You have quit the program.");
	}
}