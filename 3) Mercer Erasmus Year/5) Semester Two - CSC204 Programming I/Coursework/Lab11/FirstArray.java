// Yu-Ching Ho
// Lab 11

public class FirstArray {
	
	public static void main(String[] args) {
		int[] a = new int[5];

		for (int k = 0; k < a.length; k++)
			a[k] = k * k;
	  a[2+(4/3)] = -1;

		for (int k = 0; k < a.length; k++)
			System.out.println(a[k]);
	}
}
