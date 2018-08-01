public class EnhancedForLoopDemo {
	public static void main(String[] args) {
		int[] values = new int[10];

		// In this loop, we need the index value, so we can't use an enhanced for loop.
		for (int i = 0; i < values.length; i++) {
			values[i] = i * i;
		}

		// In this loop, we don't need the index value.
		// The enhanced for-loop simplifies the code.
		int total = 0;
		for (int element : values) {
			System.out.println(element);
			total = total + element;
		}
		System.out.println("Sum: " + total);
	}
}
