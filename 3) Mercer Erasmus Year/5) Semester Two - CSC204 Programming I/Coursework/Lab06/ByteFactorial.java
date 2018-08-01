import java.util.Scanner;

public class ByteFactorial {
	
	public static void main(String[] args) {
		byte value;

		Scanner in = new Scanner(System.in);
		System.out.print("Please enter a value: ");
		value = in.nextByte();
		
		byte result = fact(value);
		System.out.println(value + "! = " + result);
		
		in.close();
	}
	
	private static byte fact(byte n) {
		byte product = 1;
		for (byte i = 2; i <= n; i++) {
			product *= i;
		}
		return product;
	}
}
