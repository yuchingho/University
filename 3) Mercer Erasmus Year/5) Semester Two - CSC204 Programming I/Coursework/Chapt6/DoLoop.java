import java.util.Scanner;
// This program shows how you can prompt for input that fulfills a certain condition. 
// Try entering negative values, such as -2 or -0.5, to see how they are rejected.

public class DoLoop {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		double value;
		do {
			System.out.print("Enter a number greater or equal to zero: ");
			value = in.nextDouble();
		} while (value < 0);
		double root = Math.sqrt(value);
		System.out.println("The square root of the number is " + root);
		in.close();
	}
}
