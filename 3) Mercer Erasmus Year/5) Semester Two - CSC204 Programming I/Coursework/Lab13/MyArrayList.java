// Yu-Ching
// Lab13

import java.util.ArrayList;
import java.util.Scanner;

public class MyArrayList {
	
	final static int NUM_ITEMS = 6;

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		fillUp(a);
		printOut(a);
		System.out.println("\nArrayList Sum:  " + sumUp(a));
		System.out.println("Num of Positive Values:  " + posCount(a));
	}

	private static void fillUp(ArrayList<Integer> a) {
		Scanner keyboard = new Scanner(System.in);
		for(int i = 0; i < 6; i++) {
			System.out.print("Enter a number: ");
			a.add(new Integer(keyboard.nextInt()));
		}
		keyboard.close();
	}
	
	private static void printOut(ArrayList<Integer> a) {
		System.out.println(a);
	}

	private static int sumUp(ArrayList<Integer> a) {
		int sum = 0;
		for(int i = 0; i < a.size(); i++) {
			sum = sum + a.get(i);
		}
		return sum;
	}

	private static int posCount(ArrayList<Integer> a) {
		int count = 0;
		for(int i = 0; i < a.size(); i++) {
			if(a.get(i) >= 0) {
				count++;
			}
		}
		return count;
	}
}
