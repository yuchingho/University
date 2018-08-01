public class Student {
	private double[] scores;
	private int scoresSize;

	// Constructs a student with no scores and a maximum number of scores.
	public Student(int capacity) {
		scores = new double[capacity];
		scoresSize = 0;
	}

	// Adds a score for this student.
	public boolean addScore(double score) {
		if (scoresSize < scores.length) {
			scores[scoresSize] = score;
			scoresSize++;
			return true;
		} else {
			return false;
		}
	}

	// Gets the position of the minimum score.
	public int minimumPosition() {
		if (scoresSize == 0) {
			return -1;
		}
		int smallestPosition = 0;
		for (int i = 1; i < scoresSize; i++) {
			if (scores[i] < scores[smallestPosition]) {
				smallestPosition = i;
			}
		}
		return smallestPosition;
	}

	// Computes the sum of the scores
	public double sum() {
		double total = 0;
		for (int i = 0; i < scoresSize; i++) {
			total = total + scores[i];
		}
		return total;
	}

	// Removes a score at a given position.
	public void removeScore(int pos) {
		// Remove the element at this position--see Section 7.3.6
		scores[pos] = scores[scoresSize - 1];
		scoresSize--;
	}
}
