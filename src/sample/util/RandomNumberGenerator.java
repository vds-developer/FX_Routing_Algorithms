package sample.util;

import sample.board.Point;

import java.util.Random;

public class RandomNumberGenerator {
    private static Random random = new Random();

    // return 1 with probability of probability
    public static CellType generateWallPath(double probability) {
        double ranDouble = random.nextDouble();
        return ranDouble < probability? CellType.WALL : CellType.PATH;
    }

    //n is lenght of board
    public static Point generatePoint(int n) {
        return new Point(random.nextInt(n), random.nextInt(n));
    }
}
