import java.util.Scanner;

public class FloatFactorial {
	
	public static void main(String[] args) {
		float value;

		Scanner in = new Scanner(System.in);
		System.out.print("Please enter a value: ");
		value = in.nextInt();
		
		float result = fact(value);
		System.out.println(value + "! = " + result);
		
		in.close();
	}
	
	private static float fact(float n) {
		float product = 1;
		for (float i = 2; i <= n; i++) {
			product *= i;
		}
		return product;
	}
}
