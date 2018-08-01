import java.awt.*;

public class ShapeTest	{

	public static void main(String[] args)	{
		
		Color testColor = new Color(10, 20, 30);
		Circle testCircle = new Circle(0, 0, testColor, 5);
		Rectangle testRect = new Rectangle(0, 0, testColor, 2, 3);
		
		System.out.println("myCircle: " + testCircle);
		System.out.println("myRectangle: " + testRect + "\n");
		
		testCircle.scale(5.0);
		testRect.scale(5.0);
		
		System.out.println("myCircle: " + testCircle);
		System.out.println("myRectangle: " + testRect + "\n");
		
		System.out.println("myCircle's area: " + testCircle.area());
		System.out.println("myRectangle's area: " + testRect.area());
	}
}