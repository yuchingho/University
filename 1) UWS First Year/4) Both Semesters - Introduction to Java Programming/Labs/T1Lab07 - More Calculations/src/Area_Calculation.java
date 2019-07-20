public class Area_Calculation {

	public static void main(String[] args) {
		TextIO.putln("This program is to calculate the area of a shape.");
		TextIO.putln("1) Circle");
		TextIO.putln("2) Rectangle");
		TextIO.put("\nEnter your choice: ");
		int choice = TextIO.getlnInt();
		switch (choice) {
			case 1:
				circle();
				break;
			case 2:
				rectangle();
				break;	
			default: TextIO.putln("Invalid choice."); 
		}
	}
		
	public static void circle() {
		TextIO.put("Enter the radius of your circle: ");
		int radius = TextIO.getlnInt();
		double areaCircle = Math.PI * radius * radius;
		TextIO.putln("Your cirle has an area of " + areaCircle + ".");
	}

	public static void rectangle() {
		TextIO.put("Height of your rectangle is: ");
		int height = TextIO.getlnInt();
		TextIO.put("Length of your rectangle is: ");
		int length = TextIO.getlnInt();
		TextIO.putln("Your rectangle has an area of " + (height * length) + ".");	
	}
}