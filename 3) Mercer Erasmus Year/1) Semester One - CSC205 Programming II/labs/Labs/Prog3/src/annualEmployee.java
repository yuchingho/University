import java.io.Serializable;
import java.text.DecimalFormat;

public class annualEmployee extends Employee implements Serializable	{

	public annualEmployee(String inputName, double inputWage)	{
		super (inputName, inputWage); 
	}

	// Calculate wage earned in number of hours
	public String calculate(double inputWage)	{
		double pay = (super.getWage()/52/40) * inputWage;
		DecimalFormat df = new DecimalFormat("#.00");
		String calculate = "" + df.format(pay);
		return calculate;
	}
  
	public String toString()	{
		String wageInfo = "" + super.getWage();
		String nameInfo = super.getName();
		String bothInfo = "\t  Name: " + nameInfo + "\n\t  Wage: $" + wageInfo + "0 per year\n";
		return bothInfo;	
	}
}