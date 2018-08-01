public class Queue implements QueueInterface	{

	private Node lastNode;
  
	public Queue()	{
		lastNode = null;   
	}  // end default constructor
  
	// queue operations:
	public boolean isEmpty()	{
        	return (lastNode == null);
	}  // end isEmpty

	public void dequeueAll() {
		lastNode = null;
	}  // end dequeueAll

	public void enqueue(Object newItem)	{
		// insert the new node
		Node node = new Node(newItem);
		if (isEmpty())	{
			// insertion into empty queue
			node.setNext(node);
		}
		else	{
			// insertion into nonempty queue
			node.setNext(lastNode.getNext());
			lastNode.setNext(node);
		}  // end if
		lastNode = node;
	}  // end enqueue

	public Object dequeue() throws QueueException	{
		if (!isEmpty())	{
			// queue is not empty; remove front
			Node node = lastNode.getNext();
			if(node == lastNode)	{
				this.lastNode = null;
			}
			else	{
				lastNode.setNext(node.getNext());
			}
			return node.getItem();
		}  //end if
		throw new QueueException("QueueException on dequeue: Queue empty");
	}  // end dequeue
	

	public Object front() throws QueueException {
		if (!isEmpty()) {
			Node firstNode = lastNode.getNext();
			return firstNode.getItem();
		}
		else	{
			throw new QueueException("QueueException on front: Queue empty");
		}
	}

	public Object clone() throws CloneNotSupportedException	{
		boolean copied = false;
        	Queue copy = new Queue();
        	Node curr = lastNode, prev = null;
        
        	while ((! copied) && (lastNode != null))	{
        		Node temp = new Node(curr.getItem());
        		if (prev == null)
        			copy.lastNode = temp;
        		else
        			prev.setNext(temp);
                	prev = temp;
                	curr = curr.getNext();
                	copied = (lastNode == curr);
        	}
        	prev.setNext(copy.lastNode);
        	return copy;
	}
} // end Queue