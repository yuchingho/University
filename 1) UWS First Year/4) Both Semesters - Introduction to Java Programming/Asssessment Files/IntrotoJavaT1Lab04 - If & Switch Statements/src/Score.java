public class Score {

	public static void main(String[] args) {
		TextIO.putln("Hello and welcome to the World Championship Football finals!");
		TextIO.putln("This final game will decide who the victor is: ");

		TextIO.put("Team ");
		String TeamA = TextIO.getln();
		TextIO.putln("vs");
		TextIO.put("Team ");
		String TeamB = TextIO.getln();

		TextIO.put("\nAnd, the end score! \nTeam " + TeamA + " has scored ");
		int scoreA = TextIO.getlnInt();
		TextIO.put("Team " + TeamB + " has scored ");
		int scoreB = TextIO.getlnInt();

		if (scoreA > scoreB) {
			TextIO.putln("Team " + TeamA + " won!");
		}
		else if (scoreA < scoreB) {
			TextIO.putln("Team " + TeamB + " won!");
		}
		else {
			TextIO.putln("It was a draw...");
		}
	}
}