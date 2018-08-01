// This program computes how much an investment grows in a given number of years.

public class InvestmentRunner {
	
	public static void main(String[] args) {
		final double INITIAL_BALANCE = 10000;
		final double RATE = 15;
		// final int YEARS = 10;
		
		Investment invest = new Investment (INITIAL_BALANCE, RATE);
		invest.waitForBalance(20000);
		
		// Sees how many years it takes for it to reach a certain balance using the RATE
		System.out.printf("The balance after %d years is $%.2f\n", invest.getYears(), invest.getBalance());
		
		/*Investment invest = new Investment(INITIAL_BALANCE, RATE);
		invest.waitYears(YEARS);
		double balance = invest.getBalance();
		System.out.printf("The balance after %d years is $%.2f\n", YEARS, balance);*/
	}
}
