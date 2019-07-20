public class Name_Char_Initials_Counter {

	public static void main(String[] args) {
		String lastName;
		String firstName;
		int age;
		int height;
		
		TextIO.put("Surname: ");
		lastName = TextIO.getln();
		TextIO.putln("You have " + lastName.length() + " characters in your surname.\n");
		
		TextIO.put("Forename: ");
		firstName = TextIO.getln();
		TextIO.putln("The first letter of your name is " + firstName.charAt(0) + ".\n");

		TextIO.put("Age: ");
		age = TextIO.getInt();		
		TextIO.put("Height(cm): ");
		height = TextIO.getInt();
		
		TextIO.putln("\nYour initials are " + firstName.toUpperCase().charAt(0) + "." + lastName.toUpperCase().charAt(0) 
		+ ".\nYou're " + age + " years old and " + height + "cm.");
	}
}
