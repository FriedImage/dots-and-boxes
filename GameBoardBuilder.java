package com.test.jfxgame;

import java.util.List;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableObjectValue;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
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
        if (gameLine.type.equals(LineType.HORZ)) {
            Line horizLine = new Line();

            horizLine.strokeProperty().bind(new CustomBooleanBinding(gameLine.activated));
            horizLine.setStrokeWidth(4);

            double x1 = gameLine.column * (lineLength + gap) + (gap / 2);
            double x2 = x1 + lineLength;

            double y1 = gameLine.row * (lineLength + gap);
            double y2 = y1;

            // x coordinate
            horizLine.setStartX(x1);
            horizLine.setEndX(x2);

            // y coordinate
            horizLine.setStartY(y1);
            horizLine.setEndY(y2);

            // event
            horizLine.setOnMouseClicked((MouseEvent event) -> {
                gameLine.activated.set(true);
            });

            return horizLine;
        } else {
            Line vertLine = new Line();

            vertLine.strokeProperty().bind(new CustomBooleanBinding(gameLine.activated));
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

            // event
            vertLine.setOnMouseClicked((MouseEvent event) -> {
                gameLine.activated.set(true);
            });
            
            return vertLine;
        }

    }

    class CustomBooleanBinding extends ObjectBinding {

        // me messing up with custom bindings, trying to figure it out
        ObservableBooleanValue check;

        public CustomBooleanBinding(ObservableBooleanValue check) {
            super.bind(check);
            this.check = check;
        }

        @Override
        protected Color computeValue() {
            if (check.get()) {
                return Color.BLUE;
            } else {
                return Color.GREY;
            }
        }

    }

}
