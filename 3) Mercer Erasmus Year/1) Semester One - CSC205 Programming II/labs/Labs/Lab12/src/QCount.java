public class QCount	{

	public static void main(String[] args) throws CloneNotSupportedException	{
	
		Queue q = new Queue();

        q.enqueue(new Character('b'));
        q.enqueue(new Character('$'));
        q.enqueue(new Character('E'));
        q.enqueue(new Character('+'));
        q.enqueue(new Character('I'));
        q.enqueue(new Character('y'));
        q.enqueue(new Character('s'));
        q.enqueue(new Character('#'));
        
		System.out.println("Total letters in queue = " + letterCount(q));
		int count = letterCount(q);
		System.out.println(count);
		
		System.out.println("Total letters in queue = " + q.letterCountTwo());
	}

	// Character.isLetter, built in Java class
	private static int letterCount (Queue q) throws CloneNotSupportedException	{
		Queue temp = (Queue) q.clone();
		int count = 0;
		
		while(! temp.isEmpty())	{
			if(Character.isLetter((Character)temp.front()))
			count++;
			temp.dequeue();
		}
		return count;
	}
}