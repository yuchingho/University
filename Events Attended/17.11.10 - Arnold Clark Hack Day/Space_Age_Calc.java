import java.text.DecimalFormat;
import java.util.Scanner;

/** 13/10/2017, Yu-Ching Ho, Java */

public class Space_Age_Calc {
	
public static int earthAge;
public static double earthYear = 365.25;
public static double mecuryOrbit = 0.2408467;
public static double venusOrbit = 0.61519726;
public static double marsOrbit = 1.8808158;
public static double jupiterOrbit = 11.862615;
public static double saturnOrbit = 29.447498;
public static double uranusOrbit = 84.016846;
public static double neptuneOrbit = 164.79132;
public static double plutoOrbit = 248.00;
	
static DecimalFormat single = new DecimalFormat("#0");
static DecimalFormat df = new DecimalFormat("#00.00000");
	
static Scanner reader = new Scanner(System.in);
static Scanner keyboard = new Scanner(System.in);

public static void main(String[] args) {
	boolean yearsCheck = false;
	reader = new Scanner(System.in);
	System.out.print("Please enter your age: ");
		if (reader.hasNextInt()) {
			earthAge = reader.nextInt();
			if (earthAge >= 1) {
				yearsCheck = true;
				System.out.println("\nAs an Earthling, you are " + earthAge + " years old.");
				Mecury();
				Venus();
				Mars();
				Jupiter();
				Saturn();
				Uranus();
				Neptune();
				Pluto();
			}
			else {
				System.out.println("You wouldn't have been born yet.\n");
			}
		}
		else {
			reader.nextLine();
			System.out.println("That's not a valid input!");
			yearsCheck = false;
		}
	}
	
// One orbit of Mecury = 0.2408467 of one Earth Year.
// That's ~25%, so for one Earth Year, Mecury orbits ~4 times.
// However many rotations of (planet) == one Earth Year
// 1/(planet rotation) x EarthAge
			
public static double Mecury() {
	double mercuryAge = (1/mecuryOrbit) * earthAge;
	System.out.println("You will be \"" + df.format(mercuryAge) + "\" years old on Mercury.");
	return mercuryAge;
}
	
public static double Venus() {
	double venusAge = (1/venusOrbit) * earthAge;
	System.out.println("You will be \"" + df.format(venusAge) + "\" years old on Venus.");
	return venusAge;
}
	
public static double Mars() {
	double marsAge = (1/marsOrbit) * earthAge;
	System.out.println("You will be \"" + df.format(marsAge) + "\" years old on Mars.");
	return marsAge;
}
	
public static double Jupiter() {
	double jupiterAge = (1/jupiterOrbit) * earthAge;
	System.out.println("You will be \"" + df.format(jupiterAge) + "\" years old on Jupiter.");
	return jupiterAge;
}
	
public static double Saturn() {
	double saturnAge = (1/saturnOrbit) * earthAge;
	System.out.println("You will be \"" + df.format(saturnAge) + "\" years old on Saturn.");
	return saturnAge;
}
	
public static double Uranus() {
	double uranusAge = (1/uranusOrbit) * earthAge;
	System.out.println("You will be \"" + df.format(uranusAge) + "\" years old on Uranus.");
	return uranusAge;
}
	
public static double Neptune() {
	double neptuneAge = (1/neptuneOrbit) * earthAge;
	System.out.println("You will be \"" + df.format(neptuneAge) + "\" years old on Neptune.");
	return neptuneAge;
}
	
public static double Pluto() {
	double plutoAge = (1/plutoOrbit) * earthAge;
	System.out.println("You will be \"" + df.format(plutoAge) + "\" years old on Pluto.\n");
	return plutoAge;
}
}