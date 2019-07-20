public class BabyCrabCone {

	public static void main(String[] args) {
		solvePuzzle();
	}

	public static void solvePuzzle() {
		// constants
	    final String LEFT = "Left";
	    final String RIGHT = "Right";
	    final int MAMA = 0; // used to select mama only
	    final int BABY = 1; // used to select crossing with baby
	    final int CRAB = 2; // used to select crossing with crab
	    final int CONE = 3; // used to select crossing with cone
	        
	    // variables to keep track of location of Mama and items
	    String bankMama = LEFT;
	    String bankBaby = LEFT;
	    String bankCone = LEFT;
	    String bankCrab = LEFT;
	        
	    // Display opening message
	    TextIO.put("Welcome to the Puzzle of the Baby, the");
	    TextIO.putln("Crab, and the Cone.");
	    TextIO.put("One hot summer day, Mama crossed the river");
	    TextIO.putln(" to the left bank to take a break from");
	    TextIO.put("programming and buy an ice cream. She ");
	    TextIO.putln("brought her baby and her pet crab.");
	    TextIO.put("On the return she came to the river");
	    TextIO.putln("and was faced with a dilemma:");
	    TextIO.put("how could they all cross without ");
	    TextIO.putln("disaster? The ferry holds Mama");
	    TextIO.put("and just one other item.");
	    TextIO.putln(" The problem is that:");
	    TextIO.put(" * if the baby is left with the cone, ");
	    TextIO.putln("it spoils its dinner.");
	    TextIO.put(" * if the baby is left with the crab, ");
	    TextIO.putln("the crab bites the baby.");
	    TextIO.put("Please help Mama make her journey ");
	    TextIO.putln("across the river!");
	       
	    // Variables relating to user input
	    int option = 0; // option that the user chooses
	    boolean valid = false; // indicates whether choice is valid
	      
	    boolean disaster = false; // Mama leaves baby, disaster strikes
	    int crossings = 0; // count number of times Mama crosses river

	    // Main program loop
	    do {
	    	if (crossings > 7) {
	    		TextIO.put("Oh dear, Mama seems to have been ");
	            TextIO.putln("crossing this river all day!!");
	            TextIO.putln("The ice cream will melt soon!");
	    	}	            
	        // Display location of mama and items and offer menu
	        TextIO.putln("\nMama is on the " + bankMama);
	        TextIO.putln("The baby is on the " + bankBaby + " bank");
	        TextIO.putln("The crab is on the " + bankCrab + " bank");
	        TextIO.putln("The cone is on the " + bankCone + " bank");
	        TextIO.putln("Who would you like Mama to cross with?");
	        TextIO.putln ("(Type 0 for no one, 1 for baby, 2 for crab, 3 for cone");
	        option = TextIO.getlnInt();	// Get the user choice
	            
	        // Check that choice is valid
	        switch (option) {
	        	case MAMA:
	         		valid = true;
	         		break;
	        	case BABY:
	            	valid = bankBaby == bankMama;
	            	break;
	            case CRAB:
	            	valid = bankCrab == bankMama;
	            	break;
	            case CONE:
	            	valid = bankCone == bankMama;
	            	break;
	            default: valid = false;
	         }
	            
	         if (!valid) {
	        	 if (option >= MAMA && option <= CONE) {
	        		 TextIO.putln("That is on the other bank, Mama can't take it!");
	             }
	        	 else {
	        		 TextIO.putln("That wasn't an option!");
	        	 }
	         } 
	         else {
	        	 if (bankMama == LEFT) {
	        		 bankMama = RIGHT;
	        	 }
	        	 else {	// Mama on right bank
	        		 bankMama = LEFT;
	        	 }
	             crossings++;
	             TextIO.put("Mama has crossed the river");
	                
	             switch (option) {
	             	case MAMA:
	             		TextIO.putln();
	                    break;
	                case BABY:
	                    TextIO.putln(" with the baby");
	                    bankBaby = bankMama;
	                    break;
	                case CRAB:
	                    TextIO.putln(" with the crab");
	                    bankCrab = bankMama;
	                    break;
	                case CONE:
	                    TextIO.putln(" with the cone");
	                    bankCone = bankMama;
	                    break;
	                }
	                disaster = (bankBaby != bankMama) && ((bankBaby == bankCrab) || (bankBaby == bankCone));
	         }
	    }
	    while (!disaster && !((bankBaby == RIGHT) && (bankCrab == RIGHT) && (bankCone == RIGHT) && (bankMama == RIGHT)));
	    	if (!disaster) {
	    		TextIO.putln("OH THANK YOU!!!");
	            TextIO.putln("Mama, baby, crab and cone are all safely across!");
	            if (crossings > 7) {
	                TextIO.putln("But poor Mama had to cross the river" + crossings + " times.");
	                TextIO.putln("She's ready for that ice cream!!");
	                if (crossings > 11) {
	                	TextIO.putln("OH NO!! The ice cream has melted!!!");
	                }
	            }
	        } 
	    	else {  // disaster
	    		if (bankBaby == bankCone) {
	    			TextIO.putln("Oh no!! The baby ate the ice cream!");
	            }
	            if (bankBaby == bankCrab) {
	                TextIO.put("Oh no! The baby is crying!!");
	                TextIO.putln("The crab has bitten the baby!!");
	            }
	            TextIO.putln("We'll have to stop and help Mama and her family.");
	            TextIO.putln("Undoubtedly, she'll need your help again.");
	            TextIO.putln();
	    	}
	}
}