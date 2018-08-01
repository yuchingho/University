import java.util.*;

public class ConvertName {
	public static void main(String arg[]) {
		System.out.println("(e.g. Henry \"Hammerin' Hank\" Aaron)");
		System.out.print("Please enter a string below: ");
		String S;
		Scanner stdin = new Scanner(System.in);
		S = stdin.nextLine();
		
		System.out.print("\nIndexOf the first quotation mark is ");
		int firstQ = S.indexOf('"');
		if(firstQ < 0) {
			System.out.println("not there.\n");
		}
		else {
			System.out.println("at Position" + firstQ + ".\n");
		}
		String firstName = S.substring(0, firstQ-1);
		System.out.println("The substring from Position0 to the first quotation mark giving: " + firstName + ".");
		System.out.println("Removing white-space from Position0 to the first quotation mark giving the first name: " + firstName.trim() + ".\n");
		
		System.out.print("IndexOf the second quotation mark is ");
		int secondQ = S.lastIndexOf('"');
		if(secondQ <= firstQ) {
			System.out.println("not there.");
			System.out.println("Therefore, there is no last name.");
		}
		else {
			System.out.println("at Position" + secondQ + ".");
			String lastName = S.substring(secondQ+1);
			System.out.println("The substring after the second quotation mark is: " + lastName + ".");
			System.out.println("Removing white-space after the second quotation mark giving the last name: " + lastName.trim() + ".\n");
			System.out.println("Without the quotation marks, " + firstName.trim() + " "+ lastName.trim() + " is reborn.");
		}
		stdin.close();
	}
}
