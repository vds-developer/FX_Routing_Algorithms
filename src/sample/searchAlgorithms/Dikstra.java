package sample.searchAlgorithms;

import sample.board.Board;
import sample.board.Point;

public class Dikstra extends AbstractSearch {
    private int[][] distance;

    public Dikstra(Board board) {
        super(board);
        distance = new int[size][size];
    }

    @Override
    public void search() {
        for (Point point : this.sources) {
            distance[point.getY()][point.getX()] = 0;
        }
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                if (!isSource(col, row)) distance[row][col] = Integer.MAX_VALUE;
            }
        }
        while (true) {
            Point currentPoint = getMinDistance();
            if (isDestination(currentPoint)) {
                break;
            }
            visit(currentPoint);
            updateNeighborDistance(currentPoint);
        }
    }

    private void updateNeighborDistance(Point point) {
        if (point == null) return;
        int x = point.getX();
        int y = point.getY();
        for (int[] n : neighbors) {
            if (isValidPoint(x + n[1], y + n[0])
                    && !isVisited(x + n[1], y + n[0])
                    && distance[y][x] + 1 < distance[y + n[0]][x + n[1]]) {
                if (distance[y][x] == Integer.MAX_VALUE) return;
                distance[y + n[0]][x + n[1]] = distance[y][x] + 1;
            }
        }
    }

    private Point getMinDistance() {
        Point point = null;
        int min = Integer.MAX_VALUE;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if ((point == null || distance[row][col] < min)
                        && isValidPoint(col, row)
                        && !isVisited(col, row)) {
                    point = new Point(col, row);
                    min = distance[row][col];
                }
            }
        }
        return point;
    }
}
