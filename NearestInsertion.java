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
 *  salesperson problem and plot results.
 *
 *  % java NearestInsertion < tsp1000.txt
 *
 *  Optional integer command-line argument specifies animation delay in ms  
 *  % java NearestInsertion 10 < tsp1000.txt
 *  
 *************************************************************************/

public class NearestInsertion 
{

	public static void main(String[] args) 
	{
		try
		{
            long start = System.currentTimeMillis();
			String filename = args[0];
			Scanner file = new Scanner(new File(filename));
			// check for optional command-line argument that turns on animation
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
			TourGroupTwo tour = new TourGroupTwo();
			while (file.hasNextDouble()) 
			{
				double x = file.nextDouble();
				double y = file.nextDouble();
				Point p = new Point(x, y);
				tour.insertNearest(p);
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

            double end = System.currentTimeMillis() - start;
            System.out.println(end);
            System.out.println(end/1000.0);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
		}
	}

}
