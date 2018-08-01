public class mySums	{
	
	public static void main(String[] args)	{
		
		// Both the same code, written differently
		System.out.println(iterativeSum(4));
		System.out.println(recurSum(4));
	}
	
	// Iterative method
	public static double iterativeSum(int n)	{
		double result = 0;
		
		for (int i = 1; i <= n; i++)	{
			result += Math.pow(2, i) + 1;
		}
		return result;
	}
	
	// Recursive method
	public static double recurSum(int n)	{
		if(n < 1)
			return 0;
		else
			return Math.pow(2, n) + 1 + recurSum(n - 1);
	}
}