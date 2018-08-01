import java.util.Scanner;

/**
 * A simple class to run the Magpie class.
 * 
 * @author Laurie White
 * @version April 2012
 * 
 * Yoshi Lab08
 */
public class MagpieRunner {

	/** Create a Magpie, give it user input, and print its replies. */
	public static void main(String[] args) {
		Magpie maggie = new Magpie();

		System.out.println(maggie.getGreeting());
		System.out.println("Please ask a question down below:");
		Scanner in = new Scanner(System.in);
		String statement = in.nextLine();
		
		while (!statement.equals("Bye") ||
			   !statement.equals("bye")) {
			System.out.println(maggie.getResponse(statement));
			statement = in.nextLine();
		}
		in.close();
	}

}
