public class Menu_Loop {

	public static void main(String[] args) {
		TextIO.putln("Your Menu.");
		TextIO.putln("1: Option 1 happens if you choose this!");
		TextIO.putln("2: Option 2 happens if you choose this!");
		TextIO.putln("3: Option 3 happens if you choose this!");
		TextIO.putln("4: Exit.");
		int choice;
		
		do {
			TextIO.put("\nEnter your choice: ");
			choice = TextIO.getlnInt();
			switch (choice) {
			case 1:
				TextIO.put("Option 1 chosen.");
				break;
			case 2:
				TextIO.put("Option 2 chosen.");
				break;
			case 3:
				TextIO.put("Option 3 chosen.");
				break;
			case 4:
				TextIO.put("Quit option chosen.");
				break;
			default:
				TextIO.put("Invalid choice. Please try again.");
			}
		} 
		while (choice != 4);
	}
}