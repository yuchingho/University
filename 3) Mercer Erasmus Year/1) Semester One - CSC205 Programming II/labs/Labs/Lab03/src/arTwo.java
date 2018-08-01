public class arTwo	{

	private static final int RM = 1;
    private static final int CM = 2;

    public static void main(String args[])	{
    	
    	final int ROWS = 7;
        final int COLS = 6;

        int ar2[][] = new int[ROWS][COLS];	// our 2D array
        int k = 10;	// initialise our array
        
        for (int r = 0; r < ROWS; r++)
        for (int c = 0; c < COLS; c++)
        	
        ar2[r][c] = k++;
        ar2[2][1] = ar2[2][1] - 1;	// changing some values in our array
        ar2[ROWS-1][COLS-1] = -99;

        DisplayArray(ar2);
        System.out.println();

        DisplayArray(ar2, RM);
        System.out.println();

        DisplayArray(ar2, CM);
        System.out.println();
    }

	private static void DisplayArray(int ar[][])	{	// displays the contents of a two dimensional array
		for (int r = 0; r < ar.length; r++)	{
			for (int c = 0; c < ar[r].length; c++)
			System.out.print(ar[r][c] + " ");
			System.out.println();
		}
	}

	private static void DisplayArray(int ar[][], int action)	{
		if (action == RM)	{
			for (int r = 0; r < ar.length; r++)	{
				for (int c = 0; c < ar[r].length; c++)
				System.out.print(ar[r][c] + " ");
				System.out.println();
			}
		}
		else if (action == CM)	{
			for (int c = 0; c < ar[c].length; c++)	{
				for (int r = 0; r < ar.length; r++)
			    System.out.print(ar[r][c] + " ");
				System.out.println();
			}
		}
	}
}