import java.io.Serializable;

public abstract class Employee implements Serializable {
// Just like Person.java in Lab6	
	private String name;
	private double wage;
	
	public Employee(String inputName, double inputWage)	{
		this.name = inputName;
		this.wage = inputWage;
	}
	
	public String getName()	{
		return this.name;
	}
	
	public double getWage()	{
		return this.wage;
	}
	
	public void setName(String inputName)	{
		this.name = inputName;
	}
	
	public void setWage(double inputWage)	{
		this.wage = inputWage;
	}

	// Extends into hourlyEmployee and annualEmployee
	public abstract String calculate(double inputWage);
	
	public void raise(double inputWage)	{
		this.wage *= (inputWage / 100.0D + 1.0D);
	}
	
}