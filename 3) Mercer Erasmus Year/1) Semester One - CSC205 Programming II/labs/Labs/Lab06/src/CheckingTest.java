public class CheckingTest	{

	public static void main(String[] args)	{		

		AccountChecking personal = new AccountChecking(500.0);
		System.out.println("Beginning balance = $" + personal.getBalance());

		System.out.println("\nNow writing a check!");
		personal.writeCheck(127.99);
		System.out.println("Total checks = " + personal.getChecksWritten());
		System.out.println("Balance = $" + personal.getBalance());

		System.out.println("\nNow closing account!");
		personal.close();
	}
}
