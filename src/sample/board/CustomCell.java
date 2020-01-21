package sample.board;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
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
    private String sourceColor = "blue";
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
        this.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(compareCellTypeCursorType()) newValue = false;
                pseudoClassStateChanged(getHoverClass(), newValue);
            }
        });
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(compareCellTypeCursorType()) setFill(CellType.PATH);
                else setFill(cursorType);
                System.out.println(cursorType);
                System.out.println("click");
                event.consume();
            }
        });
    }

    private boolean compareCellTypeCursorType () {
        return cellType.toString() == cursorType.toString();
    }

    private PseudoClass getHoverClass () {
        switch(cursorType) {
            case DESTINATION: {
                return PseudoClass.getPseudoClass("cursorDestination");
            } case PATH: {
                return PseudoClass.getPseudoClass("cursorPath");
            } case WALL: {
                return PseudoClass.getPseudoClass("cursorWall");
            } case SOURCE: {
                return PseudoClass.getPseudoClass("cursorSource");
            }
        }
        return null;
    }

    private void setFill(CursorType cursorType) {
        switch (cursorType) {
            case PATH: {
                setFill(CellType.PATH);
                break;
            }
            case WALL: {
                setFill(CellType.WALL);
                break;
            }
            case SOURCE: {
                setFill(CellType.SOURCE);
                break;
            }
            case DESTINATION: {
                setFill(CellType.DESTINATION);
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
