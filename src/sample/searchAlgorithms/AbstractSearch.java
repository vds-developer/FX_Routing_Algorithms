package sample.searchAlgorithms;

import javafx.scene.layout.GridPane;
import sample.board.Board;
import sample.board.Point;
import sample.util.CellType;

public abstract class AbstractSearch {

    protected Board board;
    protected Point[] sources;
    protected Point[] destination;
    protected GridPane grid;
    protected int size;
    protected CellType[][] modelBoard;
    private AnimationTimeLine  animationTimeLine;


    public abstract void search();

    public AbstractSearch(Board board) {
        this.board = board;
        this.sources = board.sources;
        this.destination = board.destination;
        this.grid = board.grid;
        this.size = board.boardSize;
        this.modelBoard = board.modelBoard;
        animationTimeLine = new AnimationTimeLine(board);
    }

    public void visit(Point point) {
       visit(point.getX(), point.getY());
    }
    public void visit(int x, int y) {
        animationTimeLine.visit(x,y);
        modelBoard[y][x] = CellType.VISITED;
    }

    public boolean isValidPoint(Point point) {
        return isValidPoint(point.getX(), point.getY());
    }

    public boolean isValidPoint(int x, int y) {
        return x < size && x >= 0
                && y < size && y >= 0
                && modelBoard[y][x] != CellType.WALL;
    }

    public boolean isVisited(Point point) {
        return isVisited(point.getX(), point.getY());
    }

    public boolean isVisited(int x, int y) {
        return CellType.VISITED == modelBoard[y][x];
//                && modelBoard[y][x] != CellType.SOURCE;

    }

    public boolean isDestination(Point point) {
        return isDestination(point.getX(), point.getY());
    }

    public boolean isDestination(int x, int y) {
        return CellType.DESTINATION == modelBoard[y][x];
    }

    public void autoPlay() {
        animationTimeLine.autoPlay();
    }

}
