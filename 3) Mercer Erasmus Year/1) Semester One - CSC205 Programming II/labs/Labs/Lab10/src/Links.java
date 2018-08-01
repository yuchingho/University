public class Links	{

	public static void main(String[] args)	{

		Node pos1 = null;
    	Node pos2 = null;
    	
    	pos1 = new Node(new Integer(13));
   		pos1.setNext(new Node(new Integer(15), null));
    	
   		pos2 = new Node(new Integer(11), null);
		pos2.setNext(pos1);
		
		printList(pos2);
	}

	private static void printList(Node head)	{
		if (head != null)	{
			System.out.println(head.getItem());
			printList(head.getNext());
		}
	}
	
	// Recursive method
	private static int count (Node head)	{
		int count = 0;
		if(head == null)	{
			return 0;
		}
		else
			return 1 + count(head.getNext());
	}
	
	// Iterative method
	private static Node findMax(Node head)	{
		Node large = head;
		Node current = head;
		
		while(current.getItem() != null)	{
			if(((Comparable)large.getItem()).compareTo((Comparable)current.getItem()) < 0);	{
				large = current;
			}
			if(current.getNext() != null)	{
				current = current.getNext();
			}
			else
				break;
		}
		return large;
	}
}