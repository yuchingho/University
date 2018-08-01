import java.util.*;

public class Bubble	{

	public static void main(String[] args)	{
		
		int size = Integer.parseInt(args[0]);
		int[] list = new int[size];

		buildList(list, size);
		bubbleSort (list);
	}

	private static void buildList(int[] list, int size)	{
		Scanner stdin = new Scanner(System.in);
		for (int i = 0; i < size; i++)
		list[i] = Integer.parseInt(stdin.next());
		
		stdin.close();
	}
	/*
	private static void printOut (int[] list)	{
		for (int i = 0;  i < list.length;  i++)
		System.out.print(list[i] + " ");
		System.out.println("\n");
	}
	*/
	public static void bubbleSort (int[] list)	{
		int temp;    
		/*int pass = 0;*/
  
		boolean anotherPassNeeded = true;
		int currentBottom = list.length;

		while (anotherPassNeeded)	{
			anotherPassNeeded = false;
  
			for (int curr = 0; curr < (currentBottom - 1); curr++)
				if (list[curr + 1] < list[curr])	{
					temp = list[curr];
        			list[curr] = list[curr + 1];
        			list[curr + 1] = temp;

        			anotherPassNeeded = true;
      			}
    		currentBottom = currentBottom - 1;
		}
	}
}