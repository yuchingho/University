public class DecToBinary	{

	public static void main(String[] args)	{
		decToBinary(20);
		System.out.println();
	}

	private static void decToBinary (int num)	{
		if (num > 0)	{
			System.out.print(num % 2);
			decToBinary (num / 2);
			//System.out.print(num % 2);
		}
	}
}