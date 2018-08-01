import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;

public class Personnel	{

	private static Employee[] employees = new Employee[1];
	private static int numEmployees = 0;
  
	public static void main(String[] paramArrayOfString)	{

		for (;;)	{
			switch (menu())	{
				case 'n': 
					newEmployee(); 
					break;
				case 'c': 
					paychecks(); 
					break;
				case 'r': 
					raise(); 
					break;
				case 'p': 
					print(); 
					break;
				case 'd': 
					download(); 
					break;
				case 'u': 
					upload(); 
					break;
				case 'q': 
					System.exit(-1);
				case 'e': 
				case 'f': 
				case 'g': 
				case 'h': 
				case 'i': 
				case 'j': 
				case 'k': 
				case 'l': 
				case 'm': 
				case 'o': 
				case 's': 
				case 't': 
				default: menuError();
			}
		}
	}
  
	public static char menu()	{
		Print.Line(34, '-');
		System.out.println("|Commands: n - New employee      |");
		System.out.println("|          c - Compute paychecks |");
		System.out.println("|          r - Raise wages       |");
		System.out.println("|          p - Print records     |");
		System.out.println("|          d - Download data     |");
		System.out.println(" Download data, quit, upload data");
		System.out.println("|          u - Upload data       |");
		System.out.println("|          q - Quit              |");
		Print.Line(34, '-');
		
		SimpleIO.prompt("Enter Command: ");
		char[] arrayOfChar = SimpleIO.readLine().trim().toLowerCase().toCharArray();
		if (arrayOfChar.length > 1)	{
			return '0';
		}
		return arrayOfChar[0];
	}
  
	public static void newEmployee()	{
		SimpleIO.prompt("Enter the name of the new employee: ");
		String str = SimpleIO.readLine().trim();
		SimpleIO.prompt("Hourly (h) or salaried (s): ");
		char[] arrayOfChar = SimpleIO.readLine().trim().toLowerCase().toCharArray();
		
		switch (arrayOfChar[0])	{
			case 'h': 
				newHourly(str); 
				break;
			case 's': 
				newSalaried(str); 
				break;
			default: System.out.println("Input was not h or s;  please try again.");
		}
	}
  
	public static void newHourly(String paramString)	{
		SimpleIO.prompt("Enter hourly wage:  ");
		try	{
			double d = Double.parseDouble(SimpleIO.readLine().trim());
			employees[numEmployees] = new HourlyEmployee(paramString, d);
			numEmployees += 1;
			checkSize();
		}
		catch (NumberFormatException localNumberFormatException)	{
			System.out.println("\nError: the employee's wage must be entered as a number.\n");
		}
	}
  
	public static void newSalaried(String paramString)	{
		SimpleIO.prompt("Enter annual salary:  ");
		try	{
			double d = Double.parseDouble(SimpleIO.readLine().trim());
			employees[numEmployees] = new SalariedEmployee(paramString, d);
			numEmployees += 1;
			checkSize();
		}
		catch (NumberFormatException localNumberFormatException)	{
			System.out.println("\nError: the employee's salary must be entered as a number.\n");
		}
	}
  
	public static void paychecks()	{
		try	{
			for (int i = 0; i < numEmployees; i++)	{
				SimpleIO.prompt("Enter number of hours worked by " + employees[i].getName() + ":  ");
				System.out.println("Pay:  $" + employees[i].computePay(Double.parseDouble(SimpleIO.readLine().trim())));
			}
		}
		catch (NumberFormatException localNumberFormatException)	{
			System.out.println("\nHours must be entered as a number; plase try again.\n");
		}
	}
	
	public static void raise()	{
		SimpleIO.prompt("Enter percentage increase:  ");
		try	{
			double d = Double.parseDouble(SimpleIO.readLine().trim());
			for (int i = 0; i < numEmployees; i++)	{
				employees[i].raise(d);
			}
			System.out.println("\nNew Wages\n---------");
			print();
		}
		catch (NumberFormatException localNumberFormatException)	{
			System.out.println("\nError in percent increase; Please try again.\n");
		}
	}
  
	public static void print()	{
		for (int i = 0; i < numEmployees; i++)	{
			System.out.println(employees[i]);
		}
	}
  
	public static void download()	{
		try	{
			System.out.println("Now downloading these records...");
			print();
			ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(new FileOutputStream("Employees.dat"));
			localObjectOutputStream.writeObject(employees);
			localObjectOutputStream.close();
		}
		catch (IOException localIOException)	{
			System.err.println("\nError writing to Employees.dat\n");
		}
	}
  
	public static void upload()	{
		try	{
			ObjectInputStream localObjectInputStream = new ObjectInputStream(new FileInputStream("Employees.dat"));
			Employee[] arrayOfEmployee = (Employee[])localObjectInputStream.readObject();
			compare(arrayOfEmployee);
		}
		catch (IOException localIOException)	{
			System.err.println("\nError reading from Employees.dat\n");
		}
		catch (ClassNotFoundException localClassNotFoundException)	{
			System.err.println("\nError reading from Employees.dat: \n" + localClassNotFoundException.getMessage());
		}
	}
  
	public static void compare(Employee[] paramArrayOfEmployee)	{
		int i = 0;
		try	{
			for (int j = 0; j < paramArrayOfEmployee.length; j++)	{
				int k = 0;
				for (int m = 0; m < numEmployees; m++)	{
					if (paramArrayOfEmployee[j].equals(employees[m])) {
						k = 1;
					}
				}
				if (k == 0)	{
					employees[numEmployees] = paramArrayOfEmployee[j];
					numEmployees += 1;i++;
					checkSize();
				}
			}
		}
		catch (NullPointerException localNullPointerException)	{
		}
		finally	{
			System.out.println(i + " new employees have been added.\n");
		}
	}
  
	public static void menuError()	{
		System.out.println("\nCommand was not recognized;  please try again.\n");
	}
  
	public static void checkSize()	{
		if (numEmployees == employees.length)	{
			Employee[] arrayOfEmployee = new Employee[employees.length * 2];
			for (int i = 0; i < employees.length; i++)	{
				arrayOfEmployee[i] = employees[i];
			}
			employees = arrayOfEmployee;
		}
  	}
}