public class Temp_Convert_Table {
	
	public static int choice;

	public static void main(String[] args) {
		do {
			menu();
		}
		while (choice != 5);
	}
	
	public static void menu() {
		TextIO.putln("1) F to C");
		TextIO.putln("2) C to F");
		TextIO.putln("3) F to C Table");
		TextIO.putln("4) C to F Table");
		TextIO.putln("5) Quit");
		TextIO.put("\nEnter your choice: ");
		choice = TextIO.getlnInt();
		
		switch (choice) {
			case 1:
				TextIO.put("Enter a temperature: ");
				double FtoC = TextIO.getlnDouble();
				TextIO.putln(FtoC + "°F is " + FtoC(FtoC) + "°C\n");
				break;
			case 2:
				TextIO.put("Enter a temperature: ");
				double CtoF = TextIO.getlnDouble();
				TextIO.putln(CtoF + "CF is " + CtoF(CtoF) + "°F\n");
				break;
			case 3:
				displayTable(convertedFtoC(), "Fahrenheit", "Celsius");
				break;
			case 4:
				displayTable(convertedCtoF(), "Celsius", "Fahrenheit");
				break;
			case 5:
				TextIO.putln("You have quit.");
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
	
	public static double[] convertedFtoC() { 
		double[] tempCelsius = new double[11];
		int arrayCount = 0;
		double sum = 0;
		for (int i = 0; i <= 100; i+= 10) {
			sum = FtoC(i);
			tempCelsius[arrayCount] = sum;
			arrayCount++;
		}
		return tempCelsius; 
	}
	
	public static double[] convertedCtoF() { 
		double[] tempFahrenheit = new double[11];
		int arrayCount = 0;
		double sum = 0;
		for (int i = 0; i <= 100; i+= 10) {
			sum = CtoF(i);
			tempFahrenheit[arrayCount] = sum;
			arrayCount++;
		}
		return tempFahrenheit; 
	}
	
	public static void displayTable(double[] table, String header1, String header2) { 
		TextIO.put(header1, 15); // display header for column 1 in 15 character field 
		TextIO.putln(header2, 15); // display header for column 2 in 15 character field 
			
		for (int i = 0; i < table.length; i++) { 
				TextIO.putf("%15d %12.2f %n", i*10, table[i]); 
		}
		// The first parameter is a format string. 
		// The first % instruction applies to the first argument after the string, the second to the second etc. 
		// %15d – display an integer right justified in 15 character field 
		// %12.2f – display a floating point number with 2 places after the  point right justified in 15 character field 
	}
}