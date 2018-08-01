// Yu-Ching Ho
// Lab 11

public class CrazyArray {
	
	public static void main(String[] args) {

		int a[] = { 3, 72, 0, 41, -23, 64 };
		int i = 2;
		a[a[i]] = a[a[i]] - 1;
		int j = a[i];

		System.out.println(a[2 + a[j]]);
	}
}
