package sample.searchAlgorithms;

import sample.board.Board;
import sample.board.Point;

public class GreedySearch extends AbstractSearch {
    public GreedySearch(Board board) {
        super(board);
    }


    //only one destination
    @Override
    public void search() {
        if (this.destination.length == 0 || this.sources.length == 0) return;
        Point destination = this.destination[0];
        Point currentPoint = this.sources[0];
        boolean stuck = false;
        while (!stuck) {
                if (currentPoint.getX() < destination.getX()
                        && isValidAndNotVisited(currentPoint.getX() + 1, currentPoint.getY())) {
                    currentPoint = new Point(currentPoint.getX() + 1, currentPoint.getY());
                } else if (currentPoint.getX() > destination.getX()
                        && isValidAndNotVisited(currentPoint.getX() - 1, currentPoint.getY())) {
                    currentPoint = new Point(currentPoint.getX() - 1, currentPoint.getY());
                } else if (currentPoint.getY() < destination.getY()
                        && isValidAndNotVisited(currentPoint.getX(), currentPoint.getY() + 1)) {
                    currentPoint = new Point(currentPoint.getX(), currentPoint.getY() + 1);
                } else if (currentPoint.getY() > destination.getY()
                        && isValidAndNotVisited(currentPoint.getX(), currentPoint.getY() - 1)) {
                    currentPoint = new Point(currentPoint.getX(), currentPoint.getY() - 1);
                } else {
                    stuck = true;
                    for (int [] n : neighbors) {
                        if (isValidAndNotVisited(currentPoint.getX() + n[0], currentPoint.getY() +n[1])) {
                            currentPoint = new Point(currentPoint.getX() + n[0], currentPoint.getY() +n[1]);
                            stuck = false;
                            break;
                        } else if (isValidPoint(currentPoint.getX() + n[0], currentPoint.getY() +n[1])){
                            currentPoint = new Point(currentPoint.getX() + n[0], currentPoint.getY() +n[1]);
                            stuck = false;
                            break;
                        }
                    }
                    if (stuck){
                        System.out.println("Stuck");
                        break;
                    }
                }
                if(isDestination(currentPoint)) break;
                visit(currentPoint.getX() , currentPoint.getY());
        }
    }
}
