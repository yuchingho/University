public class Client	{

	public static void main(String[] args)	{
		
		A objectA = new A('x');
  		B objectB = new B('y', 'z');
  		
		System.out.print("\n1) printMe(objectA) = ");
		printMe(objectA);
		System.out.print("\n2) printMe(objectB) = ");
		printMe(objectB);
		/*
		objectA = new A('a');
		objectB = (B) objectA;
		System.out.print("\n3) printMe(objectB) = ");
		printMe(objectB); 
		 */
		objectA = new B('b', 'c');
		objectB = (B) objectA;
		System.out.print("\n4) printMe(objectB) = ");
		printMe(objectB);

		objectB = new B('d', 'e');
		objectA = (A) objectB;
		System.out.print("\n5) printMe(objectA) = ");
		printMe(objectA);

		objectA = new B('f', 'g');
		objectB = (B) objectA;
		System.out.print("\n6) printMe(objectB) = ");
		printMe(objectB);
		System.out.println();
	}
	
	private static void printMe(A someObject)	{
		someObject.write();
	}
}