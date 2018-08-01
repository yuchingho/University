import java.awt.*;

public class Rectangle extends Shape	{
	// Instance variables
	private int width;
	private int height;

	// Constructor
	public Rectangle(int x, int y, Color color, int width, int height)	{
		super(x, y, color);
		this.width = width;
		this.height = height;
	}

	// Instance methods
	public void draw(Graphics g)	{
		g.setColor(getColor());
		g.fillRect(getX(), getY(), width, height);
	}

	public int getHeight()	{
		return height;
	}

	public int getWidth() {
		return width;
	}

	public String toString()	{
		return super.toString() + "Width: " + this.getWidth() + " Height: " + this.getHeight();
	}
	
	public void scale(double factor)	{
		width = (int)(width * factor);
		height = (int)(height * factor);
	}
	
	public double area()	{
		double area  = height * width;
		return area;
	}
}