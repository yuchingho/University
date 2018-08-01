import java.io.Serializable;

public abstract class Employee implements Serializable	{
	
	private String name;
	private double H_Wage;
  
	protected Employee(String paramString, double paramDouble)	{
		this.name = paramString;
		this.H_Wage = paramDouble;
	}
  
	public String getName()	{
		return this.name;
	}
  
	public double getWage()	{
		return this.H_Wage;
	}
  
	public void setName(String paramString)	{
		this.name = paramString;
	}
  
	public void setWage(double paramDouble)	{
		this.H_Wage = paramDouble;
	}
  
	public abstract String computePay(double paramDouble);
  
	public boolean equals(Employee paramEmployee)	{
		return this.name.equals(paramEmployee.getName());
	}
  
	public void raise(double paramDouble)	{
		this.H_Wage *= (paramDouble / 100.0D + 1.0D);
	}
}