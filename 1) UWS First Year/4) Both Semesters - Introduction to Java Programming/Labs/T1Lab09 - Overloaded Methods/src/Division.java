public class Division {

	public static void main(String[] args) {
			TextIO.putln("This is a division program.");
			TextIO.putln("1) Integer division with 2 numbers.");
			TextIO.putln("2) Real division with 2 numbers.");
			TextIO.putln("3) Quit.");
			int choice;
			do {	
				TextIO.put("\nEnter your choice: ");
				choice = TextIO.getlnInt();

				switch (choice) {
					case 1: 
						IntergerDivision();
						break;
					case 2:
						RealDivision();
						break;
					case 3: 
						TextIO.putln("You have quit.");	
						break;
					default: TextIO.putln("Invalid choice."); 
				}
			}	
			while (choice != 3);
		}	

	public static void IntergerDivision() {
		TextIO.put("Enter your first number: ");
		int number1 = TextIO.getlnInt();
					
		TextIO.put("Enter your second number: ");
		int number2 = TextIO.getlnInt();
					
		int division = (number1 / number2);
		TextIO.putln(number1 + " divided by " + number2 + " is " + division);
	}

	public static void RealDivision() {
		TextIO.put("Enter your first number: ");
		int number1 = TextIO.getlnInt();
						
		TextIO.put("Enter your second number: ");
		int number2 = TextIO.getlnInt();
					
		double division = (number1 / number2);
		TextIO.putf(number1 + " divided by " + number2 + " is %1.2f. \n", division);
	}
}