public class MyQueue	{

	public static void main(String[] args) throws CloneNotSupportedException	{
	
		char x;
		char y;
		Queue q = new Queue();

        q.enqueue(new Character('W'));
        x = ((Character) q.front()).charValue();
        q.enqueue(new Character('V'));
        q.enqueue(new Character(x));
        q.dequeue();

        y = ((Character) q.front()).charValue();
        q.dequeue();
        q.enqueue(new Character('T'));
        q.dequeue();
        q.enqueue(new Character(x));
        q.enqueue(new Character(y));

        x = ((Character) q.front()).charValue();
        q.enqueue(new Character(x));
        printQueue(q);
	}

	private static void printQueue (Queue q) throws CloneNotSupportedException	{
		if (q.isEmpty())	{      	
			System.out.println("Your Queue is currently empty!");
            return;
        }

		Queue tempQ  = (Queue) q.clone();

		while (! tempQ.isEmpty())	{
			System.out.println(tempQ.front());
            tempQ.dequeue();
        }
	}
}