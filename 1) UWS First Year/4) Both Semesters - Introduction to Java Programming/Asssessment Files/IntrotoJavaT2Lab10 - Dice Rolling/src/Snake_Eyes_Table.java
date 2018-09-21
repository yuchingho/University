public class Snake_Eyes_Table {

	public static final int experiments = 10000;
	
	public static void main(String[] args) {
		double average;
		TextIO.putln("Total On Dice     Average Number of Rolls");
		TextIO.putln("-------------     -----------------------");
		 	
		for (int dice = 2; dice <= 12; dice++) {
			average = getAverageRollCount(dice);
		 	TextIO.putf("%10d%22.4f\n", dice, average);
		}
	} 
	   
	public static double getAverageRollCount(int roll) {
		int rollCountThisExperiment;  
	    int rollTotal;  
	    double averageRollCount;  
	    rollTotal = 0;
	       
	    for (int i = 0; i < experiments; i++) {
	    	rollCountThisExperiment = rollFor(roll);
	    	rollTotal += rollCountThisExperiment;
	    }
	    averageRollCount = (((double)rollTotal) / experiments);
	    return averageRollCount;
	}
	   
	public static int rollFor(int N) {
		if (N < 2 || N > 12) {
	       throw new IllegalArgumentException("Impossible total for a pair of dice.");
		} 
	    int die1, die2, roll, rollCount = 0; 
	    do {
	    	die1 = (int)(Math.random()*6) + 1;
	    	die2 = (int)(Math.random()*6) + 1;
	    	roll = die1 + die2;
	    	rollCount++;
	    } 
	    while (roll != N);
	    return rollCount;
	}
}