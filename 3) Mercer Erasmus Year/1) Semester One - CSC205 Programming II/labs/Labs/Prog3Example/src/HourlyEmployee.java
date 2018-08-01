import java.io.Serializable;

public class HourlyEmployee extends Employee implements Serializable	{
	
	public HourlyEmployee(String paramString, double paramDouble)	{
		super(paramString, paramDouble);
	}
  
	public String computePay(double paramDouble)	{
		double d;
		if (paramDouble > 40.0D)	{
			d = 40.0D * super.getWage() + (paramDouble - 40.0D) * super.getWage() * 1.5D;
		} 
		else	{
			d = paramDouble * super.getWage();
		}
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
		String str1 = "" + super.getWage();
		try	{
			str1 = str1.substring(0, str1.indexOf('.')) + str1.substring(str1.indexOf('.'), str1.indexOf('.') + 3);
		}
		catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException)	{
			str1 = str1.substring(0, str1.indexOf('.')) + str1.substring(str1.indexOf('.'), str1.indexOf('.') + 2) + 0;
		}
		
		int i = 40 - super.getName().length() - str1.length() - "$/hour".length();
		String str2 = super.getName();
		
		for (int j = 0; j <= i; j++)	{
			str2 = str2 + " ";
		}
		return str2 + "$" + str1 + "/hour";
	}
}