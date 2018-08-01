public class MyBools {
	public static void main(String[] args) {
		boolean B1 = true;
		System.out.print("B1 (true)");
		printBool(B1);
		
		boolean B2 = (3 == 3);
		System.out.print("B2 (3 == 3)");
		printBool(B2);
		
		boolean B3 = (12 != 19);
		System.out.print("B3 (12 != 19)");
		printBool(B3);
		
		boolean B4 = (3 < 15 - 6);
		System.out.print("B4 (3 < 15 - 6)");
		printBool(B4);
		
		boolean B5 = (3 > 15 - 6);
		System.out.print("B5 (3 > 15 - 6)");
		printBool(B5);
		
		boolean B6 = (12.2 >= 12.7);
		System.out.print("B6 (12.2 >= 12.7)");
		printBool(B6);
		
		boolean B7 = (12.2 <= 12.7);
		System.out.print("B7 (12.2 <= 12.7)");
		printBool(B7);
		
		System.out.println(3.3*2.4);
		boolean B8 = (3.3 * 2.4 == 7.92);
		System.out.print("B8 (3.3 * 2.4 == 7.92)");
		printBool(B8);
		
		// boolean B9 = (3 < 4 < 5);
		boolean B9 = (3 < 4 && 4 < 5);
		System.out.print("B9 (3 < 4 && 4 < 5)");
		printBool(B9);
		
		boolean B10 = (4 == 4);
		System.out.print("B10 (4 == 4)");
		printBool(B10);
		
		boolean B11 = (3 < 4 && 4 < 5);
		System.out.print("B11 (3 < 4 && 4 < 5)");
		printBool(B11);
		
		boolean B12 = (4 < 3 && 4 < 5);
		System.out.print("B12 (4 < 3 && 4 < 5)");
		printBool(B12);
		
		boolean B13 = (3 < 4 || 4 < 5);
		System.out.print("B13 (3 < 4 || 4 < 5)");
		printBool(B13);
		
		boolean B14 = (4 < 3 || 4 < 5);
		System.out.print("B14 (4 < 3 || 4 < 5)");
		printBool(B14);
		
		boolean B15 = (!(3 == 3));
		System.out.print("B15 (!(3 == 3))");
		printBool(B15);
		
		boolean B16 = (!( 3 == 4));
		System.out.print("B16 (! ( 3 == 4))");
		printBool(B16);
		
		boolean B17 = (!((3 == 4) && (4 == 4)));
		System.out.print("B17 (!((3 == 4) && (4 == 4)))");
		printBool(B17);
		
		boolean B18 = ((3!= 4) && (4 != 4));
		System.out.print("B18 ((3!= 4) && (4 != 4))");
		printBool(B18);
		
		boolean B19 = ((3 != 4) || (4 != 4));
		System.out.print("B19 ((3 != 4) || (4 != 4))");
		printBool(B19);
		
		boolean B20 = (false && false || true);
		System.out.print("B20 (false && false || true)");
		printBool(B20);
	}

	private static void printBool(boolean value) {
	// This procedure will print the value of the parameter sent in
		System.out.println(" is " + value + "\n");
		
	}
}
