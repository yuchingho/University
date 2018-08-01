import java.util.Scanner;

public class DoubleFactorial {
	
	public static void main(String[] args) {
		double value;

		Scanner in = new Scanner(System.in);
		System.out.print("Please enter a value: ");
		value = in.nextInt();
		
		double result = fact(value);
		System.out.println(value + "! = " + result);
		
		in.close();
	}
	
	private static double fact(double n) {
		double product = 1;
		for (double i = 2; i <= n; i++) {
			product *= i;
		}
		return product;
	}
}
