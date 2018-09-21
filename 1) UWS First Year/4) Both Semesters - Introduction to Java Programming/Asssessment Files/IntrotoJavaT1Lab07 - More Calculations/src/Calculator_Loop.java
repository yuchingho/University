public class Calculator_Loop {

	public static void main(String[] args) {
		TextIO.putln("1) Add");
		TextIO.putln("2) Subtract");
		TextIO.putln("3) Multiply");
		TextIO.putln("4) Integer Division");
		TextIO.putln("5) Floating Point Division");
		TextIO.putln("6) Area of Circle");
		TextIO.putln("7) Area of Rectangle");
		TextIO.putln("8) Quit");
		int operation;
		
		do {
			TextIO.put("\nSelect an option: ");	
			operation = TextIO.getlnInt();

			switch (operation) {
				case 1:
					add();
					break;
				case 2:
					subtract();
					break;
				case 3:
					multiply();
					break;
				case 4:
					IntergerDivision();
					break;
				case 5:
					FloatingPointDivision();
					break;
				case 6:
					circle();
					break;
				case 7: 
					rectangle();
					break;
				case 8:
					TextIO.putln("You have quit the program.");
					break;
				default:TextIO.put("Invalid choice!");
					break;	
			}
		} 
		while (operation != 8);	
	}
	
	public static void add() {
		TextIO.put("Number 1: ");
		int a = TextIO.getlnInt();
		TextIO.put("Number 2: ");
		int b = TextIO.getlnInt();
		TextIO.putln(a + " + " + b + " = " + (a+b));	
	}
			
	public static void subtract() {
		TextIO.put("Number 1: ");
		int a = TextIO.getlnInt();
		TextIO.put("Number 2: ");
		int b = TextIO.getlnInt();
		TextIO.putln(a + " - " + b + " = " + (a-b));	
	}
			
	public static void multiply() {
		TextIO.put("Number 1: ");
		int a = TextIO.getlnInt();
		TextIO.put("Number 2: ");
		int b = TextIO.getlnInt();
		TextIO.putln(a + " * " + b + " = " + (a*b));	
	}
			
	public static void IntergerDivision() {
		TextIO.put("Number 1: ");
		int a = TextIO.getlnInt();
		TextIO.put("Number 2: ");
		int b = TextIO.getlnInt();
		TextIO.putln(a + " / " + b + " = " + (a/b));	
	}
			
	public static void FloatingPointDivision() {
		TextIO.put("Number 1: ");
		int a = TextIO.getlnInt();
		TextIO.put("Number 2: ");
		int b = TextIO.getlnInt();
		TextIO.putln((double)a + " / " + (double)b + " = " + (a/b));	
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