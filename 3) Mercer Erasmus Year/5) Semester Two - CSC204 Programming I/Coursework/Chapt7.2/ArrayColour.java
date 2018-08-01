import java.awt.Color;
import java.util.Random;

public class ArrayColour {

	public static void main(String[] args) {
		Color myColours[] = new Color[5];
		fillArrayWithColours(myColours);
		print(myColours);
	}
	
	private static void fillArrayWithColours(Color[] myColours) {
		Random random = new Random();
		for(int i = 0; i < myColours.length; i++) {
			myColours[i] = new Color(random.nextInt(256), random.nextInt(256),random.nextInt(256));
		}
	}
	
	private static void print(Color[] myColours) {
		// Enhanced for-loop only works for collections
		//  Left side: a variable to hold the collection
		// Right side: the collection
		for(Color c: myColours) {
			System.out.println(c);
		}
	}
}
