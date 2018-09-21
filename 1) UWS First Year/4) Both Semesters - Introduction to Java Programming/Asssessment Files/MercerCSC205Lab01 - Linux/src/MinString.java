import java.io.*;
import java.util.*;

public class MinString	{

	private static final int SIZE = 10;

    public static void main(String[] args)	{
    	String[] list = new String[SIZE];
    	int numItems;

    	numItems = Initialize (list);
    	System.out.println(FindMin (list, numItems));
    }

    private static int Initialize (String[] list)	{
    	String filename, stateInput;
        int i = 0, numItems = 0;
        try  {
        	System.out.println("Type 'data1.dat' or 'States.dat'");
        	System.out.print("Input File : ");
        	
            Scanner stdin = new Scanner(System.in);
            filename = stdin.nextLine();
            stdin = new Scanner(new File(filename));
            
            while ((stdin.hasNext()) && (i < list.length))	{
            	stateInput = stdin.nextLine();
            	System.out.println("S = " + stateInput);
            	list[i] = stateInput;
            	i++;
            }
            numItems = i;
            stdin.close();
        }
    	catch (IOException e)  {
    		System.out.println(e.getMessage());
    	}
    	return numItems;
	}

    private static String FindMin(String[] list, int numItems)	{
    	String REFERENCE = list[0];
    	for (int i = 1; i < numItems; i++)	{
    		if (list[i].compareTo(REFERENCE) < 0)
    			REFERENCE = list[i];
    	}
    	return REFERENCE;
	}
}