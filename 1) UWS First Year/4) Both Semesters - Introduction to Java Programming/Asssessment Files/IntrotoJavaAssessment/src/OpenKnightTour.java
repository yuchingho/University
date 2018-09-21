public class OpenKnightTour {	

	static String mapBoard[][] = {{" ","A","B","C","D"},
								  {"1","-","-","-","-"},
								  {"2","-","-","-","-"},
								  {"3","-","-","-","-"}};
	
	// drawMap function is made private so would not interfere with rest of the code and will only be used when called.
	private static void drawMap() {
		for (int a = 0; a < mapBoard.length; a++) {
			// The code loops through the 2D array until it reaches the last entry in the array.
			for (int b = 0; b < mapBoard[0].length; b++) {
				if(mapBoard[a][b] == "-") {
					// If "-" is read in mapBoard, " -" will be put there to represent dashes in the table.
					TextIO.put(" -");
            	}
            	else {
            		// Puts a space before the rows and columns to space out the table evenly.
            		TextIO.put(" " + mapBoard[a][b]);
            	}
				// Puts an indent after each value to space out the columns.
            	TextIO.put("\t");
            }
    		TextIO.putln();
        }
    }

	// saveFile function to save a text file containing recorded moves.
	public static void saveFile() {
		// Saves a text file by that name.
		TextIO.writeFile("Open_Knight's_Tour.txt");
	}

	public static void main(String[] args) {
        int userMoves = 1;	// Integer value to count the number of user moves.
        boolean quitGame = false;	// Boolean value to check later if the user wants to quit.
		int toNumber = 0;			// Integer value set to '0' so user input can be switched to switch statement later.
		String Knight = "0";
		// Knight is set as a string so it can be switched later on to userMoves or to read if 'q' has been input.
        
		// If the user has not quit the game or has filled the board, the program will loop until either one of them happens.
		while (quitGame == false && userMoves != 13) {
			TextIO.putln();
       		if (userMoves == 1) {
       			TextIO.putln("Welcome to the Open Knight's Tour!");		
        	    TextIO.putln("This program allows you to move a Knight around a '3 by 4' chessboard. ");
        	    TextIO.putln("Your task is to have it visit each of the 12 squares on the board exactly once");
        	    TextIO.putln("(i.e. without visiting any square twice).\n");
        	    TextIO.putln("------------------------------------------------------------------------------");
        	    TextIO.putln("  You are only able to move your Knight in the shape of an ' L '");
        	    TextIO.putln("  Please do not cheat! You will not have fun solving the problem if you do.");
        	    TextIO.putln("------------------------------------------------------------------------------\n");
        	    TextIO.putln("Please enter your move in the format (file)(rank) like so: 'd3' or 'D3'.");
        	    TextIO.putln("To end the game, you can press 'q' to quit.\n");
            	drawMap();
        	    TextIO.putln("Where would you like to place your knight to start the problem?");
        	    TextIO.putln("(file)(rank) = ");
        	}
        	else {
        		drawMap();
        		// Shows where the Knight is and asks the question for the next move.
        		TextIO.putln("The Knight is on: " + Knight + ". Where do you want to put your next move?");
        		TextIO.putln("(file)(rank) = ");
        		TextIO.putln("Or press 'q' to quit the game.");
        	}
        	Knight = TextIO.getlnString();
        	Knight = Knight.toUpperCase();

        	if (Knight.equals("Q")) {
        		quitGame = true;
            }
            else {
            	// Limits user input.
            	// If user types something which is more or less than 2 characters, will deem it as invalid and re-loop the program
            	if (Knight.length() > 2 || Knight.length() <= 1) {
            		Knight = "Invalid square! Please enter a correct move";
                }
                char getNumber = Knight.charAt(0);
                int secondNumber = Character.getNumericValue(Knight.charAt(1));
           		switch (getNumber) {
           		case 'A':
           			toNumber = 1;
        			break;
                case 'B':
        			toNumber = 2;
        			break;
                case 'C':
        			toNumber = 3;
        			break;
                case 'D':
        			toNumber = 4;
        			break;
                default:
        			toNumber = 0;
        			break;
                }     			
                if (secondNumber < 1 || secondNumber > 3) {
                	secondNumber = 0;
                }
                // secondNumber = column, toNumber = file
                if (mapBoard[secondNumber][toNumber] == "-") {
                	// Check if "-" is still there, and if it is, userMoves replaces it in the appropriate location.
                	mapBoard[secondNumber][toNumber] = Integer.toString(userMoves);
                	userMoves++;
                }
                else {
                	TextIO.putln("Invalid move! Please enter a correct move.");
                }
            }
        }
                	
        drawMap();
        TextIO.putln();
        TextIO.putln("Game over!");
        
        // When the user has filled the board, the game will end and display a congratulations message
        if (userMoves == 13) {
        	TextIO.putln("\nCongratulations you have completed the game!");
        	TextIO.putln("Search for Open_Knight's_Tour.txt in your documents to find your saved moves!");
        		
        	saveFile();
        	TextIO.putln("Here are your moves!");
        	TextIO.putln();
        	drawMap();
        }
    }
}