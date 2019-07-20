public class Temp_Convert {

	public static void main(String[] args) {
		menu();
	}
	
	public static void menu() {
		TextIO.putln("1) F to C");
		TextIO.putln("2) C to F");
		TextIO.put("\nEnter your choice: ");
		int choice;
		choice = TextIO.getlnInt();
		
		switch (choice) {
			case 1:
				TextIO.put("Enter a temperature: ");
				double FtoC = TextIO.getlnDouble();
				TextIO.putln(FtoC + "°F is " + FtoC(FtoC) + "°C");
				break;
			case 2:
				TextIO.put("Enter a temperature: ");
				double CtoF = TextIO.getlnDouble();
				TextIO.putln(CtoF + "CF is " + CtoF(CtoF) + "°F");
				break;
			default: TextIO.putln("Invalid choice.");
				break;
		}
	}
	
	public static double FtoC(double F) {
		return (F-32) * 5 / 9;
	}
	
	public static double CtoF(double C) {
		return (32 + (9 * C / 5));
	}
}