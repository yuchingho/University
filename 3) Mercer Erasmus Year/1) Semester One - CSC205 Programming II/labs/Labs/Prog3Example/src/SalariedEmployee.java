import java.io.Serializable;

public class SalariedEmployee extends Employee implements Serializable	{
	
	public SalariedEmployee(String paramString, double paramDouble)	{
		super(paramString, paramDouble / 52.0D / 40.0D);
	}
  
	public double getSalary()	{
		return super.getWage() * 40.0D * 52.0D;
	}
  
	public void setSalary(double paramDouble)	{
		super.setWage(paramDouble / 52.0D / 40.0D);
	}
  
	public String computePay(double paramDouble)	{
		double d = getSalary() / 52.0D;
    
		String str = "" + d;
		try	{
			str = str.substring(0, str.indexOf('.')) + str.substring(str.indexOf('.'), str.indexOf('.') + 3);
		}
		catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException)	{
			str = str.substring(0, str.indexOf('.')) + str.substring(str.indexOf('.'), str.indexOf('.') + 2) + 0;
		}
		return str;
	}
  
	public String toString()	{
		String str1 = "" + getSalary();
		try	{
			str1 = str1.substring(0, str1.indexOf('.')) + str1.substring(str1.indexOf('.'), str1.indexOf('.') + 3);
		}
		catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException)	{
			str1 = str1.substring(0, str1.indexOf('.')) + str1.substring(str1.indexOf('.'), str1.indexOf('.') + 2) + 0;
		}
		
		int i = 40 - super.getName().length() - str1.length() - "$/year".length();
		String str2 = super.getName();
		
		for (int j = 0; j <= i; j++)	{
			str2 = str2 + " ";
		}
		return str2 + "$" + str1 + "/year";
	}
}