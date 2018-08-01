import java.util.*;
import java.util.Scanner;
import java.util.Stack;

public class JCFStack	{

	public static void main(String[] args) throws CloneNotSupportedException	{
		Stack stack1 = new Stack();
		stack1.push(new Integer(27));
		stack1.push(new Integer(0));
		stack1.push(new Integer(-3));
		stack1.push(new Integer(-18));
		stack1.push(new Integer(99));
        printStack(stack1); 
        
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        Object key = in.nextInt();
        int search = stack1.search(key);
        System.out.println("Key found at position " + search);
	}

	private static void printStack(Stack s) throws CloneNotSupportedException	{
        Stack tempStack = (Stack)(s.clone());
        if (! tempStack.isEmpty())
                System.out.println("*** Printing Out Stack:  ");

        while (! tempStack.isEmpty())	{
               System.out.println(tempStack.peek());
               tempStack.pop();
        }
	}
}