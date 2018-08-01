import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Database {
	
	public static Employee[] arrayEmployees = new Employee[1];
	public static int numberEmployees = 0;
	public static boolean menuExit = false;

	public static void main(String[] args)	{

		System.out.println("\tHello, Welcome to The Database\n");
		char menu;
		
		do	{
			System.out.println("  Here are the following commands:");
			System.out.println("| N | New employee");
			System.out.println("| C | Calculating paychecks");
			System.out.println("| R | Raise wages");
			System.out.println("| P | Print records");
			System.out.println("| D | Download data");
			System.out.println("| U | Upload data");
			System.out.println("| Q | Quit\n");
			System.out.println("Remember you can press 'Q' in any of the menus to quit!\n");
			
			System.out.print("  Enter a command: ");
			Scanner keyboard = new Scanner(System.in);
			String command = keyboard.next();
			menu = command.charAt(0);
						
			switch(menu) 	{
				case 'n': 
				case 'N': 
					System.out.println("\tAdding a new employee...");
					newEmployee();
					timer();
					break;
				case 'c': 
				case 'C': 
					System.out.println("\tCalculating paychecks...");
					paychecks();
					timer();
					break;
				case 'r':
				case 'R': 
					System.out.println("\tRaising employees' wages...");
					raise();
					timer();
					break;
				case 'p':
				case 'P': 
					System.out.println("\tPrinting employees...");
					print();
					timer();
					break;
				case 'd':
				case 'D': 
					System.out.println("\tDownloading employees onto a file...\n");
					download();
					timer();
					break;
				case 'u':
				case 'U': 
					System.out.println("\tUploading saved file's data...\n");
					upload();
					timer();
					break;
				case 'q':
				case 'Q': 
					System.out.println("\tThe Database will now exit.");
					System.exit(0);
					break;
				default:
					System.out.println("\tSorry menu command not recognised.");
					System.out.println("\tPlease try again.\n");
					timer();
					break;
			}
		}
		while (menu != '0');
	}
	
	public static void newEmployee()	{
		System.out.print("\t  Enter name: ");
		Scanner keyboard = new Scanner(System.in);
		String name = keyboard.nextLine();
		
		do	{
			System.out.println("\tHourly (h) or Annual (a) pay?");
			System.out.print("\t  Please enter: ");
			Scanner input = new Scanner (System.in);
			String selection = input.next();
			char payChar = selection.charAt(0);
			
			switch(payChar)	{
				case 'h':
				case 'H':
					System.out.println("\tHourly pay selected.");
					hourlyPay(name);
					menuExit = true;
					timer();
					break;
				case 'a':
				case 'A':
					System.out.println("\tAnnual pay selected.");
					annualPay(name);
					menuExit = true;
					timer();
					break;
				case 'q':
				case 'Q':
					System.out.println("\tMenu exit.");
					menuExit = true;
					timer();
					break;
				default:
					System.out.println("Sorry command not recognised. Please try again.");
					timer();
					break;
			}
		}
		while(menuExit == false);
	}

	public static void hourlyPay(String inputName)	{
		System.out.print("\t  Enter in hourly salary: ");
		double pay;
		do	{
			Scanner keyboard = new Scanner(System.in);
			pay = keyboard.nextDouble();
			
			arrayEmployees[numberEmployees] = new hourlyEmployee(inputName, pay);
			numberEmployees += 1;
			expandArrayList();
			
			System.out.println("\t" + inputName + " is earning $" + pay + "0 per hour\n");
			menuExit = true;
		}
		while (menuExit == false);
	}
	
	public static void annualPay(String inputName)	{
		System.out.print("\t  Enter in annual salary: ");
		double pay;
		do	{
			Scanner keyboard = new Scanner(System.in);
			pay = keyboard.nextDouble();
			
			arrayEmployees[numberEmployees] = new annualEmployee(inputName, pay);
			numberEmployees += 1;
			expandArrayList();
			
			System.out.println("\t" + inputName + " is earning $" + pay + "0 every year\n");
			menuExit = true;
		}
		while (menuExit = false);
	}

	public static void expandArrayList()	{
		if (numberEmployees == arrayEmployees.length)	{
			Employee[] arrayEmployee = new Employee[arrayEmployees.length * 2];
			for (int i = 0; i < arrayEmployees.length; i++)	{
				arrayEmployee[i] = arrayEmployees[i];
			}
			arrayEmployees = arrayEmployee;
		}
  	}
	
	public static void paychecks()	{
		try	{	
			for (int i = 0; i < numberEmployees; i++)	{
				System.out.print("\tHours worked by " + arrayEmployees[i].getName() + ": ");
				Scanner keyboard = new Scanner(System.in);
				int hours = keyboard.nextInt();
				System.out.println("\t  In " + hours + " hours, " + arrayEmployees[i].getName() + " earns $" + arrayEmployees[i].calculate(hours) + "\n");
				menuExit = true;
			}
		}
		catch (NumberFormatException localNumberFormatException)	{
			System.out.println("Hours must be entered as a integer.");
			System.out.println("Please try again.\n");
		}
	}
	
	public static void raise()	{
		try	{
			do	{
				System.out.print("\tEnter percentage increase: ");
				
				Scanner keyboard = new Scanner(System.in);
				int percentage = keyboard.nextInt();
				
				for (int i = 0; i < numberEmployees; i++)	{
					arrayEmployees[i].raise(percentage);
				}
				System.out.println("\tNew wages:");
				print();
				menuExit = true;
			}
			while(menuExit = false);
		}
		catch (NumberFormatException localNumberFormatException)	{
			System.out.println("Percentage must be entered as a integer.");
			System.out.println("Please try again.\n");
		}
	}
	
	public static void print()	{
		for (int i = 0; i < numberEmployees; i++)	{
			System.out.println(arrayEmployees[i]);
		}
	}
	
	public static void download()	{
		String fileName = "employee.dat";
		Employee[] a = arrayEmployees;
		try	{
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(a);
			out.close();
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void upload()	{
		String fileName = "employee.dat";
		Employee[] a = arrayEmployees;
		try	{
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			a = (Employee[]) in.readObject();
			in.close();
		}
		catch (IOException e)	{
			System.out.println(e.getMessage());
		}
		catch (ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		for (int i = 0; i < a.length; i++)
		System.out.println(a[i]);
	}
	
	public static void timer()	{
		try	{
			Thread.sleep(750);
		}
		catch (InterruptedException e)	{
		}
	}
}