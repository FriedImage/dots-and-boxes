package com.test.jfxgame;

import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Builder;

public class GameBoardBuilder implements Builder<Region> {

    private final List<GameLine> lines;
    private final double lineLength = 200;
    private final double gap = 20;

    GameBoardBuilder(List<GameLine> lines) { // putting GameBoardBuilder2 makes my IDE identify it as a method.
        this.lines = lines;
    }

    @Override
    public Region build() {
        Pane pane = new Pane();

        lines.forEach(gameLine -> {
            Line line = createLine(gameLine);
            pane.getChildren().add(line);
        });

        VBox results = new VBox(10, pane);
        results.setPadding(new Insets(30));
        return results;
    }

    private Line createLine(GameLine gameLine) { // putting "Node" as the return type is an error since we want it to return Line.
        gameLine.setActivatedProperty(true);

        if (gameLine.type.equals(LineType.HORZ)) {
            Line horzLine = new Line();

            horzLine.setStroke(Color.GREY);
            horzLine.setStrokeWidth(4);

            double x1 = gameLine.column * (lineLength + gap) + (gap / 2);
            double x2 = x1 + lineLength;

            double y1 = gameLine.row * (lineLength + gap);
            double y2 = y1;

            // x coordinate
            horzLine.setStartX(x1);
            horzLine.setEndX(x2);

            // y coordinate
            horzLine.setStartY(y1);
            horzLine.setEndY(y2);

            return horzLine;
        } else {
            Line vertLine = new Line();

            vertLine.setStroke(Color.GREY);
            vertLine.setStrokeWidth(4);

            double x1 = gameLine.column * (lineLength + gap);
            double x2 = x1;

            double y1 = gameLine.row * (lineLength + gap) + (gap / 2);
            double y2 = y1 + lineLength;

            // x coordinate
            vertLine.setStartX(x1);
            vertLine.setEndX(x2);

            // y coordinate
            vertLine.setStartY(y1);
            vertLine.setEndY(y2);

            return vertLine;
        }

    }

}
