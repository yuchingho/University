// Yu-Ching Ho
// Lab 11

import java.util.Random;

public class RandomArray {
	
	public static void main(String[] args) {
		int a[] = new int[50];
		

		for(int row = 0; row < 5; row++){
			for(int col = 0; col < 10; col++) {
				Random rand = new Random();
				a[row] = rand.nextInt(42)+9;
				System.out.printf("%5d ", a[row]);
			}
			System.out.println(" ");
		}
	}
}
