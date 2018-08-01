import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class T4 {

	public static void main(String[] args) {
		// make an array of 5 random Colors and 'print' them
		Color myColours[] = new Color[5];
		
		fillArrayWithColours(myColours);
		System.out.println("My Colours");
		printArrayOfColours(myColours);
		
		// Making another array
		Color otherColours[] = new Color[3];
		fillArrayWithColours(otherColours);
		System.out.println("\nOther Colour");
		printArrayOfColours(otherColours);
		// Merging two arrays into one
		Color mergedColours[] = merge2ColourArrays(myColours, otherColours);
		System.out.println("\nMerged Arrays: \"My Colours\" + \"Other Colour\"");
		printArrayOfColours(mergedColours);
		System.out.println("");

		// make an arrayList of random Color (let user decide how many)
		ArrayList<Color> alColour = new ArrayList<Color>();
		Scanner in = new Scanner(System.in);
		boolean done = false;
		Random r = new Random();
		while (!done) {
			alColour.add(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
			System.out.print("Another Colour? (0=no, 1=yes) ");
			int answer = in.nextInt();
			if (answer == 0) done = true;
		}
		System.out.println("\nArrayList Colours");
		printALofColours(alColour);
		
		System.out.println("\nMore Red Colours");
		printColoursMoreRed(alColour);
		
		// Creating another ArrayList
		System.out.println("");
		ArrayList<Color> alColourTwo = new ArrayList<Color>();
		done = false;
		while (!done) {
			alColourTwo.add(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
			System.out.print("Another Colour? (0 = no, 1 = yes)");
			int answer = in.nextInt();
			if (answer == 0)
				done = true;
		}
		in.close();
		System.out.println("\nSecond Array of Colour:");
		printALofColours(alColourTwo);
		
		// Add colours from alColourTwo to alColour
		for(Color c : alColourTwo) {
			alColour.add(c);
		}
		// Print out merged arrays
		System.out.println("\nMerged ArrayLists");
		printALofColours(alColour);
	}

	private static void fillArrayWithColours(Color myColours[]) {
		Random rand = new Random();
		for (int i = 0; i < myColours.length; i++) {
			myColours[i] = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
		}
	}
	
	private static void printArrayOfColours(Color myColours[]) {
		for (Color c: myColours) {
			System.out.println(c);
		}
	}
	
	private static Color[] merge2ColourArrays(Color[] myColours, Color[] otherColours) {
		Color mergedColours[] = new Color[myColours.length + otherColours.length];
		for(int i = 0; i< myColours.length; i++) 
			mergedColours[i] = myColours[i];
		for(int j = 0; j < otherColours.length; j++) {
			mergedColours[j + myColours.length] = otherColours[j];
		}
		return mergedColours;
	}
	
	
	private static void printALofColours(ArrayList<Color> alColour) {
		for (Color c: alColour) {
			System.out.println(c);
		}
	}
	
	private static void printColoursMoreRed(ArrayList<Color> alColour) {
		for(int i = 0; i < alColour.size(); i++) {
			if(alColour.get(i).getRed() > 127) {
				System.out.println(alColour.get(i));
			}
		}
	}
}
