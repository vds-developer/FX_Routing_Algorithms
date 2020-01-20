package sample.searchAlgorithms;

import sample.board.Board;
import sample.board.Point;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends AbstractSearch {

    // only 1 source
    // only 1 destination
    public BreadthFirstSearch(Board board) {
        super(board);
    }

    @Override
    public void search() {
        Queue<Point> pointLinkedList = new LinkedList<>();
        Queue<Point> destinationLinkedList = new LinkedList<>();
        for (Point p: this.sources) {
            pointLinkedList.add(p);
        }
        for (Point p: this.destination) {
            destinationLinkedList.add(p);
        }
        boolean found = false;
        while (!destinationLinkedList.isEmpty() && !pointLinkedList.isEmpty()) {
            Point currentPoint = pointLinkedList.remove();
            for (int[] n : neighbors) {
                int nextX = currentPoint.getX() + n[0];
                int nextY = currentPoint.getY() + n[1];
                if (isValidPoint(nextX, nextY) && !isVisited(nextX, nextY)) {
                    if (isDestination(nextX, nextY)) {
                        destinationLinkedList.remove();
                    }
                    pointLinkedList.add(new Point(nextX, nextY));
                    if (!isDestination(nextX, nextY)) visit(nextX, nextY);
                }
                System.out.println();
            }
        }
    }
}
