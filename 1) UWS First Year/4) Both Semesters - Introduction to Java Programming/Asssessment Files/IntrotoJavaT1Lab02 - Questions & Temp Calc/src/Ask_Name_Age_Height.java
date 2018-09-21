public class Ask_Name_Age_Height {

	public static void main(String[] args) {
		TextIO.put("What's your name? ");
		String name = TextIO.getlnString();

		TextIO.put("How old are you? ");
		int age = TextIO.getInt();

		TextIO.put("Hello " + name + ". ");
		TextIO.putln(age + " is a nice age.\n");
		
		TextIO.put("How tall are you in cm? ");
		int height = TextIO.getInt();
		TextIO.putln(height + "cm is a good height.");
	}
}