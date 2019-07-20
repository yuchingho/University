import java.util.Scanner;

public class Histogram {
	
	public static void main(String[] args) {
		int N_ELEMENTS = 200;
		int[] histArray = new int[N_ELEMENTS];
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Type the weights (0-199). One weight per line. End with -1.");
		
		int weight = (int) Math.round(keyboard.nextDouble());
		while (weight >= 0 && weight < histArray.length) {
			histArray[weight]++;
			weight = (int) Math.round(keyboard.nextDouble());
		}
		// Find the index of the element with minimum weight
		int minIndex;
		for (minIndex = 0; (minIndex < histArray.length) && (histArray[minIndex] == 0);	++minIndex);
		// And index of the element with the maximum weight.
		int maxIndex;
		for (maxIndex = histArray.length -1; (maxIndex >= 0) && (histArray[maxIndex] == 0);	--maxIndex);
		// Print histogram
		System.out.println("Weight\t:\tFrequency");
		for (weight = minIndex; weight <= maxIndex; weight++) {
			System.out.print(weight + "\t:\t");
			for (int star = 1; star <= histArray[weight]; star++) {
				System.out.print("*");
			}
			System.out.println();
			keyboard.close();
		}
	}
}
