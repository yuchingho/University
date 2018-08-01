public class squareMatrix {

	public static void main(String[] args) {

		int[][] Matrix = { {8, 1, 6,},
				   		   {3, 5 ,7,},
				   		   {4, 9, 2 }
		};
		
		int hsum = 0;
		for (int i = 0; i < Matrix.length; i++) {
			hsum = 0; 
			for (int j = 0; j < Matrix[0].length; j++)
				hsum += Matrix[i][j];
			System.out.println("The sum of each of our rows = " + hsum);
		}	
		
		int vsum = 0;
		for (int j = 0; j < Matrix[0].length; j++) {
			vsum = 0;
			for (int i = 0; i < Matrix.length; i++)
				vsum += Matrix[i][j];
			System.out.println("The sum of each of our columns = " + vsum);
		}
		
		int dsum1 = 0;
		for (int i = 0; i < Matrix.length; i++)
			dsum1 += Matrix[i][i];
		System.out.println("The diagonal sum from Top Left to Bottom Right = " + dsum1);
		
		int dsum2 = 0;
		for (int i = 0; i < Matrix.length; i++)
			dsum2 += Matrix[i][Matrix.length-i-1];
		System.out.println("The diagonal sum from Top Right to Bottom Left = " + dsum2);
		
		if (hsum + vsum + dsum1 + dsum2 == 60) {
			System.out.println("Yes, It's a Magic Square!");
		}	
	}	
}