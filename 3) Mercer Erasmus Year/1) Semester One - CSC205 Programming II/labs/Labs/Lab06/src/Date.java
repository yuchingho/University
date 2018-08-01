import java.util.*;
//Represents a date, consisting of a year, a month (1-12) and a day (1-31)
public class Date 	{

	private int year;
 	private int month;  // 1-12
  	private int day;    // 1-31

  	public Date(int year, int month, int day) 	{
    		this.year = year;
    		this.month = month;
    		this.day = day;
  	}

  	public Date()	{
    		GregorianCalendar today = new GregorianCalendar();
    		year = today.get(Calendar.YEAR);
    		month = today.get(Calendar.MONTH) + 1;
    		day = today.get(Calendar.DATE);
  	}

  	public String toString()	{
    		return year + "-" + makeTwoDigits(month) + "-" +
        	makeTwoDigits(day);
  	}

  	public boolean equals(Date otherDate)	{
		return ( (this.year == otherDate.year) &&
		 	 (this.month == otherDate.month) &&
		 	 (this.day == otherDate.day));
  	}

  	private static String makeTwoDigits(int n)	{
    		if (n <= 9)
      		  return "0" + n;
    		else
      		  return "" + n;
  	}
}