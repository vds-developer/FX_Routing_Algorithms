package sample.util;

import sample.board.Point;

import java.util.Comparator;

public class PointComparator<e> implements Comparator<PointDistance> {

    @Override
    public int compare(PointDistance o1, PointDistance o2) {
        return o1.getDistance() - o2.getDistance();
    }
}
