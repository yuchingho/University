public class Squares_Loop {

	public static void main(String[] args) {
		TextIO.put("What number do you want a sequence of '+ 10' and their squares? ");
		int a = TextIO.getlnInt();
		int b = (a + 11);
		int c = (a * a);

		for (; a < b; a = a + 1, c = a * a) {
			TextIO.putln("The number is: " + a + ", and its square is: " + c);
		}
	}
}