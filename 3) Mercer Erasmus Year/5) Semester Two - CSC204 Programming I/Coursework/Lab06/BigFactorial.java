import java.math.BigInteger;
import java.util.Scanner;

public class BigFactorial {
	
	public static void main(String[] args) {
		int value;

		Scanner in = new Scanner(System.in);
		System.out.print("Please enter a value: ");
		value = in.nextInt();
		
		BigInteger result = fact(value);
		System.out.println(value + "! = " + result);
		
		in.close();
	}
	
	private static BigInteger fact(int value) {
		BigInteger product = BigInteger.ONE;
		for (int i = 2; i <= value; i++) {
			product = product.multiply (BigInteger.valueOf(i));
		}
		return product;
	}
}
