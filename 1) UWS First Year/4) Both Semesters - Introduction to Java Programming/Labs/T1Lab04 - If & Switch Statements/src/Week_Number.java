public class Week_Number {

	public static void main(String[] args) {
		TextIO.put("Press a number between 0 - 6 for a day of the week! ");
		int day = TextIO.getlnInt();

		switch (day) {
		case 0:
			TextIO.putln("Monday!");
			break;
		case 1:
			TextIO.putln("Tuesday!");
			break;
		case 2:
			TextIO.putln("Wednesday!");
			break;
		case 3:
			TextIO.putln("Thursday!");
			break;
		case 4:
			TextIO.putln("Friday!");
			break;
		case 5:
			TextIO.putln("Saturday!");
			break;
		case 6:
			TextIO.putln("Sunday!");
			break;
		default:
			TextIO.putln("Invalid number! Please try again!");
			break;
		}
	}
}