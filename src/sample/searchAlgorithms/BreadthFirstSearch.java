package sample.searchAlgorithms;

import sample.Board;
import sample.Point;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearch extends AbstractSearch {

    // only 1 source
    // only 1 destination
    public BreadthFirstSearch(Board board) {
        super(board);
    }

    @Override
    public void search() {
//        Point startPoint = this.sources[0];
//        int currentX = startPoint.getX();
//        int currentY = startPoint.getY();
        Queue<Point> pointLinkedList = new LinkedList<>();
        Queue<Point> destinationLinkedList = new LinkedList<>();
        for (Point p: this.sources) {
            pointLinkedList.add(p);
        }
        for (Point p: this.destination) {
            destinationLinkedList.add(p);
        }
        ///y x
        System.out.println("Destination size " + destinationLinkedList.size());
        System.out.println("Current list " + pointLinkedList.size());
        System.out.println();
        int[][] neighbors = new int[][] {{-1, -1} , {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0,1}, {1, -1}, {1, 0}, {1, 1}};
        boolean found = false;
        while (!destinationLinkedList.isEmpty() && !pointLinkedList.isEmpty()) {
            Point currentPoint = pointLinkedList.remove();
            for (int[] n : neighbors) {
                int nextX = currentPoint.getX() + n[0];
                int nextY = currentPoint.getY() + n[1];
                if (isValidPoint(nextX, nextY) && !isVisited(nextX, nextY)) {
                    if (isDestination(nextX, nextY)) {
                        System.out.println("to be remove");
                        destinationLinkedList.remove();
                    }
                    pointLinkedList.add(new Point(nextX, nextY));
                    if (!isDestination(nextX, nextY)) visit(nextX, nextY);
                }
//                System.out.println("Destination size " + destinationLinkedList.size());
//                System.out.println("Current list " + pointLinkedList.size());
                System.out.println();
            }
        }
    }
}
