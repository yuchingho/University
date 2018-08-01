// Yu-Ching
// Lab13

import java.util.ArrayList;
import java.util.Random;

public class RandomArray {
	
	public static void main(String[] args) {
		ArrayList<Integer> randomArray = new ArrayList<Integer>();
		
		for(int row = 0; row < 5; row++) {
			for(int col = 0; col < 10; col++) {
				Random rand = new Random();
				randomArray.add(new Integer(rand.nextInt(41) + 10));
				System.out.printf("%5d ", randomArray.get(col + row));
			}
			System.out.println("");
		}
	}
}
