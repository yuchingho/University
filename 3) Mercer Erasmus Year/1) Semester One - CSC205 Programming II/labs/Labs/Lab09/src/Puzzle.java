public class Puzzle	{
	
	public static void main(String[] args)	{
		
		System.out.println("puzzle(9) = " + puzzle(9));
	}

	private static int puzzle(int n)	{
		if((n % 3) == 2)
			return 1;
        else if((n % 3) == 1)
        	return(puzzle(n + 1) + 2);
        else
        	return(puzzle(n / 3) + 1);
	}
}