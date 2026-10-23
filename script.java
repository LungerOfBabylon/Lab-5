import edu.princeton.cs.algs4.*;

public class script {
    public static void main(String[] args){
	int size = Integer.parseInt(args[0]);
	
	String pre = StdIn.readLine();
	StdOut.println(pre);
	StdOut.println();
	
	for (int i = 0; i < size/2; i++){
	    StdIn.readLine();
	}
	for (int i = size/2; i < size/2 + size; i++){
	    StdOut.println(StdIn.readLine());
	}
    }
}
