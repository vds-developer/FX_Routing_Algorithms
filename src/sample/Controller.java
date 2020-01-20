package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sample.board.Board;
import sample.board.Cell;
import sample.searchAlgorithms.AbstractSearch;
import sample.searchAlgorithms.BreadthFirstSearch;
import sample.searchAlgorithms.DepthFirstSearch;
import sample.searchAlgorithms.Dikstra;
import sample.util.CursorType;
import sample.util.SearchAlgorithm;

import javax.swing.*;
import java.io.FileInputStream;

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
        cursorType = CursorType.NORMAL;
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
                algorithmLabel.setText("Dikstra Search");
                break;
            }
        }
    }

    public void setCursorType(CursorType cursorType) {
        this.cursorType = cursorType;
        switch (this.cursorType) {
            case PATH: {
//                mainView.setCursor();
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

    public void setDikstra(ActionEvent actionEvent) {
        currentSearchAlgorithm = SearchAlgorithm.Dikstra;
        restartSearch(null);
    }

    public void setCursorMouse(MouseEvent mouseEvent) {
    }
}
