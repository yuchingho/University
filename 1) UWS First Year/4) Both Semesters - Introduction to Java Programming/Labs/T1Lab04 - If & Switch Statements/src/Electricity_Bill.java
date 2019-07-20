public class Electricity_Bill {

	public static void main(String[] args) {
		TextIO.putln("An electricity bill charges the first 100 units used at 26.7p per unit.");
		TextIO.putln("After the first 100 units, the units are now charged at 15.6p per unit.\n");
		TextIO.put("How many units of electricity do you use? ");

		double units = TextIO.getlnInt();
		if (units <= 100) {
			TextIO.putln("Your energy bill costs " + (int)(units * 26.7) + "p.");
		}
		else {
			double afterhundred = (units - 100) * 15.6;
			TextIO.putln("Your energy bill costs " + (int)(2670 + afterhundred) + "p.");
		}
	}
}