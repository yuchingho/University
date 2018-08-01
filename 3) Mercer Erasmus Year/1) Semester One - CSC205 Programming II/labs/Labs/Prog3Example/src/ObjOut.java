import java.io.*;
// Need to run ObjOut first before being able to print ObjIn in console

public class ObjOut	{
	
	public static void main(String[] args)	{
		
		String fileName = "object.txt";
		int[] a = {1, 2, 3};
		try	{
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(a);
			out.close();
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
}