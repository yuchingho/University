import java.awt.*;
//import jpb.*;

// All that is commented out only works in Linux

public class NervousShapes	{
	// Constants
	private static final int DELAY = 10;	 	// Animation delay (milliseconds) 
	private static final int MAX_SIZE = 20;		// Maximum width and height of a shape
	private static final int MIN_SIZE = 10;		// Minimum width and height of a shape
	private static final int NUM_SHAPES = 50;	// Number of shapes
	private static final int WINDOW_SIZE = 200;	// Width and height of draw-able portion of frame
    
	// Class variables
	private static DrawableFrame df;		// Frame in which shapes are displayed
	private static Graphics g;				// Graphics context for frame
	private static Shape shapes[] = new Shape[NUM_SHAPES];	// Array of shapes
    

	public static void main(String[] args) {
		createWindow();
		createShapes();
		animateShapes();
	}

  ///////////////////////////////////////////////////////////
  // NAME:       createWindow
  // BEHAVIOR:   Creates a frame labelled "Nervous Shapes",
  //             displays the frame, and sets the size of
  //             the frame (using the WINDOW_SIZE class
  //             variable). Assigns the frame to the df
  //             class variable, and assigns the frame's
  //             graphics context to the g class variable.
  // PARAMETERS: None
  // RETURNS:    Nothing
  ///////////////////////////////////////////////////////////
	
	private static void createWindow() {
		// Create a frame labelled "Nervous Shapes" and set its size
		df = new DrawableFrame("Nervous Shapes");
		df.setVisible(true);
		df.setSize(WINDOW_SIZE, WINDOW_SIZE);
		
		g = df.getGraphicsContext();	// Get the frame's graphics context
	}

  ///////////////////////////////////////////////////////////
  // NAME:       createShapes
  // BEHAVIOR:   Creates enough Circle and Rectangle objects
  //             to fill the shapes array. Each shape has a
  //             random colour, size, and position. The height
  //             and width of each shape must lie between
  //             MIN_SIZE and MAX_SIZE (inclusive). The
  //             position is chosen so that the shape is
  //             completely within the drawing area.
  // PARAMETERS: None
  // RETURNS:    Nothing
  ///////////////////////////////////////////////////////////
	
	private static void createShapes()	{
		for (int i = 0; i < shapes.length; i++)	{
			// Select a random colour
			int red = generateRandomInt(0, 255);
			int green = generateRandomInt(0, 255);
			int blue = generateRandomInt(0, 255);
			Color color = new Color(red, green, blue);

			// Decide whether to create a circle or a rectangle
			if (Math.random() < 0.33)	{
				// Generate a circle with a random size and position
				int diameter = generateRandomInt(MIN_SIZE, MAX_SIZE);
				int x = generateRandomInt(0, WINDOW_SIZE - diameter);
				int y = generateRandomInt(0, WINDOW_SIZE - diameter);
				shapes[i] = new Circle(x, y, color, diameter);
			} 
			else if(Math.random() < 0.66)	{
				// Generate a rectangle with a random size and position
				int width = generateRandomInt(MIN_SIZE, MAX_SIZE);
				int height = generateRandomInt(MIN_SIZE, MAX_SIZE);
				int x = generateRandomInt(0, WINDOW_SIZE - width);
				int y = generateRandomInt(0, WINDOW_SIZE - height);
				shapes[i] = new Rectangle(x, y, color, width, height);
			} 
			else	{
				int size = generateRandomInt(MIN_SIZE, MAX_SIZE);
				int x = generateRandomInt(0, WINDOW_SIZE - size);
				int y = generateRandomInt(0, WINDOW_SIZE - size);
				shapes[i] = new Triangle(x, y, color, size);
			}
		}
	}

  ///////////////////////////////////////////////////////////
  // NAME:       animateShapes
  // BEHAVIOR:   Establishes an infinite loop in which the
  //             shapes are animated. During each loop
  //             iteration, the drawing area is cleared and
  //             the shapes are then drawn at new positions.
  //             The new x and y coordinates for each shape
  //             will either be the same as the old ones,
  //             one pixel smaller, or one pixel larger. A
  //             shape is not moved if doing so would cause
  //             any portion of the shape to go outside the
  //             drawing area. At the end of each animation
  //             cycle, there is a brief pause, which is
  //             controlled by the delay constant.
  // PARAMETERS: None
  // RETURNS:    Nothing
  ///////////////////////////////////////////////////////////
	private static void animateShapes()	{
		while (true) {
      	// Clear drawing area
		g.setColor(Color.white);
		g.fillRect(0, 0, WINDOW_SIZE - 1, WINDOW_SIZE - 1);

		for (int i = 0; i < shapes.length; i++)	{
			// Change the x coordinate for shape i
			int dx = generateRandomInt(-1, +1);
			int newX = shapes[i].getX() + dx;
			if (newX >= 0 && newX + shapes[i].getWidth() < WINDOW_SIZE)
				shapes[i].move(dx, 0);

			// Change the y coordinate for shape i
			int dy = generateRandomInt(-1, +1);
			int newY = shapes[i].getY() + dy;
			if (newY >= 0 && newY + shapes[i].getHeight() < WINDOW_SIZE)
			shapes[i].move(0, dy);

			shapes[i].draw(g);	// Draw shape i at its new position
		}

		// Call repaint to update the screen
		df.repaint();

		// Pause briefly
		try	{
			Thread.sleep(DELAY);
		} 
		catch (InterruptedException e) {}
		}
	}

  ///////////////////////////////////////////////////////////
  // NAME:       generateRandomInt
  // BEHAVIOR:   Generates a random integer within a
  //             specified range.
  // PARAMETERS: minimum - the lower bound of the range
  //             maximum - the upper bound of the range
  // RETURNS:    A random integer that is greater than or
  //             equal to minimum and less than or equal to max
  ///////////////////////////////////////////////////////////
	private static int generateRandomInt(int min, int max)	{
		return (int) ((max - min + 1) * Math.random()) + min;
	}
}