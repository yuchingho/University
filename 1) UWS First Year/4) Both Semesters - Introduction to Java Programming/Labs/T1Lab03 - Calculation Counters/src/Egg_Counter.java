public class Egg_Counter {

	public static void main(String[] args) {

		TextIO.put("How many eggs do you have? ");

		int eggs = TextIO.getlnInt();
		int gross = (eggs / 144);
		int dozen = ((eggs - (gross * 144)) / 12);
		int left = (eggs - ((gross * 144) + (dozen * 12)));

		TextIO.putln("You have " + gross + " gross, " + dozen + " dozen eggs worth with " + left + " eggs left over.");
		TextIO.put("(A gross is 144 eggs!)");
	}
}