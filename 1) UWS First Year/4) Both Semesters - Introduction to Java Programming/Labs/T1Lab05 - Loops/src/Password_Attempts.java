public class Password_Attempts {

	public static void main(String[] args) {
		int attempts = 0;
		boolean match = false;
		boolean match1 = false;

		do {
			TextIO.put("Username: ");
			String username = TextIO.getlnString();
			String correct = "brian";
			match = username.equals(correct);

			TextIO.put("Password: ");
			String password = TextIO.getlnString();
			String correct1 = "spam";
			match1 = password.equals(correct1);

			if (match & match1 == true) {
				attempts = 4;
			} 
			else {
				TextIO.putln("Incorrect \nPlease re-enter username or password.\n");
				attempts++;
			}
		} 
		while (attempts <= 2);
		
		if (attempts == 4) {
			TextIO.putln("Welcome");
		}
		else {
			TextIO.put("You have reached the maximum number of attempts.");
		}
	}
}