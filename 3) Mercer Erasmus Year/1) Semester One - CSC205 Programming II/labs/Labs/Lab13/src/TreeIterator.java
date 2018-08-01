/*public class TreeIterator implements java.util.Iterator {
	
	private BinaryTreeBasis binTree;
	private TreeNode currentNode;
	private QueueInterface queue; // from Chapter 7

	public TreeIterator(BinaryTreeBasis bTree) {
		binTree = bTree;
		currentNode = null; 
		// empty queue indicates no traversal type currently 
		// selected or end of current traversal has been reached
		queue = new QueueReferenceBased();
	}  // end constructor

	public boolean hasNext() {
		return !queue.isEmpty();
	}  // end hasNext

	public Object next() throws java.util.NoSuchElementException {
		try {
			currentNode = (TreeNode)queue.dequeue();
			return currentNode.getItem();
		}  // end try
		catch (QueueException e) {
			throw new java.util.NoSuchElementException();
		}  // end catch
	}  // end next

	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}  // end remove

	public void setPreorder() {
		queue.dequeueAll();
		preorder(binTree.root);
	}  // setPreOrder

	public void setInorder() {
		queue.dequeueAll();
		inorder(binTree.root);
	}  // end setInorder

	public void setPostorder() {
		queue.dequeueAll();
		postorder(binTree.root);
	}  // end setPostorder

	private void preorder(TreeNode treeNode) {
		if (treeNode != null) {  
			queue.enqueue(treeNode);
			preorder(treeNode.getLeft());
			preorder(treeNode.getRight());
		} // end if
	}  // end preorder

	private void inorder(TreeNode treeNode) {
		if (treeNode != null) {  
			inorder(treeNode.getLeft());
			queue.enqueue(treeNode);
			inorder(treeNode.getRight());
		} // end if
	}  // end inorder

	private void postorder(TreeNode treeNode) {
		if (treeNode != null) {  
			postorder(treeNode.getLeft());
			postorder(treeNode.getRight());
			queue.enqueue(treeNode);
		} // end if
	}  // end postorder
} */ // end TreeIterator