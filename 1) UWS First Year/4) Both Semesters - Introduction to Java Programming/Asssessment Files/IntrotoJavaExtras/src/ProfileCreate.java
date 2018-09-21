public class ProfileCreate {
	
	public static void main(String[] args) {
		String name; // The user’s name.
		String email; // The user’s email address.
		double salary; // the user’s yearly salary.
		String favColor; // The user’s favourite colour.
		
		TextIO.putln("Good Afternoon! This program will create your profile file.");
		TextIO.putln("If you will just answer a few simple questions.\n");
		// Gather responses from the user.
		TextIO.put("What is your name? ");
		name = TextIO.getln();
		TextIO.put("What is your email address? ");
		email = TextIO.getln();
		TextIO.put("What is your yearly income? ");
		salary = TextIO.getlnDouble();
		TextIO.put("What is your favourite colour? ");
		favColor = TextIO.getln();
		
		// Write the user’s information to the file named profile.txt.
		TextIO.writeFile("profile.txt"); // subsequent output goes to the file
		TextIO.putln("Name: " + name);
		TextIO.putln("Email: " + email);
		TextIO.putln("Favorite Color: " + favColor);
		TextIO.putf( "Yearly Income: %1.2f\n", salary);
		// The "/n" in the previous line is a carriage return, and the
		// comma in %,1.2f adds separators between groups of digits.
		// Print a final message to standard output.
		TextIO.writeStandardOutput();
		TextIO.putln("\nThank you. Your profile has been written to \"profile.txt\"");
		TextIO.putln("\"profile.txt\" has been put outside the src folder of this project.");
	}
}