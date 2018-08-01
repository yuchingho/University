import java.util.Scanner;

public class GeoCalc {

	public static void main(String[] args) {
		
		double x1; // x coordinate for Point A
		double y1; // y coordinate for Point A
		double x2; // x coordinate for Point B
		double y2; // y coordinate for Point B
		
		Scanner keyboard = new Scanner(System.in);

		System.out.println("Yu-Ching's Geometric Calculator\n");
		
		System.out.print("Enter in the x coordinate for Point A: ");
		x1 = keyboard.nextDouble();
		
		System.out.print("Enter in the y coordinate for Point A: ");
		y1 = keyboard.nextDouble();
		
		System.out.print("Enter in the x coordinate for Point B: ");
		x2 = keyboard.nextDouble();
		
		System.out.print("Enter in the y coordinate for Point B: ");
		y2 = keyboard.nextDouble();
		
		// Calculations
		double root = Math.sqrt(((x2 - x1)*(x2 - x1)) + ((y2 - y1)*(y2 - y1)));
		double Rwidth = x2 - x1;
		double Rheight = y2 - y1;
		double Rperimeter = (2 * Rwidth) + (2 * Rheight);
		double Rarea = 	Rwidth * Rheight;
		
		double Cradius = root/2;
		double Cdiameter = root;
		double Ccircumference = 2 * (Math.PI * Cradius);
		double Carea = Math.PI * (Cradius * Cradius);
		
		System.out.println("\nThe distance between (" + x1 + ", " + y1 + ") and (" + x2 + ", " + y2 + ") is: " + root);
		System.out.println("\nThe rectangle with coordinates:");
		System.out.print("(" + x1 + ", " + y1 + ") ");
		System.out.print("(" + x1 + ", " + y2 + ") ");
		System.out.print("(" + x2 + ", " + y1 + ") ");
		System.out.print("(" + x2 + ", " + y2 + ")");
		
		System.out.println("\n\thas a width of: " + Rwidth);
		System.out.println("\thas a height of: " + Rheight);
		System.out.println("\thas a perimeter of: " + Rperimeter);
		System.out.println("\thas an area of: " + Rarea);
		
		System.out.println("\nThe circle with points A and B at ends of a diameter: ");
		System.out.println("\thas its centre at: (" + ((x2 + x1) / 2) + ", " + ((y2 + y1) / 2) + ")");
		System.out.println("\thas a radius of: " + Cradius);
		System.out.println("\thas a diameter of: " + Cdiameter);
		System.out.println("\thas a circumference of: " + Ccircumference);
		System.out.println("\thas an area of: " + Carea);
		
		keyboard.close();
	}
}