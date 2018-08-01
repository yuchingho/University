import java.io.*; 

public class Pez { 
		
	public static void main(String[]args) throws IOException, CloneNotSupportedException {
		Stack color = new Stack(); 
		addPez(color);  
		removeGreen(color); 
		printPez(color); 			
	}
	
	public static void addPez(Stack colours) throws IOException, CloneNotSupportedException { 
		colours.push(new String("yellow"));
		colours.push(new String("red"));
		colours.push(new String("green"));
		colours.push(new String("green"));
		colours.push(new String("yellow"));
		colours.push(new String("yellow"));
		colours.push(new String("red"));
		colours.push(new String("green"));
	} 
	
	public static void removeGreen(Stack colours) throws CloneNotSupportedException	{
		Stack removeColours = new Stack(); 
		
		while(! colours.isEmpty())	{
			String current = (String) colours.top(); 
			if (! current.equals("green")) { 
				removeColours.push(current); 
				colours.pop(); 
			} 
			else	{
				colours.pop(); 
			}
		} 
		
		while(! removeColours.isEmpty())	{
			colours.push(removeColours.top()); 
			removeColours.pop(); 
		} 
	} 
	
	public static void printPez(Stack colours) throws CloneNotSupportedException	{
		Stack tempStack = (Stack) colours.clone();
		if(! tempStack.isEmpty())
			System.out.print("");
		while(! tempStack.isEmpty())	{
			System.out.println((String)tempStack.top());
			tempStack.pop();
		}
	}
}