import java.io.*;
import java.net.*;

public class IncUser
{  public static void main(String[] args )
   {  int i = 1;
      Socket client;
      InputStream input;
      OutputStream output;
      String Svalue;
      char c;

      try
      {  
	client = new Socket("intruder.cs.mercer.edu", 1253);
	input = client.getInputStream();
	output = client.getOutputStream();

	System.out.println("BASKETBALL SCORE\n");
        output.write((int) 'U');
        output.write((int) '\n');

int forever = 1;
while(forever==1)
{
        Svalue = "";
        while ((c=(char)input.read()) != '\n')
          Svalue = Svalue + String.valueOf(c);

	System.out.println(Svalue);
}

	client.close();
      }
      catch (IOException e)
      {  System.out.println(e);
      }
   }
}
