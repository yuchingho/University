public class Account	{

	private double balance;	// Instance variables

  	public Account(double initialBalance)	{	// Constructors
    		balance = initialBalance;
  	}
  	
  	public Account()	{
    		balance = 0.0;
  	}

  	public void deposit(double amount)	{	// Instance methods
    		balance += amount;
  	}

  	public void withdraw(double amount)	{
    		balance -= amount;
  	}

  	public double getBalance()	{
    		return balance;
  	}

  	public void close()	{
    		balance = 0.0;
  	}
}