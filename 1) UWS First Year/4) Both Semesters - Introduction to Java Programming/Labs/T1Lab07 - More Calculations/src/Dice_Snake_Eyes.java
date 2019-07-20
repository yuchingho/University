public class Dice_Snake_Eyes {

	public static void main(String[] args) {
        int die1; 
        int die2;   
        int countRolls;  
        countRolls = 0;
        
        do { 	
        	die1 = (int)(Math.random()*6) + 1;   
        	die2 = (int)(Math.random()*6) + 1;
        	countRolls++;   
        		
        	TextIO.put(die1);
        	TextIO.putln(" and " + die2 + ".");
        }                    
        while ( die1 != 1 || die2 != 1 );
       	TextIO.putln(" ");
       	TextIO.putln("It took " + countRolls + " rolls to get snake eyes.");	
     }  
 }  