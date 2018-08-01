/**
 * A program to carry on conversations with a human user. This is the initial
 * version that:
 * <ul>
 * <li>Uses indexOf to find strings</li>
 * <li>Handles responding to simple words and phrases</li>
 * </ul>
 * This version uses a nested if to handle default responses.
 * 
 * @author Laurie White
 * @version April 2012
 * 
 *          Yoshi Lab08
 */
public class Magpie {
	/** Get a default greeting */
	public String getGreeting() {
		return "Hello, let's talk.";
	}

	/** Gives a response to a user statement */
	public String getResponse(String statement) {
		String response = "";

		if (statement.indexOf("no") >= 0) {
			int noResponse = 3;
			double r = Math.random();
			int randomResponse = (int) (r * noResponse);
			if (randomResponse == 0) {
				response = "Oh, I'm sorry I asked.";
			} 
			else if (randomResponse == 1) {
				response = "Please don't hit me!";
			} 
			else if (randomResponse == 2) {
				response = "Why so negative!?";
			}
		} 
		else if (statement.indexOf("mother") >= 0 || 
				 statement.indexOf("father") >= 0 || 
				 statement.indexOf("sister") >= 0) {
					response = "Tell me more about your family.";
		} 
		else if (statement.indexOf("brother") >= 0) {
			int broResponse = 2;
			double r = Math.random();
			int randomResponse = (int) (r * broResponse);
			if (randomResponse == 0) {
				response = "Tell me more about your family.";
			} 
			else if (randomResponse == 1) {
				response = "What's your brother's name?";
			} 
			else if (randomResponse == 1) {
				response = "Did he go to Mercer University?";
			}
		} 
		else if (statement.indexOf("dog") >= 0 || 
				 statement.indexOf("cat") >= 0) {
					response = "Tell me more about your pets.";
		} 
		else if (statement.indexOf("Dr") >= 0 || 
				 statement.indexOf("dr") >= 0 || 
				 statement.indexOf("Bob") >= 0 || 
				 statement.indexOf("bob") >= 0 || 
				 statement.indexOf("Allen") >= 0 || 
				 statement.indexOf("allen") >= 0) {
					int AllenResponse = 3;
					double r = Math.random();
					int randomResponse = (int) (r * AllenResponse);
					if (randomResponse == 0) {
						response = "Dr Allen is the best teacher at Mercer!";
					} 
					else if (randomResponse == 1) {
						response = "Dr Allen is teaching us about chatbots.";
					} 
					else if (randomResponse == 2) {
						response = "He is so funny! I enjoy his class so much!";
					}
			} 
		else if (statement.trim().length() > 0) {	
			response = getRandomResponse();
		}
		else {
			int nothingResponse = 3;
			double r = Math.random();
			int randomResponse = (int) (r * nothingResponse);
			if (randomResponse == 0) {
				response = "Say something please.";
			} 
			else if (randomResponse == 1) {
				response = "... Are you going to type something?";
			} 
			else if (randomResponse == 2) {
				response = "Hurry up, I've not got all day...";
			}	
		}
		return response;
	}

	/** Pick a default response to use if nothing else fits. */
	private String getRandomResponse() {
		final int NUMBER_OF_RESPONSES = 6;
		double r = Math.random();
		int whichResponse = (int) (r * NUMBER_OF_RESPONSES);
		String response = "";

		if (whichResponse == 0) {
			response = "Interesting, tell me more.";
		} else if (whichResponse == 1) {
			response = "Hmmm.";
		} else if (whichResponse == 2) {
			response = "Do you really think so?";
		} else if (whichResponse == 3) {
			response = "You don't say.";
		} else if (whichResponse == 4) {
			response = "Jeez.";
		} else if (whichResponse == 5) {
			response = "Why are you so mean to me?!";
		}
		return response;
	}
}
