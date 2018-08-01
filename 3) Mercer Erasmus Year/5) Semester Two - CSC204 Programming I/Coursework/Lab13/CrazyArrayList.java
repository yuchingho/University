// Yu-Ching
// Lab13

import java.util.ArrayList;

public class CrazyArrayList {
	
	public static void main(String[] args) {

		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(0, new Integer(3));
		a.add(0, new Integer(72));
		a.add(1, new Integer(0));
		a.add(1, new Integer(41));
		a.add(2, new Integer(-23));
		a.add(0, new Integer(64));
		System.out.println(a);

		a.set(4, a.get(2) + a.get(a.get(a.size() - 1)));
		System.out.println(a);
	}
}
