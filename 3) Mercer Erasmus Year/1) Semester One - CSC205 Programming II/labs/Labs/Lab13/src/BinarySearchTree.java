//package BinaryTrees;
//import SearchKeys.KeyedItem;

// ADT binary search tree.
//  Assumption: A tree contains at most one item with a given search key at any time.

public class BinarySearchTree extends BinaryTreeBasis	{
	// inherits isEmpty(), makeEmpty(), getRootItem(), and
	// the use of the constructors from BinaryTreeBasis

	public BinarySearchTree() {
		// end default constructor
	}  

	public BinarySearchTree(KeyedItem rootItem) {
		super(rootItem);
	}  // end constructor

	public void insert(KeyedItem newItem) {
		root = insertItem(root, newItem);
	}  // end insert

	public KeyedItem retrieve(Comparable searchKey) {
		return retrieveItem(root, searchKey);
	}  // end retrieve

	public void delete(Comparable searchKey) throws TreeException {
		root = deleteItem(root, searchKey);
	}  // end delete

	public void delete(KeyedItem item) throws TreeException {
		root = deleteItem(root, item.getKey());
	}  // end delete

	// countNodes //////////////////////////
	public int countNodes()	{
		return countNodes(root);
	}
	
	protected int countNodes(TreeNode tNode)	{
		if (tNode == null)
			return 0;
		else
			return 1 + countNodes(tNode.getLeft()) + countNodes(tNode.getRight());
	}

	// inOrder /////////////////////////////
	public void inOrder()	{
		inOrder(root);
	}

	protected void inOrder(TreeNode tNode)	{
		if (tNode != null)	{
			inOrder(tNode.getLeft());
			System.out.print(tNode.getItem());
			inOrder(tNode.getRight());
		}
	}
	
	// preOrder ////////////////////////////
	public void preOrder()	{
		preOrder(root);
	}
	
	protected void preOrder(TreeNode tNode)	{
		if(tNode != null) {
			System.out.print(tNode.getItem());
			preOrder(tNode.getLeft());
			preOrder(tNode.getRight());
		}
	}
	
	// postOrder ///////////////////////////
	public void postOrder()	{
		postOrder(root);
	}
	
	protected void postOrder(TreeNode tNode) {
		if(tNode != null) {
			postOrder(tNode.getRight());  
			postOrder(tNode.getLeft()); 
			System.out.print(tNode.getItem()); 
		} 
	} 
	
	// Duplicate Check /////////////////////
	public boolean duplicateCheck(BinarySearchTree otherTree)	{
		return duplicateCheck(root, otherTree.getRoot()); 
	} 
	
	private boolean duplicateCheck(TreeNode t1,TreeNode t2)	{
		if(t1 == null && t2 == null) {
			return true; 
		}
		if(countNodes(t1)!= countNodes(t2))	{
			return false; 
		}
		if(((KeyedItem)t1.getItem()).getKey() != ((KeyedItem)t2.getItem()).getKey())	{
			return false; 	 
		}
		return (duplicateCheck(t1.getLeft(), t2.getLeft()) && duplicateCheck(t1.getRight(), t2.getRight())); 		
	}
	
	protected TreeNode insertItem(TreeNode tNode, KeyedItem newItem)	{
		TreeNode newSubtree;
		if (tNode == null) {
			// position of insertion found; insert after leaf
			// create a new node
			tNode = new TreeNode(newItem, null, null);
			return tNode;
		}  // end if
		KeyedItem nodeItem = (KeyedItem)tNode.getItem();
		// search for the insertion position
		if (newItem.getKey().compareTo(nodeItem.getKey()) < 0) {
			// search the left subtree
			newSubtree = insertItem(tNode.getLeft(), newItem);
			tNode.setLeft(newSubtree);
			return tNode;
		}
		else { // search the right subtree
			newSubtree = insertItem(tNode.getRight(), newItem);
			tNode.setRight(newSubtree);
			return tNode;
		}  // end if
	}  // end insertItem

