import java.util.*;
import java.io.*;

public class myLibrary	{
	
	private static ArrayList<LibraryBook> books = new ArrayList<LibraryBook>(50);
	private static int numBooks;
	
	public static void main(String[] args) throws FileNotFoundException	{
		
		System.out.println("Hello, welcome to The Library");
		System.out.println("-----------------------------\n");
		
		try {
			System.out.print("Please enter the filename: ");
			Scanner scanner = new Scanner(System.in);
			String filename = scanner.nextLine();
			scanner = new Scanner(new File(filename));
			
			numBooks = inputBooks("library.dat", books);
			selectionSort(books);	// Selection Sort
			int menuChoice;
			
			do {
				System.out.println("\nHere is your menu -");
				System.out.println("1) Display all book records");
				System.out.println("2) Search for a book by its title");
				System.out.println("3) Exit Search Program\n");
				System.out.print("Please make your choice: ");

				Scanner keyboard = new Scanner(System.in);
				menuChoice = keyboard.nextInt();
				
				switch (menuChoice)	{
					case 1:
						for (int i = 0; i < books.size(); i++)	{
							printBook(books, numBooks);

							// When user hits enter, clearScreen is called
							String enter;	
							System.out.println("Please hit enter to cycle through the records.");
							System.out.println("Press 'm' to escape.");
							Scanner stdin = new Scanner(System.in);
							enter = stdin.nextLine();
							clearScreen();
							if (enter.toLowerCase().equals("m"))	{							
								break;
							}
							numBooks++;		
						}
					break;
					
					case 2:			
						String userInput;
						System.out.print("\nWhat book are you looking for: ");
						Scanner readUser = new Scanner(System.in);
						userInput = readUser.nextLine();
						String userTitle = userInput;
						int location = binarySearch(books, userTitle);
						
						boolean found = (location != -1);
						int numBooks = 0;
						
						if (found)
							printBook(books, location);
						else
							System.out.println("\nThat book is not here. Please try again.");
					break;
					
					case 3:
						System.exit(0);
					break;
					
					default:
						System.out.println("Menu choice not recognised. Please try again.");
					break;
				}	
			}
			while (menuChoice != '0');
		}		
		catch (IOException e)	{
			System.out.println(e.getMessage());
		}
	}
	
	// Selection Sort
	private static void selectionSort(ArrayList<LibraryBook> myBooks)	{
        int minIndex;
        int index;
        int j;
        
        LibraryBook temp; 

        for (index = 0; index < myBooks.size() - 1; index++)	{
        	minIndex = index;
        	for (j = minIndex+1; j < myBooks.size(); j++)
        	if (myBooks.get(j).compareTo(myBooks.get(minIndex)) < 0)
        	minIndex = j;
        	if (minIndex != index)	{
        		temp = myBooks.get(index);
        		myBooks.set(index, myBooks.get(minIndex));
        		myBooks.set(minIndex, temp);
           	}
        }
	}
	
	// Binary Search
	private static int binarySearch(ArrayList<LibraryBook> books, String Title)	{
		int first = 0;
		int last = books.size() - 1; 
		int	middle;
		int	location;
		boolean found = false;

		do	{
			middle = (first + last) / 2;
			if (Title.equals(books.get(middle).getTitle()))
				found = true;
			else if (Title.compareTo(books.get(middle).getTitle()) < 0)
				last = middle - 1;
			else
				first = middle + 1;
        }  
		
		while ((! found) && (first <= last));
        	location = middle;
        	return (found ? location: -1);
  	}
	
	public static int inputBooks(String inputFile, ArrayList<LibraryBook> books)	{
		numBooks = 0;
		try	{
			Scanner in = new Scanner (new File(inputFile));
			while(in.hasNext())	{
				Scanner lsc = new Scanner(in.nextLine()).useDelimiter(";");
				
				String title = lsc.next();
				String name = lsc.next();
				int copyright = lsc.nextInt();
				double price = lsc.nextDouble();
				String genre = lsc.next();
	
				books.add(new LibraryBook(title, name, copyright, price, genre));
			}
		}
		catch (IOException e)	{
			System.out.println(e.getMessage());
		}
		return numBooks;
	}

	// Print Book
	private static void printBook(ArrayList<LibraryBook> books, int location)	{
		System.out.println("       Record: #" + (location + 1));
		System.out.println("	Title: " + books.get(location).getTitle());	
		System.out.println("Author's Name: " + books.get(location).getAuthor());	
		System.out.println("    Copyright: " + books.get(location).getCopyright());	
		System.out.println("        Price: $" + books.get(location).getPrice());	
		System.out.println("        Genre: " + books.get(location).getGenre());	
	}
	
	// Clear Screen
    private static void clearScreen()	{
    	System.out.println("\u001b[H\u001b[2J");
    }
}