import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.StdOut;

public class TourGroupOne {
    private Node first;
    private int n;

    private class Node { // creating the Node subClass
        private Point p;
        private Node next;
    }

    public TourGroupOne() {
    }

    public void show() { // print the tour to standard output
        Node current = first;
        if (first == null) {
            System.out.println(first.p.toString());
        } else {
            while (current != null) {
                System.out.println(current.p.toString());
                current = current.next;
            }
        }
    }

    public void draw() { // draw the tour to standard draw
        Node current = first;
        while (current != null) {
            current.p.draw();
            if (current.next != null) { // if it not the last node, draw a line to the next node
                current.p.drawTo(current.next.p);
            } else {
                current.p.drawTo(first.p); // if it is the last node, draw a line to the first node
            }
            current = current.next; // move to the next node
        }
    }

    public int size() { // number of points on tour
        return n;
    }

    public double distance() { // return the total distance of the tour
        Node current = first;
        double distance = 0;
        while (current.next != null) {
            distance += current.p.distanceTo(current.next.p);
            System.out.println(distance + " For Point " + n);
        }
        return distance;
    }

    public void insertNearest(Point p) { // insert p using nearest neighbor heuristic
        Node newNode = new Node();
        newNode.p = p;
        if (first == null) { // if the list is empty, set the first to the new node and return
            newNode.next = null;
            first = newNode;
            n++;
            return;
        }
        Node closest = first; // initialize the closest node to first
        double Dist = first.p.distanceTo(p); // same thing but with distance
        Node current = first.next; // move current forward
        while (current != null) { // traverse until the end of the list
            double d = current.p.distanceTo(p); // grab the new distance
            if (d < Dist) { // if the new distance is less then make it the closest
                Dist = d;
                closest = current;
            }
            current = current.next; // move to the next node
        }
        newNode.next = closest.next; // once the closest is confirmed insert the new node after it
        closest.next = newNode;
        n++;
    }

    public void insertSmallest(Point p) { // insert p using smallest increase heuristic

        Node newNode = new Node(); // the new node to be inserted
        newNode.p = p; // set the point of the new node to p

        if (first == null) { // if the list is empty add this new node
            newNode.next = null;
            first = newNode;
            n++;
            return;
        }

        Node step = new Node(); // the node that steps through the list
        Node bestPos = new Node(); // the node that is the best position to insert the newNode infront of
        double minDist = Double.MAX_VALUE; // this sets the minDist to the largest possible value so that any distance
                                           // will be smaller than it, it's not ideal as it's a lot of memory but it
                                           // works
        step = first;
        while (step != null) {
            if (step.next == null) { // if step is at the end of the list
                double dist = distance() - step.p.distanceTo(p); // checks the change from across the entire list
                                                                 // agienst oldDist which functions the same as minDist
                                                                 // but also uses the total
                double oldDist = distance() - minDist;
                if (dist < oldDist) { // checks if this distance is better
                    minDist = dist; // update the minimum distance
                    bestPos = step; // saves the best position to insert the newNode infront of
                }
            } else {
                double dist = step.p.distanceTo(p) + p.distanceTo(step.next.p) - step.p.distanceTo(step.next.p);
                if (dist < minDist) { // checks if this distance is better
                    minDist = dist; // update the minimum distance
                    bestPos = step; // saves the best position to insert the newNode infront of
                }
            }
            step = step.next; // move to the next node
        }

        newNode.next = bestPos.next; // set the next of the new node to the next of the best position
        bestPos.next = newNode; // set the next of the best position to the new
        n++; // increment the size of the tour

        /*
         * actual explanation for the dist calculation, basically instead of calling
         * distance() which would have to run through the entire list this instead
         * calulates
         * calculates just the new distance bewtween the three nodes, the first node
         * being what points to the new node and the second being the new node and the
         * third being where new node points
         * you subtract that original distance between the first and third so you will
         * get how much the distance increased by addding the new node, not just the new
         * distance
         */
    }
}
