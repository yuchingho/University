public class factorial	{
	
	public static void main (String [] args)	{
		int value;					//  Check that there is a command line parameter
      
      if (args.length == 1)	{		//  Get the corresponding integer or use 7 if the string is badly formatted
    	  try	{					//  Convert the first parameter to an integer
    		  value = Integer.parseInt (args[0]);
    	  }
    	  catch (Exception e)	{	//  If anything goes wrong, use 7
				System.err.println ("The integer wasn't recognized, using 7");
				value = 7;
    	  }
      }
      else	{						//  There's not one parameter
    	  System.err.println ("You need one command line parameter, using 7.");
    	  value = 7;
      }
      int result = fact (value);
      System.out.println (value + "! = " + result);
	}

	private static int fact (int n)	{
		int product = 1;
		for (int i = 2; i <= n; i++)	{
			product *= i;
		}
		return product;
   }
}