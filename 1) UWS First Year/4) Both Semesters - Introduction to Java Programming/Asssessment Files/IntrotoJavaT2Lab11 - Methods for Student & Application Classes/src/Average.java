public class Average {

	public static void main(String[] args) {
		int[] values = new int[10];
		values[0] = 1;
		TextIO.putln("Type in 10 numbers:");
		int count = 0;
		
		for (int i = 0; i < values.length; i ++) {
			count ++;
			TextIO.put(count + ") ");	
			values[i] = TextIO.getInt();
		}
		
		int average = (values[0] + 
					   values[1] + 
					   values[2] + 
					   values[3] + 
					   values[4] + 
					   values[5] + 
					   values[6] + 
					   values[7] + 
					   values[8] + 
					   values[9]) / 10;
		TextIO.putln("\nThe average of these numbers is " + average + ".");
	}
}