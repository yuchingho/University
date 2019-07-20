import java.io.*;

public class Duck	{

	public static void main(String[] args)	{

		try	{
			FileReader fileIn = new FileReader("duck.dat");
			BufferedReader in = new BufferedReader(fileIn);
            int line = 1;

            	while (true)	{
            		String orig = in.readLine();
            		if (orig == null)
            			break;

            		String inLower = orig.toLowerCase();

            		if (duckCheck (orig) && countCheck(orig, inLower))
            			System.out.println("Line " + line + " is a duckling");

            		else if (duckCheck (inLower))
            			System.out.println("Line " + line + " is a duck");

            		else
            			System.out.println("Line " + line + " is not a duck");
            		line++;
            	}
            	in.close();
       		}

		catch (IOException e)	{
			System.out.println(e.getMessage());
		}
  	}

  	private static boolean duckCheck (String s)	{
  		return (s.indexOf("waddle") != -1 &&
                s.indexOf("swim") != -1 &&
                s.indexOf("quack") != -1);
  	}

  	private static boolean countCheck (String orig, String inLower)	{
  		return ((keyCount(orig,"swim") == keyCount(inLower, "swim")) &&
             	(keyCount(orig,"waddle") == keyCount(inLower, "waddle")) &&
             	(keyCount(orig,"quack") == keyCount(inLower, "quack")) );
  	}

  	private static int keyCount (String s, String key)	{
  		int count = 0;  
		int i = 0;

		while (i < s.length())
		if (s.indexOf(key, i) != -1)	{
			count++;  
			i = s.indexOf(key, i) + 1;
        }
        else
        	i++;
        return count;
  	}
}