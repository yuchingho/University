public class Clock	{

	private int hour, min, sec;

	public Clock(int hourIn, int minIn, int secIn)	{
		hour = hourIn;
        min = minIn;
        sec = secIn;
	}

	public Clock()	{
		hour = 0;      
		min = 0;
		sec = 0;
	}

	public void reset()	{
		hour = 0;
		min = 0;
		sec = 0;
	}

    public void reset(int hourRes, int minRes, int secRes)	{
    	hour = hourRes;
    	min = minRes;
    	sec = secRes;
	}

	public void advance()	{
		sec++;
		if (sec >= 60)	{
			sec = 0;
			min++;
			if (min >= 60)	{
				min = 0;
				hour++;
				if (hour >= 24)
					hour = 0;
			}
		}
	}

	public String toString()	{
		String hourString, minString, secString;

		if (hour < 10)
			hourString = "0" + hour;
		else
			hourString = "" + hour;

		if (min < 10)
			minString = "0" + min;
		else
			minString = "" + min;

		if (sec < 10)
			secString = "0" + sec;
		else
			secString = "" + sec;
		return hourString + ":" + minString + ":" + secString;
       }
}