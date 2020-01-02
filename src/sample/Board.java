package sample;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import sample.util.CellType;
import sample.util.RandomNumberGenerator;

//import java.awt.*;


// on the board red is wall,
public class Board {

    public GridPane grid;
    public int boardSize;
    private static  final double wallProbability = 0.2;
    public Point[] sources;
    public Point[] destination;
    // 0 is path, 1 is wall, 2 is source, 3 is destination, 4 is visited
    public CellType[][] modelBoard;

    public Board( GridPane grid, int n) {
        this.grid = grid;
        this.boardSize = n;
        this.modelBoard = new CellType[n][n];
        grid.setGridLinesVisible(true);
        for (int i = 0; i < n; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100.0 / n);
            col.setHalignment(HPos.CENTER);
            grid.getColumnConstraints().add(col);

            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / n);
            row.setValignment(VPos.CENTER);
            grid.getRowConstraints().add(row);
        }
        generateBlankBoard();
    }

        public void generateBoard() {
            generateBoard(1, 1, wallProbability);
        }

        public void generateBlankBoard() {
            generateBoard(0, 0, 0);
        }

        public void generateBoard(int numberOfSources, int numberOfDestination, double wallProbability) {
            for (int row = 0; row < boardSize; row++ ) {
                for (int col = 0; col < boardSize; col++) {
                    CellType type = RandomNumberGenerator.generateWallPath(wallProbability);
                    grid.add(new Cell(col, row, type, this), col, row);
                    modelBoard[row][col] = type;
                }
            }
            //todo can possible land on same destination
            sources = new Point[numberOfSources];
            for (int i = 0; i < numberOfSources; i++) {
                Point point  = RandomNumberGenerator.generatePoint(boardSize);
                sources[i] = point;
                grid.add(new Cell(point.getX(), point.getY(), CellType.SOURCE, this), point.getX(), point.getY());
                modelBoard[point.getY()][point.getX()] = CellType.SOURCE;
            }
            destination = new Point[numberOfDestination];
            for (int i = 0; i < numberOfDestination; i++) {
                Point point  = RandomNumberGenerator.generatePoint(boardSize);
                destination[i] = point;
                grid.add(new Cell(point.getX(), point.getY(), CellType.DESTINATION, this), point.getX(), point.getY());
                modelBoard[point.getY()][point.getX()] = CellType.DESTINATION;
            }
        }

    public void restartBoard() {
        generateBoard();
    }



}
