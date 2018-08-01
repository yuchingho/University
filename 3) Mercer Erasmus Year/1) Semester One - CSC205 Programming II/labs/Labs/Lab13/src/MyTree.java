public class MyTree {

	public static void main (String[] args)	{
		
		System.out.println("\nOriginal");
		
		BinarySearchTree t = new BinarySearchTree();
		t.insert(new KeyedItem("M "));
		t.insert(new KeyedItem("J "));
		t.insert(new KeyedItem("D "));
		t.insert(new KeyedItem("F "));
		t.insert(new KeyedItem("L "));
		t.insert(new KeyedItem("W "));
		t.insert(new KeyedItem("S "));
		t.insert(new KeyedItem("T "));
		t.insert(new KeyedItem("Z "));
		t.inOrder();
		
		System.out.println("\n\nPreOrder") ;
		t.preOrder(); 
		
		System.out.println("\n\nPostOrder"); 
		t.postOrder();  
		// Counting nodes
		int count = t.countNodes(); 
		System.out.println();
		System.out.print(count);
		System.out.println(" : Number of nodes in the tree"); 
		
		
		System.out.println("\nmyCopy - copied tree");
	    BinarySearchTree myCopy = new BinarySearchTree(); 
	    myCopy.insert(new KeyedItem("M ")); 
	    myCopy.insert(new KeyedItem("J ")); 
	    myCopy.insert(new KeyedItem("D ")); 
	    myCopy.insert(new KeyedItem("F ")); 
	    myCopy.insert(new KeyedItem("L ")); 
	    myCopy.insert(new KeyedItem("W ")); 
	    myCopy.insert(new KeyedItem("S ")); 
	    myCopy.insert(new KeyedItem("T ")); 
	    myCopy.insert(new KeyedItem("Z "));
	    myCopy.inOrder(); 
		
		System.out.println("\n\nPreOrder"); 
		myCopy.preOrder();
		System.out.println();
		if(t.duplicateCheck(myCopy))	{
			System.out.println("They are duplicates"); 
		} 
		else	{
			System.out.println("They are not duplicates"); 
		} 
		
		System.out.println("\nDelete 'M'");
		myCopy.delete("M "); 
		myCopy.preOrder(); 
		System.out.println();
	}
}