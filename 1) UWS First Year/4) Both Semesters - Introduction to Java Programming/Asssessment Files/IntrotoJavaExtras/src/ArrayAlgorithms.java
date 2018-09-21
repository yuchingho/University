import java.util.Arrays;

public class ArrayAlgorithms {
	
	public static void main(String[] args) {
		TextIO.putln("This application generates a 1D and a 2D array of random numbers and displays");
		TextIO.putln("the smallest value in the arrays.");
		TextIO.put("Please enter the length of the array to generate: ");
		
		final int N_ELEMENTS = TextIO.getlnInt();
		int[] array = new int[N_ELEMENTS];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random()*1000);
		}
		
		int smallest = findMinimum(array);
		TextIO.putln("The array is " + Arrays.toString(array));
		TextIO.putln("The smallest value in the array is " + smallest);
		
		int[][] matrix = new int[N_ELEMENTS][N_ELEMENTS];
		for (int row = 0; row < matrix.length; row++) {
			for (int column = 0; column < matrix[0].length; column++) {
				matrix[row][column] = (int)(Math.random()*1000);
			}			
		}
		
		smallest = findMinimum(matrix);
		TextIO.putln("The rows of the matrix contain: ");
		for (int row = 0; row < matrix.length; row++) {
			TextIO.putln(row + ": " + Arrays.toString(matrix[row]));
		}
		TextIO.putln("The smallest value in the array is " + smallest);
	}

// This is the algorithm to find the minimum element in an array of int given in the lecture slides by Mughal, Hamre, and Rasmussen
	public static int findMinimum(int[] array) {
		int minvalue = array[0];
		for (int counter = 1; counter < array.length; ++counter) {
			if (array[counter] < minvalue) {
				minvalue = array[counter];
			}
		}
		return minvalue;
	}

// This is the algorithm to find the minimum element in an 2d array of int given in the lecture slides by Mughal, Hamre, and Rasmussen
	public static int findMinimum(int[][] matrix) {
		int minvalue = matrix[0][0];
		for (int counter1 = 0; counter1 < matrix.length; ++counter1) {
			// Find the minimum value in matrix[counter1];
			for (int counter2 = 0; counter2 < matrix[counter1].length; ++counter2) {
				if (matrix[counter1][counter2] < minvalue) {
					minvalue = matrix[counter1][counter2];
				}
			}
		}
		return minvalue;
	}
}
