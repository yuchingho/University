public class Reverse {

	public static void main(String[] args) {
		int [] values = new int[6];
		values[0] = 1;
		TextIO.putln("Type in 6 numbers:");
		int	count = 0;
			
		for (int i = 0; i < values.length; i ++) {
			count ++;
			TextIO.put(count + ") ");	
			values[i] = TextIO.getInt();
		}
			
		int[] reverse;
		reverse = new int[6];
			
		TextIO.putln("\nReverse order: ");
		for (int i = (values.length -1); i >= 0; i --) {
			reverse [reverse.length -1 -i] = values[i];
			TextIO.putln(values[i]);
		}
	}
}