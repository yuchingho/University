import java.util.Scanner;

public class Stats 	{
	
	private static final int MAX_SIZE = 100;

	public static void main(String[] args)	{
		
		int[] List = new int[MAX_SIZE];	
		int numItems;
		
		System.out.println("Press '0' to exit the entering of numbers");
		System.out.println("Please enter any numbers you want in your array:");
		
		numItems = fillUp(List);
		
		System.out.println("You put in " + numItems + " numbers.");
		System.out.println("The range of your " + numItems + " items is " + Range (List, numItems));
		System.out.println("The mean of your " + numItems + " items is " + Average (List, numItems)); 
	}
	
	private static int fillUp(int List[])	{
		Scanner scanner = new Scanner (System.in);
        int userInput = scanner.nextInt();
        int numItems = 0;
        
        while (true) {
        	if ((userInput == 0) || (numItems == MAX_SIZE))
        	break;
        	List[numItems++] = userInput;
        	userInput = scanner.nextInt();
        }	
        scanner.close();
        return numItems;
	}
	
	 private static double Average (int List[], int numItems)	{
		 int sum = 0;
		 for(int i = 0; i < numItems; i++)
		 sum += List[i];
		 double mean = (double) sum / (double) numItems;
		 return mean;
	 }

	 private static int Range(int List[], int numItems)	{
		 int max = List[0];
		 int min = List[0];
		 
		 for(int i = 0; i < numItems; i++)	{
			 if(max < List[i])
				 max = List[i];
			 if(min > List[i])
				 min = List[i];
		 }
		 int range = max - min;
		 return range;
	 }
}
