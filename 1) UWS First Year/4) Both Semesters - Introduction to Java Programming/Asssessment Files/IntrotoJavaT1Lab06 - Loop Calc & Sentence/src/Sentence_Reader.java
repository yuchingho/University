public class Sentence_Reader {

	public static void main(String[] args) {
		String sentence;
		char ch;
		boolean reader = true;
		
		TextIO.putln("This program will break up your sentence.");
		TextIO.putln("Enter \"no\" to end the program.");
		
		do {
			TextIO.put("Enter a line of text: ");
			sentence = TextIO.getln();
			
			for (int i = 0; i < sentence.length(); i++) {
				ch = sentence.charAt(i);
				
				if (sentence.equals("no")) {
					reader = false;
				}
				else if (Character.isLetter(ch)) {
					TextIO.put(ch);
					reader = false;
				}
				else {
					if (reader == false) {
						TextIO.put("\n");
						reader = true;
					}
				}
			}
			TextIO.put("\n");
		}
		while (!sentence.equals("no"));
		TextIO.put("Program terminated.");
	}
}