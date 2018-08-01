public class DateTest	{

	public static void main(String[] args)	{
	
		Date today = new Date();
		Date holiday = new Date(2013, 9, 22);
		Date lastSunday = new Date(2013, 9, 22);

		System.out.println("Today = " + today);
		System.out.println("Last Sunday = " + lastSunday);
		System.out.println("Holiday = " + holiday);

// Comparing the address of these objects rather than their contents, therefore unequal
		if (lastSunday.equals(holiday))	
			System.out.println("They are Equal!");
		else
			System.out.println("They are NOT Equal!");
// Giving lastSunday the same address in memory as holiday, therefore equal
		lastSunday = holiday;

		if (lastSunday == holiday)
			System.out.println("They are Equal!");
		else
			System.out.println("They are NOT Equal!");
	}
}