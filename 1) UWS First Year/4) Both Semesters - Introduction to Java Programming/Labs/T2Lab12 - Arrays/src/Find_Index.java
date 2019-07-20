public class Find_Index {

	public static void main(String[] args) {
		String[] theUsers;
		theUsers = new String [6];
		theUsers[0] = "Anne";
		theUsers[1] = "Tom";
		theUsers[2] = "Kate";
		theUsers[3] = "Harry";
		theUsers[4] = "Dick";
		TextIO.putln("'Anne', 'Tom', 'Dick', 'Kate', 'Harry'.");
		TextIO.putln("Type a name to find out where it is in the list: ");
		String target = TextIO.getlnString();
		find(theUsers, target);
	}

	public static void find(String[] theUsers, String target) {
		for (int i = 0; i < theUsers.length; i++) {
			// ".equals" is for comparing Strings.
			// "==" is for comparing primitive values (int, double).
			if (target.equals(theUsers[i])) {
				TextIO.putln("Found " + target + " at Index " + i + ".");
				break;
			}
		}
	}
} 