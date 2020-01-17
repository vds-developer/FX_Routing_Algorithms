package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import sample.searchAlgorithms.AbstractSearch;
import sample.searchAlgorithms.BreadthFirstSearch;
import sample.searchAlgorithms.DepthFirstSearch;

public class Controller {

    @FXML
    GridPane grid;

    private Board board;
    private final int defaultBoardSize = 20;
    private AbstractSearch search;

    @FXML
    public void initialize(){
        board = new Board(grid, defaultBoardSize);
        search = new BreadthFirstSearch(board);
//        search = new DepthFirstSearch(board);
        search.search();

    }

    public void randomizeBoard(ActionEvent actionEvent) {
        board.generateBoard();
//        search = new BreadthFirstSearch(board);
        search = new DepthFirstSearch(board);
        search.search();
    }

    public void startSearch(ActionEvent actionEvent) {

        search.autoPlay();
    }

    public void restartSearch(ActionEvent actionEvent) {
        board.restartBoard();
//        search = new BreadthFirstSearch(board);
        search = new DepthFirstSearch(board);
        search.search();
    }
}
