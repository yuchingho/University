public class Employee extends Person	{

	private double salary;
	private Date hireDate;

	public Employee (String lastName, String firstName, Date birthDate, double salary, Date hireDate)	{
		super(lastName, firstName, birthDate);
		this.salary = salary;
		this.hireDate = hireDate;
	}

	public double getSalary()	{
		return salary;
	}

	public void setSalary (double salary)	{
		this.salary = salary;
	}
	
	public Date getHireDate()	{
		return hireDate;
	}

	public String toString()	{
		String info = "Name = " + getLastName() + ", " + getFirstName() + "\nSalary = " + getSalary() 
						+ "\nBirth = " + getBirthDate() + "\nHired = " + getHireDate();
		return info;
	}
}