import java.util.*;

public class JCFQueue	{
	
	public static Object newVal = new Character('*');

	public static void main(String[] args) throws CloneNotSupportedException	{
	
		LinkedList queue1 = new LinkedList();
		LinkedList queue2 = new LinkedList();
		Object key = new Character('$');

        queue1.addLast(new Character('b'));
        queue1.addLast(new Character('$'));
        queue1.addLast(new Character('E'));
        queue1.addLast(new Character('$'));
        
        queue2.addLast(new Character('b'));
        queue2.addLast(new Character('$'));
        queue2.addLast(new Character('E'));
        queue2.addLast(new Character('b'));
        
		System.out.println("Queue1: " + queue1);
		System.out.println("Queue2: " + queue2);
        System.out.println("Same list size = " + identicalCheck(queue1, queue2));
       
		findAndReplace(queue1, key, newVal);
		System.out.println("After findAndReplace:");
		printQueue(queue1);
	}

	private static boolean identicalCheck(LinkedList queue1, LinkedList queue2) throws CloneNotSupportedException	{
		if(queue1.size() == queue2.size())	{
			return true;
		}
		else	{
			return false;
		}
	}

	private static void findAndReplace(LinkedList queue1, Object key, Object newVal)	{
		if(queue1.equals(key))	{
			queue1.remove(key);
			queue1.add(newVal);
		}
	}

	private static void printQueue (LinkedList q) throws CloneNotSupportedException	{
		if(q.isEmpty())	{
			System.out.println("The queue is empty");
		}
		else	{
			q.remove(1);
			q.remove(2);
			q.add(1, newVal);
			q.add(3, newVal);
			System.out.println("New queue: " + q);
		}
	}
}