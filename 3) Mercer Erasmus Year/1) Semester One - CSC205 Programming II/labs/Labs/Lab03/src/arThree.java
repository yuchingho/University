import java.util.*;

public class arThree	{

	public static void main(String args[])	{
		System.out.println("\nThis program will accept a user-specified number of integers\n" +
						"and compute their sum. Input can be terminated before the maximum\n" +
						"specified inputs are reached by entering a negative value.\n");
		System.out.print("Enter maximum number of numbers you want to enter: ");
		
		Scanner stdin = new Scanner(System.in);
		int item_count = stdin.nextInt();

		double in_array[] = new double[item_count];	// create the input array
		boolean stop = false;

		for(int i = 0; (i < in_array.length) && !stop; i++)	{
			System.out.print("\nEnter value " + (i+1) + "/" + item_count + ": ");
			double number = stdin.nextDouble();

			if (number < 0) // stop loop
				stop = true;
			else            // save input in our array
				in_array[i] = number;
		}
		stdin.close();
		// System.out.println("\n\nSum of values: " + Sum(in_array));
	}
	// place your Sum method here. 
}