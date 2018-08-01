//This example demonstrates local variables.

public class Counter {

	private int value; // value is instance variable

	// Advances the value of this counter by 1.
	public void click() {
		int updatedValue = value + 1;
		// updatedValue is a local variable
		value = updatedValue;
		// updatedValue is forgotten here
	}

	// Resets the value of this counter to a given value.
	public void resetTo(int newValue) {
		// Takes newValue and makes it unavailable so newValue is forgotten here
		value = newValue;
	}

	// Gets the current value of this counter.
	public int getValue() {
		return value;
	}
}
