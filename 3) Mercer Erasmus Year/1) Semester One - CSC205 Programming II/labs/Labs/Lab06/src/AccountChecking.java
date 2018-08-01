public class AccountChecking extends Account	{
	
	private int checks;

	public AccountChecking (double Balance)	{
		super(Balance);
		checks = 0;
	}
	
	public int getChecksWritten()	{
		return checks;
	}

	public void writeCheck (double amount)	{
		withdraw (amount);
		checks++;
	}
}