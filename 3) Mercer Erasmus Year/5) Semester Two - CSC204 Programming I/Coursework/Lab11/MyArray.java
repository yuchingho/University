// Yu-Ching Ho
// Lab 11

import java.util.Scanner;

public class MyArray {
	
	final static int NUM_ITEMS = 6;

	public static void main(String[] args) {
		int a[] = new int[NUM_ITEMS];

		fillUp(a);
		printOut(a);
		System.out.println("Array Sum:  " + sumUp(a));
		System.out.println("Number of Positive Values:  " + positiveCount(a));
	}

	private static void fillUp(int[] a) {
		Scanner keyboard = new Scanner(System.in);
		for(int i = 1; i < a.length; i++) {
			System.out.print("Enter value " + i + ": ");
			a[i] = keyboard.nextInt();
		}
		System.out.println(" ");
		keyboard.close();
	}

	private static void printOut(int[] a) {
		System.out.print("PrintOut of Array: ");
		for(int i = 1; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println("\n");
	}

	private static int sumUp(int[] a) {
		int sum = 0;
		for(int i = 1; i < a.length; i++) {
			sum = sum + a[i];
		}
		return sum;
	}

	private static int positiveCount(int[] a) {
		int count = 0;
		for(int i = 1; i < a.length; i++) {
			if(a[i] >= 0) {
				count++;
			}
		}
		return count;
	}
}
