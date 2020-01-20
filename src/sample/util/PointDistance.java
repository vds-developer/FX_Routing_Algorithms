package sample.util;

import sample.board.Point;

public class PointDistance {
    private Point point;
    private int distance;
    public PointDistance(Point point, int distance) {
        this.point = point;
        this.distance = distance;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}