//package BinaryTrees;

public abstract class BinaryTreeBasis {
	protected TreeNode root;

	public BinaryTreeBasis() {
		root = null;
	}  // end default constructor

	public BinaryTreeBasis(Object rootItem) {
		root = new TreeNode(rootItem, null, null);
	}  // end constructor

	public boolean isEmpty() {
		// Returns true if the tree is empty, else returns false.
		return root == null;
	}  // end isEmpty

	public void makeEmpty() {
		// Removes all nodes from the tree.
		root = null;
	}  // end makeEmpty

	public TreeNode getRoot()	{
		return root;
	}

	public Object getRootItem() throws TreeException {
		if (root == null) {
			throw new TreeException("TreeException: Empty tree");
		}
		else {
			return root.getItem();
		}  // end if
	}  // end getRootItem

}  // end BinaryTreeBasis