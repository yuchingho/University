// Yu-Ching
// Lab13

import java.util.ArrayList;

public class BegArrayList {
	
	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int k = 0; k < 10; k++)
			a.add(new Integer(k * k));
		// At position 2, set a to "-1"
		a.set(2 + 4 / 3, -1);
		System.out.println(a);
	}
}
