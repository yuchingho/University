public class Queue implements QueueInterface	{
	
	private Node lastNode;
  
	public Queue()	{
		this.lastNode = null;
	}
  
	public boolean isEmpty()	{
		return this.lastNode == null;
	}
  
	public void dequeueAll()	{
		this.lastNode = null;
	}
  
	public void enqueue(Object paramObject)	{
		Node localNode = new Node(paramObject);
		if (isEmpty())	{
			localNode.setNext(localNode);
		}
		else	{
			localNode.setNext(this.lastNode.getNext());
			this.lastNode.setNext(localNode);
		}
		this.lastNode = localNode;
	}
  
	public Object dequeue() throws QueueException	{
		if (!isEmpty())	{
			Node localNode = this.lastNode.getNext();
			////////////////////////////////////////////////
			if (localNode == this.lastNode)	{
				this.lastNode = null;
			} 
			else {
				this.lastNode.setNext(localNode.getNext());
			////////////////////////////////////////////////
			// This is different. Able to use?
			}
			return localNode.getItem();
		}
		throw new QueueException("QueueException on dequeue: Queue empty");
	}
  
	public Object front() throws QueueException	{
		if (!isEmpty())	{
			Node localNode = this.lastNode.getNext();
			return localNode.getItem();
		}
		throw new QueueException("QueueException on front: Queue empty");
	}
  
	public Object clone() throws CloneNotSupportedException	{
		int i = 0;
		Queue localQueue = new Queue();
		Node localNode1 = this.lastNode;Object localObject = null;
		while ((i == 0) && (this.lastNode != null))	{
			Node localNode2 = new Node(localNode1.getItem());
			if (localObject == null) {
				localQueue.lastNode = localNode2;
			} 
			else {
				((Node)localObject).setNext(localNode2);
			}
			localObject = localNode2;
			localNode1 = localNode1.getNext();
			i = this.lastNode == localNode1 ? 1 : 0;
		}
		((Node)localObject).setNext(localQueue.lastNode);
		return localQueue;
	}
}