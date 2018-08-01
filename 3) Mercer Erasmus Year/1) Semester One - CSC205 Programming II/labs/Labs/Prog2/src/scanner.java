import java.util.*;
import java.io.*;

public class scanner	{
	
	public static void main (String[] args)		{
		
		ArrayList<LibraryBook> books = new ArrayList<LibraryBook> (50);
		System.out.println("Scanner Class");
		int numBooks = inputBooks("library.dat", books);
	}

	public static int inputBooks (String inputFile, ArrayList<LibraryBook> books)	{
		int numBooks = 0;
		try	{
			Scanner in = new Scanner (new File(inputFile));
			while(in.hasNext())	{
				
				Scanner lsc = new Scanner (in.nextLine()).useDelimiter(";");
				
				String title = lsc.next();
				String name = lsc.next();
				int copyright = lsc.nextInt();
				double price = lsc.nextDouble();
				String genre = lsc.next();
				books.add(new LibraryBook(title, name, copyright, price, genre));
				printRecord(books, numBooks);
				numBooks++;
			}
		}
		catch (IOException e)	{
			System.out.println(e.getMessage());
		}
		return numBooks;
	}

	public static void printRecord(ArrayList<LibraryBook> books, int location)	{
		System.out.println("	   Record: #" + (location + 1));
		System.out.println("	    Title: " + books.get(location).getTitle());	
		System.out.println("    Author's Name: " + books.get(location).getAuthor());	
		System.out.println("	Copyright: " + books.get(location).getCopyright());	
		System.out.println("	    Price: " + books.get(location).getPrice());	
		System.out.println("	    Genre: " + books.get(location).getGenre() + "\n");	
	}
}