package com.test.jfxgame;

import java.util.List;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableObjectValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Builder;

public class GameBoardBuilder implements Builder<Region> {

    private final List<GameLine> lines;
    private final List<GameBox> boxes;
    private final double lineLength = 100;

    private final double gap = 10;
    private final ObjectProperty<BoxOwner> activePlayerProperty;

    BooleanProperty gameOver;
    IntegerProperty player1Score;
    IntegerProperty player2Score;

    GameBoardBuilder(GameData gameData) { // putting GameBoardBuilder2 makes my IDE identify it as a method.
        this.lines = gameData.gameLines;
        this.boxes = gameData.boxes;
        this.activePlayerProperty = gameData.activePlayer;
        this.gameOver = gameData.gameOver;
        this.player1Score = gameData.player1Score;
        this.player2Score = gameData.player2Score;
    }

    @Override
    public Region build() {
        Pane pane = new Pane();

        // line loop
        lines.forEach(gameLine -> {
            Line line = createLine(gameLine);
            pane.getChildren().add(line);
        });

        // box loop
        boxes.forEach(gameBox -> {
            Rectangle box = createBox(gameBox);
            pane.getChildren().add(box);
        });

        Label currentPlayerStatic = new Label("Current Player: ");
        Label currentPlayer = new Label(); // new label
        currentPlayer.textProperty().bind(activePlayerProperty.asString());

        Label player = new Label();
        player.textProperty().bind(new GameOverBinding(player1Score, player2Score));
        player.setFont(new Font(20.0));
        
        Label staticWins = new Label("Wins!");
        staticWins.setFont(new Font(40.0));
        
        VBox gameOverBox = new VBox(player, staticWins);
        gameOverBox.setAlignment(Pos.CENTER);
        
        Label staticPlayer1Score = new Label("Player 1 Score: ");
        Label staticPlayer2Score = new Label("Player 2 Score: ");
        
        Label player1ScoreLabel = new Label("0");
        Label player2ScoreLabel = new Label("0");
        
        player1ScoreLabel.textProperty().bind(player1Score.asString());
        player2ScoreLabel.textProperty().bind(player2Score.asString());
        HBox scoreP1 = new HBox(5, staticPlayer1Score, player1ScoreLabel);
        HBox scoreP2 = new HBox(5, staticPlayer2Score, player2ScoreLabel);
        VBox scores = new VBox(5, scoreP1, scoreP2);
        
        HBox players = new HBox(5, currentPlayerStatic, currentPlayer);
        VBox gameBoard = new VBox(10, pane, players, scores);
        StackPane results = new StackPane(gameBoard, gameOverBox);

        gameBoard.visibleProperty().bind(gameOver.not());
        gameOverBox.visibleProperty().bind(gameOver);

        gameBoard.setPadding(new Insets(30));
        return results;
    }

    private Line createLine(GameLine gameLine) { // putting "Node" as the return type is an error since we want it to return Line.
        if (gameLine.type.equals(LineType.HORZ)) {
            Line horizLine = new Line();

            horizLine.strokeProperty().bind(new LineColorBinding(gameLine.activated));
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

            vertLine.strokeProperty().bind(new LineColorBinding(gameLine.activated));
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

    private Rectangle createBox(GameBox gameBox) {
        Rectangle rBox = new Rectangle();
        rBox.fillProperty().bind(new BoxColorBinding(gameBox.boxOwner));

        double x1 = (gameBox.getColumn() * (lineLength + gap) + (gap));
        double y1 = (gameBox.getRow() * (lineLength + gap) + (gap));

        double height = lineLength - gap;
        double width = lineLength - gap;

        rBox.setHeight(height);
        rBox.setWidth(width);

        rBox.setX(x1);
        rBox.setY(y1);

        return rBox;
    }

    class LineColorBinding extends ObjectBinding {

        ObservableBooleanValue check;

        public LineColorBinding(ObservableBooleanValue check) {
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

    // testing for Boxes
    class BoxColorBinding extends ObjectBinding {

        ObservableObjectValue check;

        public BoxColorBinding(ObservableObjectValue check) {
            super.bind(check);
            this.check = check;
        }

        @Override
        protected Color computeValue() {
            if (check.get().equals(BoxOwner.PLAYER1)) {
                return Color.RED;
            } else if (check.get().equals(BoxOwner.PLAYER2)) {
                return Color.GREEN;
            } else {
                return Color.YELLOW;
            }
        }

    }

    class GameOverBinding extends StringBinding {

        IntegerProperty p1Score;
        IntegerProperty p2Score;

        public GameOverBinding(IntegerProperty p1Score, IntegerProperty p2Score) {
            super.bind(p1Score, p2Score);
            this.p1Score = p1Score;
            this.p2Score = p2Score;
        }

        @Override
        protected String computeValue() {
            if (p1Score.greaterThan(p2Score).get()) {
                return "Player 1";
            } else if (p2Score.greaterThan(p1Score).get()) {
                return "Player 2";
            } else {
                return "Nobody";
            }
        }
    }

}
