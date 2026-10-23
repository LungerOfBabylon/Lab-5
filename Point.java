import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*************************************************************************
 *  YOU DO NOT NEED TO MODIFY THIS FILE
 *
 * Taken from section 3.2, An Introduction to Programming (in Java) by Robert
 * Sedgewick and Kevin Wayne
 *
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *
 *  Immutable data type for 2D points.
 *
 *************************************************************************/

public class Point {
	private static final Color[] colors = new Color[1000];
	private final double x;   // Cartesian
	private final double y;   // coordinates
	private final Color index;

	static {
		for(int i = 0; i < colors.length; i++)
		{
			colors[i] = new Color((float)Math.random(),
					(float)Math.random(),
					(float)Math.random());
		}
	}

	// create and initialize a point with given (x, y)
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
		index = colors[(int)(Math.random() * colors.length)];
	}

	// return Euclidean distance between invoking point this and that
	public double distanceTo(Point that) {
		double dx = this.x - that.x;
		double dy = this.y - that.y;
		return Math.sqrt(dx*dx + dy*dy);
	}

	// draw this point using standard draw
	public void draw() {
		StdDraw.point(x, y);
	}

	// draw the line from the invoking point this to that using standard draw
	public void drawTo(Point that) {

		StdDraw.setPenColor(index);
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	// return string representation of this point
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	// test client
	public static void main(String[] args) {

		try
		{
			String filename = args[0];
			Scanner file = new Scanner(new File(filename));
			// get dimensions
			int w = file.nextInt();
			int h = file.nextInt();
			StdDraw.setCanvasSize(w, h);
			StdDraw.setXscale(0, w);
			StdDraw.setYscale(0, h);
			StdDraw.setPenRadius(.001);

			StdDraw.show(0);
			// read in and plot points one at at time
			int i = 0;
			while (file.hasNextDouble()) {
				double x = file.nextDouble();
				double y = file.nextDouble();
				Point p = new Point(x, y);
				p.draw();
				if (i % 20 == 0)
					StdDraw.show(0);
				i++;
			}
			file.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
		}
		StdDraw.show(0);
	}
}
