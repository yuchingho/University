public class Selection	{

	public static void main(String[] args)	{
  
		int[] list = {8, 15, 12, 34, 1};

		System.out.println("Before Sort: ");
		printOut(list);
		selectionSort(list);

		System.out.println("After Sort: ");
		printOut(list);
  	}

  	private static void printOut (int[] list)	{
  		for (int i = 0; i < list.length; i++)
  		System.out.print(list[i] + " ");
		System.out.println("\n");
  	}

	public static void selectionSort (int[] list)	{
		int minIndex;
		int index;
		int j;
		int temp;  
		int pass = 0;

		for (index = 0; index < list.length - 1; index++)	{
        
			minIndex = index;
			for (j = minIndex + 1; j < list.length;  j++)
			if (list[j] < list[minIndex])
			minIndex = j;
			
			if (minIndex != index)	{
				temp = list[index];
				list[index] = list[minIndex];
				list[minIndex] = temp;
			}
	        System.out.println(" ** After Outer Pass " + ++pass + " ** ");
    		printOut (list); 
        }
  	}
}