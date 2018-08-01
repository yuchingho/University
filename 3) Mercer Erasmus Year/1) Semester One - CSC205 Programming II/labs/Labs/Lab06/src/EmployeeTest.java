public class EmployeeTest	{

	public static void main (String[] args)	{
		
		Employee person = new Employee("Brown", "Morris", new Date (1980, 3, 8), 40000, new Date (2002, 2, 5));
		System.out.println(person);
	}
}