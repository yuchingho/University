import java.io.*;

public class MyStack	{

	public static void main(String[] args) throws IOException, CloneNotSupportedException	{
		
		Stack stack1 = new Stack();
		stack1.push(new Integer(27));
		stack1.push(new Integer(0));
		stack1.push(new Integer(-3));
		stack1.push(new Integer(-18));
		stack1.push(new Integer(99));
		
        stack1.pop();
       	stack1.pop();
       	
		int x = ((Integer)stack1.top()).intValue();
        stack1.push(new Integer(-12));
		stack1.push(new Integer(x));
        
		printStack(stack1);
	}

	private static void printStack(Stack s) throws CloneNotSupportedException	{
        Stack tempStack = (Stack)(s.clone());
        if(! tempStack.isEmpty())
        	System.out.println("*** Printing Out Stack:  ");
        while(! tempStack.isEmpty())	{
            System.out.println(tempStack.top());
            tempStack.pop();
        }
	}
}