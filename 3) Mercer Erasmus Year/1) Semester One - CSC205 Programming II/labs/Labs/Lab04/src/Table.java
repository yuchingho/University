public class Table	{

	private static final int ROWS = 3;
  	private static final int COLS = 3;
	
	public static void main(String[] args)	{

		int[][] table = new int[ROWS][COLS];
		int[][] myTable = new int [3][3];
	
		initialize (table);	//print (table);
		scale (table, 5);
	
		initializemyTable (myTable);
		System.out.println("");
		printmyTable (myTable);
	}

	private static void initialize (int[][] table)	{	// Post : Each element of Table is initialised
		for (int i = 0; i < table.length; i++)		//        to the sum of its components
	   	for (int j = 0; j < table[i].length; j++)
		table[i][j] = i + j;
	}
	/*
	private static void print (int[][] table)	{	   // Post : The contents of Table are printed
		System.out.println("\t[0]\t[1]\t[2]");		   //        to the screen in tabular format
		
		for (int i = 0; i < table.length; i++)	{
			System.out.print("[" + i + "]");
			for (int j = 0; j < table[i].length; j++)
			System.out.print("\t" + table[i][j]);
			System.out.println();
		}
	}
	*/
	private static void scale (int[][] table, int factor)	{
		System.out.println("\t[0]\t[1]\t[2]");
        
		for (int i = 0; i < table.length; i++)	{
			System.out.print("[" + i + "]");
			for (int j = 0; j < table[i].length; j++)
			System.out.print("\t" + (table[i][j]) * 5/* Added a 5*/);
            System.out.println();
        }
	}

	private static void initializemyTable (int[][] myTable)	{
		for (int i = 0; i < myTable.length; i++)
        for (int j = 0; j < myTable[i].length; j++)
        myTable[i][j] = 1;
	}

	private static void printmyTable (int[][] myTable)	{
		System.out.println("\t[0]\t[1]\t[2]");

		for (int i = 0; i < myTable.length; i++)	{
			System.out.print("[" + i + "]");
			for (int j = 0; j < myTable[i].length;  j++)
			System.out.print("\t" + myTable[i][j]);
			System.out.println();
        }
	}
}