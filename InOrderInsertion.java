import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*************************************************************************
 *  YOU DO NOT NEED TO MODIFY THIS FILE
 *
 *  Compilation:  javac NearestInsertion.java
 *  Execution:    java NearestInsertion < file.txt
 *  Dependencies: Tour.java Point.java StdIn.java StdDraw.java
 *
 *  Run nearest neighbor insertion heuristic for traveling
 *  salesperson problemand plot results.
 *
 *  % java NearestInsertion < tsp1000.txt
 *
 *************************************************************************/

public class InOrderInsertion {

	public static void main(String[] args) {

		try
		{
			String filename = args[0];
			Scanner file = new Scanner(new File(filename));
			// KDV, added to demo animation
			int delay = -1;
			if (args.length > 1)
				delay = Integer.parseInt(args[1]);

			// get dimensions
			int w = file.nextInt();
			int h = file.nextInt();
			StdDraw.setCanvasSize(w, h);
			StdDraw.setXscale(0, w);
			StdDraw.setYscale(0, h);

			// turn on animation mode
			StdDraw.show(0);

			// run smallest insertion heuristic
			TourGroupOne tour = new TourGroupOne();
			while (file.hasNextDouble()) {
				double x = file.nextDouble();
				double y = file.nextDouble();
				Point p = new Point(x, y);
				if (delay > -1)
				{
					StdDraw.clear();
					tour.draw();
					StdDraw.text(100, 0, "" + tour.distance());
					StdDraw.show(delay);
				}
			}
			file.close();

			// draw to standard draw
			tour.draw();
			StdDraw.show(0);

			// print tour to standard output
			System.out.println("Tour distance = " + tour.distance());
			tour.show();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found.");;
		}
	}

}
