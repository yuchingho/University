/**
* Reads a list of non-zero numbers from the user, then prints
* out the input numbers in the reverse of the order in which
* the were entered. There is no limit on the number of inputs.
* 
*/

import java.util.ArrayList;

public class ReverseWithArrayList {
	
	public static void main(String[] args) {
		ArrayList<Integer> list;
		list = new ArrayList<Integer>();
		TextIO.putln("Enter some non-zero integers. Enter 0 to end.");
		while (true) {
			TextIO.put("? ");
			int number = TextIO.getlnInt();
			if (number == 0)
				break;
			list.add(number);
		}
		TextIO.putln();
		TextIO.putln("Your numbers in reverse are:");
		for (int i = list.size() - 1; i >= 0; i--) {
			TextIO.putf("%10d%n", list.get(i));
		}
	}
}