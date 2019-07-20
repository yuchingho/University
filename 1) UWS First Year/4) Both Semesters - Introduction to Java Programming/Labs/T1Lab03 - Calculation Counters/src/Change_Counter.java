public class Change_Counter {

	public static void main(String[] args) {
		
		double a, b, c, d, e, f, g, h;
		double total;
		
		TextIO.put("How many 1p's do you have:  ");
		a = TextIO.getlnInt();
		
		TextIO.put("How many 2p's do you have:  ");
		b = TextIO.getlnInt() * 2;
		
		TextIO.put("How many 5p's do you have:  ");
		c = TextIO.getlnInt() * 5;
		
		TextIO.put("How many 10p's do you have: ");
		d = TextIO.getlnInt() * 10;
		
		TextIO.put("How many 20p's do you have: ");
		e = TextIO.getlnInt() * 20;
		
		TextIO.put("How many 50p's do you have: ");
		f = TextIO.getlnInt() * 50;
		
		TextIO.put("How many £1's do you have:  ");
		g = TextIO.getlnInt() * 100;
		
		TextIO.put("How many £2's do you have:  ");
		h = TextIO.getlnInt() * 200;

		total = a + b + c + d + e + f + g + h;
		TextIO.putln("Your total change is £" + total / 100);
	}
}