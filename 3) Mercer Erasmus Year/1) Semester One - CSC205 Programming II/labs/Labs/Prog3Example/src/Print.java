public class Print	{
	
	public static void clearScreen()	{
		System.out.println("\u001b[H\u001b[2J");
	}

	public static void Line(int n, char ch)	{
		for (int i = 1; i <= n; i++)
		System.out.print(ch);
		System.out.println();
	}
}