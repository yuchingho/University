// This program will read a string and give its length

import java.util.*;

public class Prog2	{

	public static void main(String[] args)	{

		String data;
		System.out.print("Enter a string: ");
		Scanner stdin = new Scanner(System.in);
		data = stdin.nextLine();
		
		System.out.println("The string has " + data.length() + " characters.");
		stdin.close();
	}
}