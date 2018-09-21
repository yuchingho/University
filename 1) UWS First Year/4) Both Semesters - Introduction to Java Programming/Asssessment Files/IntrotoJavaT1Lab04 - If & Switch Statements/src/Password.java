public class Password {

	public static void main(String[] args) {
		TextIO.put("Username: ");
		String username = TextIO.getlnString();
		String correct = "brian";
		boolean match = false;
		match = username.equals(correct);

		TextIO.put("Password: ");
		String password = TextIO.getlnString();
		String correct1 = "spam";
		boolean match1 = false;
		match1 = password.equals(correct1);

		if (match & match1 == true) {
			TextIO.putln("Welcome");
		}
		else {
			TextIO.putln("Incorrect");
		}
	}
}