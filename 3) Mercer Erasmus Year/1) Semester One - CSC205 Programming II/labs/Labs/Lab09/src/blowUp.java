public class blowUp {
	
	public static int counter = 1;
	
	public static void main(String[] args)	{
		
		if (counter != 0)	{
			System.out.println(counter);
			counter++;
			main(args);
		}
	}
}