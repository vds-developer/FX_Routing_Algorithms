package sample.board;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import sample.util.CellType;
    public class Cell extends Rectangle {
        public Point point;
        private Board board;
        private CellType cellType;

        private String pathColor = "white";
        private String wallColor = "black";
        private String sourceColor = "green";
        private String destinationColor = "red";
        private String visitedColor = "green";

        public Cell(int x, int y, CellType cellType, Board board) {
            super(board.grid.widthProperty().divide(board.boardSize + 1).doubleValue(), board.grid.heightProperty().divide(board.boardSize + 1).getValue());
//            double width =
//            double height =

            this.point = new Point(x, y);
            this.cellType = cellType;
            widthProperty().bind(board.grid.widthProperty().divide(board.boardSize + 1));
            heightProperty().bind(board.grid.heightProperty().divide(board.boardSize + 1));
            setFill(cellType);
        }

        public void setFill(CellType cellType){
            switch (cellType) {
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
                case VISITED: {
                    this.setFill(Paint.valueOf(visitedColor));
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
