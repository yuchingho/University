import java.util.Random;

// This class models a die that, when cast, lands on a random face.
public class Die {
	private Random generator;
	private int sides;

	// Constructs a die with a given number of sides. 
	// The number of sides, e.g. 6 for a normal die

	public Die(int s) {
		sides = s;
		generator = new Random();
	}

	// Simulates a throw of the die
	public int cast() {
		return 1 + generator.nextInt(sides);
	}
}
