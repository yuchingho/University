/**
* 
* Sorts the String elements from list[0] to list[size-1] in to ascending order.
* @param list, the list to be sorted
* @param size, the number of elements in the list that are to be sorted (list[0] to list[size-1])
* @throw NullPointerException if any of the String elements to be sorted or the list itself are null
* 
*/

public class BubbleSort {

	public static void bubbleSort(String list[ ], int size) {
		int pos;
		int comp;
		String temp;
		boolean exchangeFlag;
		do {
			exchangeFlag = false;
			for(pos = 0; pos < (size - 1); pos++) {
				comp = list[pos].compareTo(list[pos+1]);
				if (comp > 0) {
					temp = list[pos];
					list[pos] = list[pos+1];
					list[pos+1] = temp;
					exchangeFlag = true;
				}
			}
		}
		while (exchangeFlag);
	}
	
/**
* This method provides a test for the bubbleSort() method.
* It creates an array of 10 strings and calls bubbleSort() to sort the array. 
* The array is displayed to verify that it has been sorted.
*/
	public static void main(String[] args) {
     	String stringList[] = new String[10];
		// Set up data…
		stringList[0] = "Test";
		stringList[1] = "Something";	
		stringList[2] = "No Data";
		stringList[3] = "Another Test";		
		stringList[4] = "Apple";
		stringList[5] = "Keyboard";		
		stringList[6] = "Nonsense";
		stringList[7] = "Charming";
		stringList[8] = "Ingredients";
		stringList[9] = "Alphabetical";
	
		// Call the subroutine…
		bubbleSort( stringList, 10 );
	
		// check results… e.g. visually
		for(int index = 0; index < 10; index++)
		     System.out.println(stringList[index]);
	}
}