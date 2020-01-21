package sample.board;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import sample.util.CellType;
import sample.util.CursorType;

public class CustomCell extends Rectangle {
    public Point point;
    private Board board;
    private CellType cellType;
    private CursorType cursorType;

    private String pathColor = "white";
    private String wallColor = "black";
    private String sourceColor = "green";
    private String destinationColor = "red";
    private String visitedColor = "green";
    @FXML
    Label algorithmLabel;

    public CustomCell(int x, int y, CellType cellType, Board board) {
        super(board.grid.widthProperty().divide(board.boardSize + 1).doubleValue(), board.grid.heightProperty().divide(board.boardSize + 1).getValue());
//            double width =
//            double height =

        this.point = new Point(x, y);
        this.cellType = cellType;
        widthProperty().bind(board.grid.widthProperty().divide(board.boardSize + 1));
        heightProperty().bind(board.grid.heightProperty().divide(board.boardSize + 1));
        setFill(cellType);

    }

    public void setFill(CellType cellType) {
        switch (cellType) {
            case PATH: {
                this.cellType = CellType.PATH;
                this.setFill(Paint.valueOf(pathColor));
                break;
            }
            case WALL: {
                this.cellType = CellType.WALL;
                this.setFill(Paint.valueOf(wallColor));
                break;
            }
            case SOURCE: {
                this.cellType = CellType.SOURCE;
                this.setFill(Paint.valueOf(sourceColor));
                break;
            }
            case DESTINATION: {
                this.cellType = CellType.DESTINATION;
                this.setFill(Paint.valueOf(destinationColor));
                break;
            }
            case VISITED: {
                this.cellType = CellType.VISITED;
                this.setFill(Paint.valueOf(visitedColor));
                break;
            }
        }
    }

    public void setVisit() {
        this.cellType = CellType.VISITED;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public CellType getCellType() {
        return this.cellType;
    }


    public void setCursorType(CursorType cursorType) {
        this.cursorType = cursorType;
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setFill(cursorType);
                System.out.println("click");
            }
        });
    }

    private void setFill(CursorType cursorType) {
        switch (cursorType) {
            case PATH: {
                this.setFill(Paint.valueOf(pathColor));
                break;
            }
            case WALL: {
                this.setFill(Paint.valueOf(wallColor));
                break;
            }
            case SOURCE: {
                this.setFill(Paint.valueOf(sourceColor));
                break;
            }
            case DESTINATION: {
                this.setFill(Paint.valueOf(destinationColor));
                break;
            }
        }
    }


    //todo add label to cell
//        public void setLabel(CellType cellType) {
//            switch (cellType) {
//                case SOURCE: {
//                    set
//                }
//            }
//        }

}
