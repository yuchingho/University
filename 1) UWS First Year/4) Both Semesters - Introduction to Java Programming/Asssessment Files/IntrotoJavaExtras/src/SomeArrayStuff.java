public class SomeArrayStuff {
	
	public static void main(String[] args) {
		TextIO.put("How many characters do you want to enter: ");
		int length = TextIO.getlnInt();
		char[] array = new char[length];
		TextIO.putln("Enter " + length + " characters and hit enter:");
		for (int i = 0; i < array.length; i++) {
			array[i] = TextIO.getChar();
		}
		TextIO.putln("\nThis array contains: ");
		for (int i=0; i < array.length; i++) {
			TextIO.putln("array[" + i + "] = " + array[i]);
		}
		// You can also loop through the array like this:
		TextIO.putln("\nOr using a For-Each loop:");
		int i = 0;
		for (char c : array) {
			TextIO.putln("array[" + i + "] = " + c);
			i++; // just to display what the index is
		}
	}
}		