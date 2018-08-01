import java.io.*;
import java.util.*;

public class magicSquare	{

	private static final int rowcol = 3;

	public static void main(String[] args) throws IOException	{

		int [][] squareMatrix = new int[rowcol][rowcol];
		getvalues(squareMatrix);
	
		for(int i = 0; i < 3; i++)	{
			int rowsum = 0;
			for(int j = 0; j < 3;j++) 
			rowsum += squareMatrix[i][j];
			System.out.println("Row sum:" + rowsum);
		}
	
		for(int i = 0; i < 3; i++) 	{
			int colsum = 0;
			for(int j = 0; j < 3; j++) 
			colsum += squareMatrix[j][i];
			System.out.println("Column sum:" + colsum);
		}

		int ldisum = 0;
		for(int i = 0; i < 3; i++) 	{
			ldisum += squareMatrix[i][i];
		}
		System.out.println("Left diagonal sum:" + ldisum);	

		int rdisum = 0;
		for(int i = 0; i < 3; i++) 	{  
			rdisum += squareMatrix[i][3 - i - 1];
		}
		System.out.println("Right diagonal sum:" + rdisum);

		if (rdisum == ldisum /*== colsum == rowsum*/)	{
			System.out.println("It's a square matrix");
		}
	}

	public  static int[][] getvalues (int[][] squareMatrix) throws IOException	{
		int num;
		Scanner f = new Scanner(new File("magic.dat"));
		for(int i = 0; i < 3; i++)	{
			for(int j = 0; j < 3; j++)	{
				num = f.nextInt();
				squareMatrix[i][j] = num;
            }
        }
		f.close();
		return squareMatrix;
	}
}