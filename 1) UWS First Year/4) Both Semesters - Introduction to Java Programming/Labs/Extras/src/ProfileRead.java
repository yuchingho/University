public class ProfileRead {
	
	public static void main(String[] args) {
		String name = "";
		String email = "";
		double salary = 0.0;
		String favColour = "";
		boolean error = false;
		
		TextIO.readFile("profile.txt");
		String word = TextIO.getWord();
		/////////////////////////////////
		if (word.equals("Name:")) {
			name = TextIO.getWord();
		}
		else {
			error = true;
		}
		TextIO.getln();
		word = TextIO.getWord();
		/////////////////////////////////
		if (word.equals("Email:")) {
			email = TextIO.getWord();
		} 
		else {
			error = true;
		}
		TextIO.getln();
		word = TextIO.getWord();
		/////////////////////////////////
		if (word.equals("Favorite")) {
			word = TextIO.getWord();
			favColour = TextIO.getWord();
		}
		else {
			error = true;
		}
		TextIO.getln();
		word = TextIO.getWord();
		/////////////////////////////////
		if (word.equals("Yearly")) {
			word = TextIO.getWord();
			salary = TextIO.getlnDouble();
		} 
		else {
			error = true;
		}
		TextIO.readStandardInput();
		/////////////////////////////////
		if (error) {
			TextIO.putln("Something went wrong!");
		} 
		else {
			TextIO.putln("Your name is " + name);
			TextIO.putln("Your email address is " + email);
			TextIO.putln("Your favourite colour is " + favColour);
			TextIO.putf("Your annual salary is %,1.2f\n", salary);
		}
	}
}