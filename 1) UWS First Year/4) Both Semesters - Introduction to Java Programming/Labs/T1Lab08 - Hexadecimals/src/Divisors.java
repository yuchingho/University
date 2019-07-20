public class Divisors {

	public static void main(String[] args) {
		int N;           
	    int maxDivisors = 1;
	    int numWithMax = 1;
	       
	    for (N = 2; N <= 10000; N++) {
	    	int D;  	           
	        int divisorCount = 0;
	           
	        for (D = 1; D <= N; D++) {  
	        	if (N % D == 0)
	        	divisorCount++;	 
	        }
	           
	        if (divisorCount > maxDivisors) {
	        	maxDivisors = divisorCount;
	        	numWithMax = N;	
	        }
        }
	    TextIO.putln("Among integers between 1 and 10,000.");
	    TextIO.putln("The maximum number of divisors is " + maxDivisors + ".");
	    TextIO.putln("The number with " + maxDivisors + " divisors is " + numWithMax + ".");	
	 }
}