import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.io.PrintStream;

public class dighBingo	{

	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;
	public static final int DIAGONAL = 3;
	
	//private static final int ROWS = 5;
	//private static final int COLS = 5;
	//private static final int NUM_PICKS = 75;
	
	private static int pos = 1;
	private static int pickNum = 0;
	private static int[] picks = new int[75];
  
	public static void main(String[] paramArrayOfString)	{

		int[][] arrayOfInt = new int[5][5];
    
		fillCard(arrayOfInt);
		printCard(arrayOfInt);
		playGame(arrayOfInt);
		printCard(arrayOfInt);
	}
  
	private static void fillCard(int[][] paramArrayOfInt)	{
		try	{
			FileReader localFileReader = new FileReader("bingo.in");
			BufferedReader localBufferedReader = new BufferedReader(localFileReader);
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					paramArrayOfInt[i][j] = Integer.parseInt(localBufferedReader.readLine());
				}
			}
			localBufferedReader.close();
		}
		catch (IOException localIOException)	{
			System.out.println(localIOException.getMessage());
		}
	}
  
	private static boolean duplicate(int paramInt1, int[] paramArrayOfInt, int paramInt2)	{
		for (int i = 0; i < paramInt2; i++) {
			if (paramArrayOfInt[i] == paramInt1) {
				return true;
			}
		}
		return false;
	}
  
	private static void playGame(int[][] paramArrayOfInt)	{
		int j = 0;
    
		System.out.println("\tBINGO NUMBERS PICKED AT RANDOM FROM BIN :");
		System.out.print("\t");
		int i;
		do	{
			markCard(paramArrayOfInt);
     		i = checkForWin(paramArrayOfInt);
     		j++;
		} 
		while (i == 0);
		announceWin(i, j);
	}
  
	private static void markCard(int[][] paramArrayOfInt)	{
		int i = (int)(Math.random() * 74.0D) + 1;
		while (duplicate(i, picks, pickNum)) {
			i = (int)(Math.random() * 74.0D) + 1;
		}
		picks[pickNum] = i;
		for (int j = 0; j < 5; j++) {
			for (int k = 0; k < 5; k++) {
				if (paramArrayOfInt[j][k] == picks[pickNum]) {
					paramArrayOfInt[j][k] = 0;
				}
			}
		}
		System.out.print(picks[pickNum] + "\t");
		if (pos % 9 == 0)	{
			System.out.print("\n\t");
			pos = 1;
		}
		else	{
			pos += 1;
		}
		pickNum += 1;
	}
  
	private static void announceWin(int paramInt1, int paramInt2)	{
		System.out.print("\n\n\tYOU WIN WITH A ");
		switch (paramInt1)	{
			case 1: 
				System.out.print("HORIZONTAL BINGO");
				break;
			case 2: 
				System.out.print("VERTICAL BINGO");
				break;
			case 3: 
				System.out.print("DIAGONAL BINGO");
		}
		System.out.println(" AFTER " + paramInt2 + " PICKS!");
	}
  
	private static int checkForWin(int[][] paramArrayOfInt)	{
		int i = 0;
		int j;
		int k;
		int m;
		
		for (k = 0; k < 5; k++)	{
			j = 0;
			for (m = 0; m < 5; m++) {
				j += paramArrayOfInt[k][m];
			}
			if (j == 0) {
				return 1;
			}
		}
		
		for (k = 0; k < 5; k++)	{
			j = 0;
			for (m = 0; m < 5; m++) {
				j += paramArrayOfInt[m][k];
			}
			if (j == 0) {
				return 2;
			}
		}
		
		j = 0;
		for (k = 0; k < 5; k++) {
			j += paramArrayOfInt[k][(5 - k - 1)];
		}
		if (j == 0) {
			return 3;
		}
		
		j = 0;
		for (k = 0; k < 5; k++) {
			j += paramArrayOfInt[k][k];
		}
		if (j == 0) {
			return 3;
		}
		return i;
	}
  
	private static void printCard(int[][] paramArrayOfInt)	{
		System.out.println("\n\tYOUR BINGO CARD :");
		System.out.println("\t   B    I    N    G    O   ");
    
		System.out.println("\t--------------------------");
		for (int i = 0; i < 5; i++)	{
			System.out.print("\t|");
			for (int j = 0; j < 5; j++) {
				if (paramArrayOfInt[i][j] == 0) {
					System.out.print("  X |");
				} 
				else if (paramArrayOfInt[i][j] < 10) {
					System.out.print("  " + paramArrayOfInt[i][j] + " |");
				} 
				else {
					System.out.print(" " + paramArrayOfInt[i][j] + " |");
				}
			}
			System.out.println("\n\t--------------------------\n");
		}
		System.out.println();
	}
}