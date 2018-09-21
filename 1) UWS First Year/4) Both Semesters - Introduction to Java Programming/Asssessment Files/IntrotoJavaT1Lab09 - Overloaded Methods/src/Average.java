public class Average {

	public static void main(String[] args) {
		TextIO.putln("This program is to calculate the averages of numbers.");
		TextIO.putln("1) Enter 2 numbers");
		TextIO.putln("2) Enter 3 numbers");
		TextIO.putln("3) Enter 4 numbers");
		TextIO.putln("4) Quit");
		int choice;	
		
		do {	
			TextIO.put("\nEnter your choice: ");
			choice = TextIO.getlnInt();
			switch (choice) {
			case 1:
				average2(); 
				break;
			case 2:
				average3(); 
				break;
			case 3:
				average4(); 
				break;
			case 4:
				TextIO.putln("You have quit.");
				break;
			default:
				TextIO.putln("Invalid choice."); 
			}
					
		} 
		while (choice != 4);	
	}
					
	public static void average2() {
		TextIO.put("Enter your first number: ");
		int number1 = TextIO.getlnInt();
							
		TextIO.put("Enter your second number: ");
		int number2 = TextIO.getlnInt();
							
		double average = ((number1 + number2)/2);
		TextIO.putf("The average is %1.2f. \n", average);
	}
						
	public static void average3() {
		TextIO.put("Enter your first number: ");
		int number1 = TextIO.getlnInt();
						
		TextIO.put("Enter your second number: ");
		int number2 = TextIO.getlnInt();
							
		TextIO.put("Enter your third number: ");
		int number3 = TextIO.getlnInt();
							
		double average = ((number1 + number2 + number3)/3);
		TextIO.putf("The average is %1.2f. \n", average);	
	} 
						
	public static void average4() {
		TextIO.put("Enter your first number: ");
		int number1 = TextIO.getlnInt();
							
		TextIO.put("Enter your second number: ");
		int number2 = TextIO.getlnInt();
				
		TextIO.put("Enter your third number: ");
		int number3 = TextIO.getlnInt();
				
		TextIO.put("Enter your fourth number: ");
		int number4 = TextIO.getlnInt();
				
		double average = ((number1 + number2 + number3 + number4)/4);
		TextIO.putf("The average is %1.2f. \n", average);	
	}
}							