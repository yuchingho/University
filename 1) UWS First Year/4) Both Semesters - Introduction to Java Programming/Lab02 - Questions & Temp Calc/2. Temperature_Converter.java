public class Temperature_Converter {

	public static void main(String[] args) {
		float tempF;
		TextIO.putln("What Fahrenheit temperature do you want converted into Celsius? ");
		TextIO.put("Please enter your temperature: ");
		tempF = TextIO.getlnFloat();
		TextIO.putln(tempF + "°F is " + convertFtoC(tempF) + "°C");
	}

	public static float convertFtoC(float F) {
		return ((F - 32) * 5) / 9;
	}
}