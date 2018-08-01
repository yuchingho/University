import java.util.Scanner;

public class ShortFactorial {
	
	public static void main(String[] args) {
		short value;

		Scanner in = new Scanner(System.in);
		System.out.print("Please enter a value: ");
		value = in.nextShort();
		
		short result = fact(value);
		System.out.println(value + "! = " + result);
		
		in.close();
	}
	
	private static short fact(short n) {
		short product = 1;
		for (short i = 2; i <= n; i++) {
			product *= i;
		}
		return product;
	}
}
