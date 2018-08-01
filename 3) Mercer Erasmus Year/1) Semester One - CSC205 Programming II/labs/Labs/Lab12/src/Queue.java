public class Queue implements QueueInterface {

	private final int MAX_QUEUE = 50; // maximum size of queue
	private Object[] items;
	private int front, back, count;
  
	public Queue()	{
		items = new Object[MAX_QUEUE];  
		front = 0;
		back = MAX_QUEUE-1; 
		count = 0;
	}  // end default constructor

	// queue operations:
	public boolean isEmpty()	{
		return count == 0;
	}  // end isEmpty

	public boolean isFull()	{
		return count == MAX_QUEUE;
	}  // end isFull
  
	public void enqueue(Object newItem)	{
		if (!isFull())	{
			back = (back+1) % (MAX_QUEUE);
			items[back] = newItem;
			++count;
		}
		else	{
			throw new QueueException("QueueException on enqueue: Queue full");
		}  // end if
	}  // end enqueue

	public Object dequeue() throws QueueException {
		if (!isEmpty())	{
			// queue is not empty; remove front
			Object queueFront = items[front];
			front = (front+1) % (MAX_QUEUE);
			--count;
			return queueFront;
    }
    else	{
      throw new QueueException("QueueException on dequeue: Queue empty");
    	}   // end if
	}  // end dequeue

	public void dequeueAll()	{
	    items = new Object[MAX_QUEUE];  
	    front = 0;
	    back = MAX_QUEUE-1;
	    count = 0;
	}  // end dequeueAll

	public Object front() throws QueueException	{
		if (!isEmpty())	{  
			// queue is not empty; retrieve front
			return items[front];
		}
    else	{
      throw new QueueException("Queue exception on front: Queue empty");
    	}  // end if
	}  // end front
   
	public Object clone() throws CloneNotSupportedException	{
		Queue temp = new Queue();
	  	Object[] items = new Object[MAX_QUEUE];
	  	
	  	temp.front = this.front;
	    temp.back = this.back;
	    temp.count = this.count;

	    for (int i = 0; i < this.items.length; i++)
		temp.items[i] = this.items[i];

	    return temp;
	}

	// letterCount instance method goes HERE
	public int letterCountTwo()	{
		count = 0;
		for(int i = 0; i < items.length; i++)	{
			if(items[i] != null)	{
				if(Character.isLetter((Character)items[i]))
					count++;
			}
		}
		return count;
	}
} // end Queue