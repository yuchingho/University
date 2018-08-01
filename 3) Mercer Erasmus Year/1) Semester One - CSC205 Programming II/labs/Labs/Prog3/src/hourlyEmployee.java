import java.io.Serializable;
import java.text.DecimalFormat;

public class hourlyEmployee extends Employee implements Serializable	{

	public hourlyEmployee(String inputName, double inputWage)	{
		super (inputName, inputWage);
	}
	
	// Calculate wage earned in number of hours
	public String calculate(double inputWage)	{
		double pay;
		
		if (inputWage > 40)	{
			double overtimeHours = (inputWage - 40);
			double overtimePay = super.getWage() * 1.5;
			double normalPay = super.getWage() * 40;
			pay = normalPay + (overtimeHours * overtimePay);
		} 
		else	{
			pay = inputWage * super.getWage();
		}
		
		DecimalFormat df = new DecimalFormat("#.00");
		String calculate = "" + df.format(pay);
		return calculate;
	}
  
	public String toString()	{
		String wageInfo = "" + super.getWage();
		String nameInfo = super.getName();
		String bothInfo = "\t  Name: " + nameInfo + "\n\t  Wage: $" + wageInfo + "0 per hour\n";
		return bothInfo;	
	}
}