	protected KeyedItem retrieveItem(TreeNode tNode, Comparable searchKey) {
		KeyedItem treeItem;
		if (tNode == null) {
			treeItem = null;
		}
		else {
			KeyedItem nodeItem = (KeyedItem)tNode.getItem();
			if (searchKey.compareTo(nodeItem.getKey()) == 0) {
				// item is in the root of some subtree
				treeItem = (KeyedItem)tNode.getItem();
			}
			else if (searchKey.compareTo(nodeItem.getKey()) < 0) {
				// search the left subtree
				treeItem = retrieveItem(tNode.getLeft(), searchKey);
			}
			else  { // search the right subtree
				treeItem = retrieveItem(tNode.getRight(), searchKey);
			}  // end if
		}  // end if
		return treeItem;
	}  // end retrieveItem

	protected TreeNode deleteItem(TreeNode tNode, Comparable searchKey) {
		// Calls: deleteNode.
		TreeNode newSubtree;
		if (tNode == null) {
			throw new TreeException("TreeException: Item not found");
		}
		else {
			KeyedItem nodeItem = (KeyedItem)tNode.getItem();
			if (searchKey.compareTo(nodeItem.getKey()) == 0) {
				// item is in the root of some subtree
				tNode = deleteNode(tNode);  // delete the item
			}
			// else search for the item
			else if (searchKey.compareTo(nodeItem.getKey()) < 0) {
				// search the left subtree
				newSubtree = deleteItem(tNode.getLeft(), searchKey);
				tNode.setLeft(newSubtree);
			}
			else { // search the right subtree
				newSubtree = deleteItem(tNode.getRight(), searchKey);
				tNode.setRight(newSubtree);
			}  // end if
		}  // end if
		return tNode;
	}  // end deleteItem

	protected TreeNode deleteNode(TreeNode tNode) {
		// Algorithm note: There are four cases to consider:
		//   1. The tNode is a leaf.
		//   2. The tNode has no left child.
		//   3. The tNode has no right child.
		//   4. The tNode has two children.
		// Calls: findLeftmost and deleteLeftmost
		KeyedItem replacementItem;

		// test for a leaf
		if ( (tNode.getLeft() == null) && (tNode.getRight() == null) ) {
			return null;
		}  // end if leaf

		// test for no left child
		else if (tNode.getLeft() == null) {
			return tNode.getRight();
		}  // end if no left child

		// test for no right child
		else if (tNode.getRight() == null) {
			return tNode.getLeft();
		}  // end if no right child

		// there are two children:
		// retrieve and delete the in-order successor
		else {
			replacementItem = findLeftmost(tNode.getRight());
			tNode.setItem(replacementItem);
			tNode.setRight(deleteLeftmost(tNode.getRight()));
			return tNode;
		}  // end if
	}  // end deleteNode

	protected KeyedItem findLeftmost(TreeNode tNode)  {
		if (tNode.getLeft() == null) {
			return (KeyedItem)tNode.getItem();
		}
		else {
			return findLeftmost(tNode.getLeft());
		}  // end if
	}  // end findLeftmost

	protected TreeNode deleteLeftmost(TreeNode tNode) {
		if (tNode.getLeft() == null) {
			return tNode.getRight();
		}
		else {
			tNode.setLeft(deleteLeftmost(tNode.getLeft()));
			return tNode;
		}  // end if
	}  // end deleteLeftmost

	public Object clone() throws CloneNotSupportedException	{
        if (this.root != null)	{
        	BinarySearchTree copy = new BinarySearchTree(new KeyedItem(""));
            copyTree (copy.root, this.root);
            return copy;
        }
        else
            return null;
	}

	protected void copyTree (TreeNode toPtr, TreeNode fromPtr) throws CloneNotSupportedException	{
		if (fromPtr == null)
			toPtr = null;
		else	{
			toPtr.setItem(fromPtr.getItem());
			if (fromPtr.getLeft() != null)	{ 
				toPtr.setLeft(new TreeNode(""));
				copyTree (toPtr.getLeft(), fromPtr.getLeft());
			}
			if (fromPtr.getRight() != null)	{
				toPtr.setRight(new TreeNode(""));
                copyTree (toPtr.getRight(), fromPtr.getRight());
			}
		}
	}
}  // end BinarySearchTree