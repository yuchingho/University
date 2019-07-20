public class Capitalize {

	public static void main(String[] args) {
	    String line; 
    	TextIO.put("Enter a line of text to be capitalized: ");
    	line = TextIO.getln();
    	
    	TextIO.putln("\nCapitalized version:");
    	printCapitalised(line);	
    }
   
   static void printCapitalised(String str) {
      char ch;       
      char prevCh;   
      int i;       
      prevCh = '.'; 
      
      for (i = 0; i < str.length(); i++) {
    	  ch = str.charAt(i);
    	  if (Character.isLetter(ch) && ! Character.isLetter(prevCh))
    		  TextIO.put( Character.toUpperCase(ch) );
    	  else
    		  TextIO.put(ch);
    	  	  prevCh = ch; 	
      }	
   }
}