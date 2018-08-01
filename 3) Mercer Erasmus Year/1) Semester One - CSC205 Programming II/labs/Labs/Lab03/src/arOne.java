public class arOne	{

	private static final int ASIZE = 10;     // our array size

	public static void main(String args[])	{
		
		double ar1[] = new double[ASIZE];	// create our array
		for(int i = 0; i < ASIZE; i++)	// initialise our array - note use of constant
		
		ar1[i] = i+11;

		for(int i = 0; i < ASIZE; i++)	// display array in main
		System.out.println(i + ": " + ar1[i]);

		ar1[ASIZE - 1] = 2001;	// which element gets set to 2001?   

		System.out.println("\nCalling Method to display contents of array:\n");
		DisplayArray(ar1);
		DisplayArray(Triple1(ar1));
		DisplayArray(ar1);
		Triple2(ar1);
		DisplayArray(ar1);
	}

	private static void DisplayArray(double ar[])	{	// print the contents of array ar  
		for (int i = 0; i < ar.length; i++)
		System.out.print(ar[i] + "  ");
		System.out.println();
	}

	private static double[] Triple1(double ar[])	{
		double temp[] = new double[ASIZE];
		for (int i = 0; i < ar.length; i++)
		temp[i] = ar[i] * 3;
		return temp;
	}

	private static void Triple2(double ar[])	{
		for (int i = 0; i < ar.length; i++)
			ar[i] = ar[i] * 3;
	}
}