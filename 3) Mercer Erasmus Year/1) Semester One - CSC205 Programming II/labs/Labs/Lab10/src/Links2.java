public class Links2 { 

	public static void main(String args[])	{
		
		Node head = null;
  		head = insert(head, new Integer(13));
  		head = insert(head, new Integer(-1));
  		head = insert(head, new Integer(0));
  		head = insert(head, new Integer(50));
		printList(head);
	}

	private static Node insert(Node head, Comparable newValue)	{
	
		Node prev, curr = head;

		for (prev = null, curr = head;
             curr != null && newValue.compareTo(curr.getItem()) > 0;
             prev = curr, curr = curr.getNext())	{
			// Empty body
			// Designed to take items in and sort in ascending order
		}
		
        Node newNode = new Node(newValue, curr);
        if (prev != null)	{
        	prev.setNext(newNode);
        	return head;
        }
        else
        	return newNode;
	}

	private static void printList(Node head)	{
		if (head != null)	{
			System.out.println(head.getItem());
			printList(head.getNext());
		}
	}
} 