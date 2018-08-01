import java.util.*;
import java.io.*;

public class Bingo	{

	private static final int Rows = 5;
	private static final int Cols = 5;

	public static void main (String[] args) throws IOException	{
	
		int[][] bingoMatrix = new int [Rows][Cols];
		
		fillCard (bingoMatrix);
		printCard (bingoMatrix);	
		checkForWin (bingoMatrix);	
	}	

		private static void checkForWin (int[][] bingoMatrix)	{
			int horizontalSum = 0;
			for (int i = 0; i < bingoMatrix.length; i++)	{ 
				for (int j = 0; j < bingoMatrix[0].length; j++)
				horizontalSum += bingoMatrix[i][j];
			}	
		
			int verticalSum = 0;
			for (int j = 0; j < bingoMatrix[0].length; j++) {
				for (int i = 0; i < bingoMatrix.length; i++)
				verticalSum += bingoMatrix[i][j];
			}
		
			int diagonalSum1 = 0;
			for (int i = 0; i < bingoMatrix.length; i++)
				diagonalSum1 += bingoMatrix[i][i];
		
			int diagonalSum2 = 0;
			for (int i = 0; i < bingoMatrix.length; i++)
				diagonalSum2 += bingoMatrix[i][bingoMatrix.length - i - 1];
		
			if (horizontalSum == 0)
				System.out.println("Yes, It's a win!");
			if (verticalSum == 0)
				System.out.println("Yes, It's a win!");
			if (diagonalSum1 == 0)
				System.out.println("Yes, It's a win!");
			if (diagonalSum2 == 0)
				System.out.println("Yes, It's a win!");
			else
				System.out.println("It's not a win sorry");
		}
		
		private static void printCard (int[][] bingoMatrix)	{	
			for (int i = 0; i < bingoMatrix.length; i++)	{
				for (int j = 0; j < bingoMatrix[i].length; j++)
				System.out.print("\t" + bingoMatrix[i][j]);
				System.out.println("\n");
			}
		}

		private static int[][] fillCard (int[][] bingoMatrix) throws IOException	{
			Scanner scanner = new Scanner (new File("bingo.in"));
			for (int i = 0; i < bingoMatrix.length; i++)	{
				for (int j = 0; j < bingoMatrix[i].length; j++)	{
					bingoMatrix[i][j] = scanner.nextInt();
				}
			}
			scanner.close();
			return bingoMatrix;
		}
}