public class SimTheDice {

	public static void main(String[] args) {
		Die die1 = new Die(6);
		Die die2 = new Die(6);	
		final int count = 1000;
		double theSums[] = new double[13];
		
		for(int i = 0; i < count; i++) {
			int value1 = die1.cast();
			int value2 = die2.cast();
			int sum = value1 + value2;
			
			theSums[sum]++;
		}
		
		System.out.println("You rolled 2 dice " + count + " times.");
		for(int i = 2; i <= 12; i++) {
			System.out.println("You rolled a " + i + ": " + ((int)theSums[i]) + " times\t" + ((theSums[i]/count)*100) + "%");
		}	
	}
}
