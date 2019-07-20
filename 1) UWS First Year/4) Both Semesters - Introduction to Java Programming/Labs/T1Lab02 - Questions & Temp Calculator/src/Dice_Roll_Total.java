public class Dice_Roll_Total {

	public static void main(String[] args) {
		int dice1;
		int dice2;

		dice1 = (int)((Math.random() * 6) + 1);
		TextIO.putln("The first die comes up as " + dice1 + ". ");
		dice2 = (int)((Math.random() * 6) + 1);
		TextIO.putln("The second die comes up as " + dice2 + ".");
		TextIO.putln("Your total roll is " + (dice1 + dice2) + ".");
	}
}