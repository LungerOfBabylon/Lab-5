import edu.princeton.cs.algs4.*;

public class Tour {
    private Node first;

    private class Node {
        private Point p;
        private Node next;
    }

    public Tour() {
        first = null;
    }

    public void show(){
        Node current = first;

        while (current != null) {
            System.out.println(current.p);
            current = current.next;
        }
    }
    public void draw(){
        Node current = first;
        while (current != null){
            current.p.draw();
            if(current.next != null){
                current.p.drawTo(current.next.p);
            }
            current = current.next;
        }
    }

    public int size(){
        int count = 0;
        Node current = first;

        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public double distance(){
        Node current = first;
        double dist = 0;
        while(current != null){
            if(current.next == null){
                dist += 0;
            }else{
                dist += current.p.distanceTo(current.next.p);
            }
            current = current.next;
        }
        return dist;
    }

    public void insertNearest(Point p){
    
        Node x = new Node();
        x.p = p;
        if(first == null){
            first = x;
        }
        Node current = first;
        Node nearest = first;
        
        double currentDist = Double.POSITIVE_INFINITY;
        //double currentDist = first.p.distanceTo(first.next.p);
        
        while(current != null){
            if(currentDist >= current.p.distanceTo(p)){
                currentDist = current.p.distanceTo(p);
                nearest = current;
            }
            current = current.next;
        }
        Node hold = nearest.next;
        nearest.next = x;
        x.next = hold;
        
    }

    public void insertSmallest(Point p){
        Node x = new Node();
        x.p = p;
        if(first == null){
            first = x;
        }
        Node current = first;
        Node smallest = first;
        //double currentDist = first.p.distanceTo(first.next.p);
        double currentDist = Double.POSITIVE_INFINITY;

        while (current != null) {

        Node next = current.next;
        if (next == null) {
            next = first;
        }

        double d = current.p.distanceTo(p)+ p.distanceTo(next.p)- current.p.distanceTo(next.p);
        
        if(d<currentDist){
            currentDist = d;
            smallest = current;
        }
        current=current.next;

        }
        Node hold = smallest.next;
        smallest.next = x;
        x.next = hold;

    }
    public void insertInOrder(Point p){
        Node oldfirst = first;
        first = new Node();
        first.p = p;
        first.next = oldfirst;
        
    }
}
