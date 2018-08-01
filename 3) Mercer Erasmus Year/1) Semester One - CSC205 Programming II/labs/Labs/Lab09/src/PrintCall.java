public class PrintCall	{

	public static void main(String[] args)	{
    	printMe(1);
	}

	public static void printMe(int n)	{
		if ((n != 1) && (n != 5))	{
			for (int i = 1; i <= n; i++)	
            System.out.print(' ');
            System.out.println("This was written by call number " + n + ".");
        }
		
	// Add an if-statement here with a recursive-statement in 
	// its body along with a for loop similar to the one above 
		if (n < 5)	{
			printMe(n + 1);
		}
		if (n != 5)	{
			for (int i = 1; i <= n; i++)	
			System.out.print(' ');
			System.out.println("This was ALSO written by call number " + n + ".");
		}
	}
}