package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import sample.util.CellType;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AnimationTimeLine {
    private GridPane grid;
    private Board board;
    private List<Timeline> timelineList;
    private int duration = 100;

    public AnimationTimeLine(Board board) {
        this.grid = board.grid;
        this.board = board;
        this.timelineList = new LinkedList<>();
    }

    public void visit(int x, int y) {
        timelineList.add(new Timeline(
                new KeyFrame(Duration.millis(duration), event -> {
                    grid.add(new Cell(x, y, CellType.VISITED, board), x, y);
                })
        ));
    }

    public void autoPlay() {
        if (timelineList.isEmpty()) return;
        Iterator<Timeline> iterator = timelineList.iterator();
        Timeline current = iterator.next();
        Timeline next;
        current.play();
        while (iterator.hasNext()) {
            next = iterator.next();
            Timeline finalNext = next;
            current.setOnFinished(e -> finalNext.play());
            current = finalNext;
        }
    }
}
