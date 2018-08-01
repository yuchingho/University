// Lab09
// Yoshi

import java.awt.Color;
import java.util.Scanner;

public class PlayWithPictures {
	private static Picture thePicture = new Picture();
	private static int width; 
	private static int height;

	public static void main(String[] args) {
		char choice;
		Scanner in = new Scanner(System.in);

		System.out.println("Please select a picture to transform:\n");

		thePicture.pick();
		width = thePicture.getWidth();
		height = thePicture.getHeight();
		boolean done = false;

		while (!done) {
			System.out.println("\n\nSelect a transformation to apply to your picture:\n");
			System.out.println("0. Reset original image.");
			System.out.println("1. Make the Negative Image.");
			System.out.println("2. Flip the Image Left to Right.");
			System.out.println("3. Flip the Image Top to Botton.");
			System.out.println("4. Mirror Image Left to Right.");
			System.out.println("5. Mirror Image Top to Bottom.");
			System.out.println("6. Shift Colors.");
			System.out.println("7. MY RAZZLE DAZZLE METHOD");
			System.out.println("x. Exit this program.");
			System.out.println("s. Save and Exit this program.");
			System.out.print("\nEnter the integer corresponding to the transform you desire:");

			choice = in.next().charAt(0);
			switch (choice) {
			case '0':
				thePicture.reload();
				break;
			case '1':
				makeNegative();
				break;
			case '2':
				flipLR();
				break;
			case '3':
				flipTB();
				break;
			case '4':
				mirrorLR();
				break;
			case '5':
				mirrorTB();
				break;
			case '6':
				rgb2gbr();
				break;
			case '7':
				System.out.println("Select another picture!");
				myRazzleDazzle();
				break;
			case 's':
				System.out.print("Save the picture as: ");
				String picName = in.next();
				thePicture.saveAs(picName);
				// No 'break;' this means 's' flows into 'x'
			case 'x':
				done = true;
				System.out.print("Good Bye");
				break;
			default:
				System.out.println("Your entry is not a choice. Please try again.");
			}
		}
		in.close();
	}

	// makeNegative() alters every pixel in thePicture so that its color is changed to be its negative:
	// each color intensity - R, G, B -is subtracted from the MAX intensity, 255.
	// This method comes from our textbook's Chapter Six image example.
	public static void makeNegative() {
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				Color original = thePicture.getColorAt(x, y);
				Color negative = new Color(255 - original.getRed(), 255 - original.getGreen(), 255 - original.getBlue());
				thePicture.setColorAt(x, y, negative);
			}
	}

	public static void flipLR() {
		for (int x = 0; x < width/2; x++)
			for (int y = 0; y < height; y++) {
				Color leftSide = thePicture.getColorAt(x, y);
				Color rightSide = thePicture.getColorAt((width-x-1), y);
				thePicture.setColorAt(x, y, rightSide);
				thePicture.setColorAt((width-x-1), y, leftSide);
			}
	}

	private static void flipTB() {
		for (int y = 0; y < height/2; y++)
			for (int x = 0; x < width; x++) {
				Color topSide = thePicture.getColorAt(x, y);
				Color botSide = thePicture.getColorAt(x, (height-y-1));
				thePicture.setColorAt(x, y, botSide);
				thePicture.setColorAt(x, height-y-1, topSide);
			}
	}

	private static void mirrorLR() {
		for (int x = 0; x < width/2; x++)
			for (int y = 0; y < height; y++) {
				Color mirrorRight = thePicture.getColorAt((width-x-1), y);
				thePicture.setColorAt(x, y, mirrorRight);
			}
	}

	private static void mirrorTB() {
		for (int y = 0; y < height/2; y++)
			for (int x = 0; x < width; x++) {
				Color mirrorBot = thePicture.getColorAt(x,(height-y-1));
				thePicture.setColorAt(x, y, mirrorBot);
			}
	}

	private static void rgb2gbr() {
		// From Red | Green | Blue 
		// To Green | Blue  | Red
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				Color original = thePicture.getColorAt(x, y);
				Color changed = new Color(original.getGreen(), original.getBlue(), original.getRed());
				thePicture.setColorAt(x, y, changed);
			}
	}

	private static void myRazzleDazzle() {
		Picture razzlePicture = new Picture();
		razzlePicture.pick();
		width = (int)(Math.random() * thePicture.getWidth());
		height = (int)(Math.random() * thePicture.getHeight());
		razzlePicture.closeRazzle();
		
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				Color original = thePicture.getColorAt(x, y);
				Color random = new Color(((int)((Math.random()*255)+1)), original.getBlue(), original.getBlue());
				thePicture.setColorAt(x, y, random);
			}
		for (int x = 0; x < width/4; x++)
			for (int y = 0; y < height; y++) {
				Color mirrorRight = thePicture.getColorAt((width-x-1), y);
				thePicture.setColorAt(x, y, mirrorRight);
			}
		for (int y = 0; y < height/4; y++)
			for (int x = 0; x < width; x++) {
				Color mirrorBot = thePicture.getColorAt(x,(height-y-1));
				thePicture.setColorAt(x, y, mirrorBot);
			}
	}

}
