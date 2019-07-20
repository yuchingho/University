import java.util.Scanner;

public class ClockTest	{

	public static void main(String[] args)	{
		Clock dvr = new Clock();
      	System.out.println("What time would you like to set your DVR to?\n");

      	Scanner stdin = new Scanner(System.in);
      	System.out.print("Hours = ");
      	int hour = stdin.nextInt();
   		System.out.print("Minutes = ");
   		int min = stdin.nextInt();
   		System.out.print("Seconds = ");
      	int sec = stdin.nextInt();
      	dvr.reset(hour,min,sec);
      	stdin.close();

      	System.out.println("\nThe time is now " + dvr + "\n\nAdvancing the time!\n");
      	dvr.advance();
     	System.out.println("The time is now " + dvr + "\n");
   	}
}