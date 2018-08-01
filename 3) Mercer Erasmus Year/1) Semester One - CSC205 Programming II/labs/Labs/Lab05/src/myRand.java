public class myRand	{

	public static void main(String[] args)	{
		
		int size = Integer.parseInt(args[0]);
		for(int i=0; i < size; i++)	{
			System.out.println((int) ((Math.random()*500) + 1));
		}
	}
}