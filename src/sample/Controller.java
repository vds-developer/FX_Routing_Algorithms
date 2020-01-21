package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sample.board.Board;
import sample.searchAlgorithms.AbstractSearch;
import sample.searchAlgorithms.BreadthFirstSearch;
import sample.searchAlgorithms.DepthFirstSearch;
import sample.searchAlgorithms.Dikstra;
import sample.util.CellType;
import sample.util.CursorType;
import sample.util.SearchAlgorithm;

public class Controller {

    @FXML
    GridPane grid;

    @FXML
    Label algorithmLabel;

    @FXML
    SplitPane mainView;

    SearchAlgorithm currentSearchAlgorithm;

    private Board board;
    private final int defaultBoardSize = 10;
    private AbstractSearch search;
    private CursorType cursorType;

    @FXML
    public void initialize(){
        board = new Board(grid, defaultBoardSize);
        currentSearchAlgorithm = SearchAlgorithm.BreadthFirst;
        cursorType = CursorType.PATH;
        setSearchAlgorithm(currentSearchAlgorithm);
        ObservableList<Node> child = grid.getChildren();
        for(Node node : child) {
            node.getStyleClass().clear();
        }
    }

    public void randomizeBoard(ActionEvent actionEvent) {
        board.generateBoard();
        setSearchAlgorithm(currentSearchAlgorithm);
    }

    public void startSearch(ActionEvent actionEvent) {
        search.search();
        search.autoPlay();
    }

    public void restartSearch(ActionEvent actionEvent) {
        board.restartBoard();
        setSearchAlgorithm(currentSearchAlgorithm);
    }

    public void setSearchAlgorithm(SearchAlgorithm algorithm) {
        switch (algorithm) {
            case DepthFirst: {
                search = new DepthFirstSearch(board);
                algorithmLabel.setText("Depth First Search");
                break;
            }case BreadthFirst: {
                search = new BreadthFirstSearch(board);
                algorithmLabel.setText("Breadth First Search");
                break;
            } case Dikstra: {
                search = new Dikstra(board);
                algorithmLabel.setText("Dijkstra Search");
                break;
            }
        }
    }

    private static PseudoClass wall = PseudoClass.getPseudoClass("cursorWall");
    public void setCursorType(CursorType cursorType) {
        this.cursorType = cursorType;
        setCursor();
//        grid.hoverProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                grid.pseudoClassStateChanged(getHoverClass(), newValue);
//            }
//        });
//        grid.pseudoClassStateChanged(getHoverClass(), true);
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

    public void setCursor() {
        for (int row = 0; row < defaultBoardSize; row++){
            for (int col = 0; col < defaultBoardSize; col++) {
                board.modelBoard[row][col].setCursorType(this.cursorType);
//                board.modelBoard[row][col].setFill(CellType.SOURCE);
            }
        }
    }


    public void setBreadthFirst(ActionEvent actionEvent) {
        currentSearchAlgorithm = SearchAlgorithm.BreadthFirst;
        restartSearch(null);
    }

    public void setDepthFirst(ActionEvent actionEvent) {
        currentSearchAlgorithm = SearchAlgorithm.DepthFirst;
        restartSearch(null);
    }

    public void setDijkstra(ActionEvent actionEvent) {
        currentSearchAlgorithm = SearchAlgorithm.Dikstra;
        restartSearch(null);
    }

    public void addWall(MouseEvent mouseEvent) {
        setCursorType(CursorType.WALL);
    }

    public void addDestination(MouseEvent mouseEvent) {
        setCursorType(CursorType.DESTINATION);
    }

    public void addSource(MouseEvent mouseEvent) {
        setCursorType(CursorType.SOURCE);
    }
}
