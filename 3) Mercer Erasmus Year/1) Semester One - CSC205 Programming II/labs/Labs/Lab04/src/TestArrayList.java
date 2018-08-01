import java.util.*;
public class TestArrayList	{

    public static void main(String[] args)	{

    	ArrayList<String> myArrayList = new ArrayList<String>(10);
		myArrayList.add("Python");
 		myArrayList.add("Java");
 		myArrayList.add("Java");
 		myArrayList.add("Java");
 		myArrayList.add("Java");
 		myArrayList.add("Java");
		myArrayList.add("C++");
 		myArrayList.add("C++");
 		myArrayList.add("C++");
 		myArrayList.add("C++");
		
		printArrayList(myArrayList);
		delete(myArrayList, "Python");
		printArrayList(myArrayList);

		int countTwo = count(myArrayList, "Java");
		System.out.println(countTwo);
    }

    private static void printArrayList(ArrayList<String> myArrayList)	{
		// Pre  : myArrayList has been initialised
		// Post : The elements of Vector v are printed to the screen on separate lines
    	for (int i = 0; myArrayList.size() > i; i++)	{
    		System.out.println(myArrayList.get(i));
		}
	}

	private static void delete(ArrayList<String> myArrayList, String key)	{
		// Pre  : myArrayList has been initialised
		// Post : All copies of key are removed from myArrayList
		for (int i = 0; myArrayList.size() > i; i++)	{
			myArrayList.remove(key);
		}
    }

    private static int count(ArrayList<String> myArrayList, String key)		{
		// Pre  : myArrayList has been initialised
		// Post : number of occurrences of key is computed and returned
		int count = 0;
		for (int i = 0; myArrayList.size() > i; i++)	{
			if (myArrayList.get(i).equals(key))	{
				count++;
			}
		}
		return count;
    }
}