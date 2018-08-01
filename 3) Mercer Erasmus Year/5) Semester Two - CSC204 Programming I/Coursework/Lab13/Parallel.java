// Yu-Ching
// Lab13

import java.util.ArrayList;

public class Parallel {
	
	public static void main(String[] args) {
		ArrayList<Character> a = new ArrayList<Character>();
		a.add(new Character('C'));
		a.add(new Character('$'));
		a.add(new Character('d'));
		a.add(new Character('Z'));

		ArrayList<Boolean> b = new ArrayList<Boolean>();
		b.add(false);
		b.add(false);
		b.add(true);
		b.add(false);

		ArrayList<String> c = new ArrayList<String>();
		c.add(0, "baseball");
		c.add(0, "basketball");
		c.add(0, "golf");
		c.add(0, "tennis");

		ArrayList<Fraction> d = new ArrayList<Fraction>();
		d.add(new Fraction(1, 2));
		d.add(new Fraction(2, 3));
		d.add(new Fraction(-2, 5));
		d.add(new Fraction(12, 25));

		for (int k = 3; k >= 0; k--) {
			System.out.print(a.get(k) + " " + b.get(k) + " " + d.get(k) + " ");
			if (c.get(k).compareTo("basketball") < 0)
				System.out.println("Before basketball");
			else if (c.get(k).compareTo("basketball") > 0)
				System.out.println("After basketball");
			else
				System.out.println("Hoops Time!");
		}
	}
}
