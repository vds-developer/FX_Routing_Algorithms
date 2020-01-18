package sample.searchAlgorithms;

import sample.board.Board;
import sample.board.Point;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstSearch extends AbstractSearch {
    public DepthFirstSearch(Board board) {
        super(board);
    }

    @Override
    public void search() {
        Stack<Point> pointStack = new Stack<Point>();
        Queue<Point> destinationLinkedList = new LinkedList<>();
        for (Point p: this.sources) {
            pointStack.add(p);
        }
        for (Point p: this.destination) {
            destinationLinkedList.add(p);
        }

        int[][] neighbours  = new int [][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, -1}};

        while (!destinationLinkedList.isEmpty() && !pointStack.isEmpty()) {
            Point currentPoint = pointStack.pop();
            int currentX = currentPoint.getX();
            int currentY = currentPoint.getY();
            if (!isVisited(currentPoint) && !isDestination(currentPoint)) visit(currentPoint);

            if(isDestination(currentPoint)) {
                destinationLinkedList.remove();
            }
            int nextX = -1;
            int nextY = -1;
            for (int[] pair: neighbours) {
                if(isValidPoint(currentX + pair[0], currentY + pair[1]) &&
                !isVisited(currentX + pair[0], currentY + pair[1])){
                    nextX = currentX + pair[0];
                    nextY = currentY + pair[1];
                    pointStack.add(new Point(nextX, nextY));
                }
            }


        }
    }
}
