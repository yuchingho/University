import java.util.*;

public class Binary	{

  	public static void main(String[] args)	{
  
  		int[] list = {8, 15, 12, 34, 1};

  		Bubble.bubbleSort(list);
		printArray(list);
		
		System.out.print("Which key are you looking for? ");
		Scanner stdin = new Scanner(System.in);
        String userInput = stdin.next();
        int key = Integer.parseInt(userInput);

		int location = binarySearch (list, key);
        boolean found = (location != -1);
		System.out.println();

		if (found)
			System.out.println("Key " + key + " was found at index " + location);
		else
         	System.out.println("Sorry, a key of " + key + " was not found.");
		stdin.close();
   	} 
  	
  	private static void printArray (int[] A)	{
		System.out.print("Contents of our Array : ");
		for (int i = 0;  i < A.length;  i++)
		System.out.print(A[i] + " ");
		System.out.println("\n");
  	}
	 
	private static int binarySearch (int[] list, int key)	{
		int first = 0;
		int last = list.length - 1; 
		int	middle;
		int	location;
		int checks = 0;

		boolean found = false;

		do	{
			middle = (first + last) / 2;
			System.out.print("Check " + ++ checks + ": ");
			System.out.println("First = " + first + ", Last = " + last + ", Middle = " + middle);

			if (key == list[middle])
				found = true;
			else if (key < list[middle])
				last = middle - 1;
			else
				first = middle + 1;
        }  
		
		while ((! found) && (first <= last));
        location = middle;
        return (found ? location: -1);
  	}
}
