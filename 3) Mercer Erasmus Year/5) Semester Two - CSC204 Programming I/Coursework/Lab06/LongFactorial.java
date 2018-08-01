import java.util.Scanner;

public class LongFactorial {
	
	public static void main(String[] args) {
		long value;

		Scanner in = new Scanner(System.in);
		System.out.print("Please enter a value: ");
		value = in.nextInt();
		
		long result = fact(value);
		System.out.println(value + "! = " + result);
		
		in.close();
	}
	
	private static long fact(long n) {
		long product = 1;
		for (long i = 2; i <= n; i++) {
			product *= i;
		}
		return product;
	}
}
