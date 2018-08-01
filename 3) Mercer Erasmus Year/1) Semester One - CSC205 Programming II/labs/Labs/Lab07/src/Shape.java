import java.awt.*;

public abstract class Shape	{
	// Instance variables
	private int x;
	private int y;
	private Color color;

	// Constructor
	protected Shape(int x, int y, Color color)	{
		this.x = x;
		this.y = y;
		this.color = color;
	}

	// Abstract methods
	public abstract void draw(Graphics g);
	public abstract int getHeight();
	public abstract int getWidth();
	
	public abstract void scale(double factor);
	public abstract double area();

	// Other instance methods
	public Color getColor()	{
		return color;
	}

	public int getX()	{
		return x;
	}

	public int getY()	{
		return y;
	}

	public void move(int dx, int dy)	{
		x += dx;
		y += dy;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String toString()	{
		return ("\nPosition: (" + this.getX() + ", " + this.getY() + 
				") \nColor: (r = " + this.getColor().getRed() + 
				", b = " + this.getColor().getBlue() + 
				", g = " + this.getColor().getGreen() + ")\n");
	}
}