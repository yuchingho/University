import java.io.*; 
import java.util.*;

public class Search implements Serializable{ 
	
	public static void main(String argv[]) throws IOException	{ 

		Scanner stdin = new Scanner(System.in);
		System.out.println("Please input 0 or more values at keyboard");
		Node head = buildList();

		System.out.println("Now printing list");
		printList(head);

		System.out.println("\nWhat key in list are you searching for? ");
		int key = stdin.nextInt();
		System.out.print("Your key was ");
		if(search(head, key))	{
			System.out.println("found.");
		}
		else	{
			System.out.println("not found.");
		}
	}

	private static void printList(Node head)	{
		if (head != null)	{
			System.out.print(head.getItem() + " ");
			printList(head.getNext());
		}
	}
	
	// In Linux, press "Ctrl-D" to end program so will continue on with code.
	// In Windows, press "Ctrl-Z"
	private static Node buildList() throws IOException	{
		Scanner in = new Scanner(System.in);
        Node head;
        if(in.hasNext())	{
        	head = new Node(new Integer(in.nextInt()), null);
        }
        else	{
        	head = new Node(null, null);
        	return head;
        }
        while(in.hasNext())
        head = insert(head,new Integer(in.nextInt()));
        return head;
	}

	private static Node insert(Node head, Comparable newValue)	{
		Node prev = head;
		Node curr = head;

		for (prev = null, curr = head;
             curr != null && newValue.compareTo(curr.getItem()) > 0;
             prev = curr, curr = curr.getNext())	{
			// Empty body
		}

        Node newNode = new Node(newValue, curr);
        if (prev != null)	{
        	prev.setNext(newNode);
        	return head;
        }
        else
        	return newNode;
	}

	private static boolean search(Node head, Comparable key)	{
		// PRE: Head points to the front of linked list. List may be empty or non-empty. Key is item search for.
		// POST: Returns true or false regarding whether key is found in list
		Node current = head;
		boolean found = false;
		
		if(head == null)	{
			found = false;
		}
		while(current.getNext() != null)	{
			current = current.getNext();
			if(key.compareTo(current.getItem()) == 0)	
			found = true;
		}
		return true;
	} 
} 