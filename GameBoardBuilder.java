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
import javafx.scene.Node;
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
    private final double lineLength = 35;

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

        double maxX = 0;
        double maxY = 0;

        // add all lines to Pane loop
        for (GameLine gameLine : lines) {
            Line line = createLine(gameLine);
            Node rectangle = createRectangle(gameLine);
            pane.getChildren().addAll(rectangle, line);
            maxX = Math.max(maxX, line.getEndX());
            maxY = Math.max(maxY, line.getEndY());
        }

        pane.setMinSize(maxX + gap, maxY + gap);

        // add all boxes to Pane loop
        boxes.forEach(gameBox -> {
            Rectangle box = createBox(gameBox);
            pane.getChildren().add(box);
        });

        pane.setMinSize(maxX + 5, maxY + 5); // automatically change min pane size by line size + 5px
        pane.setMaxSize(maxX + 5, maxY + 5); // automatically change max pane size by line size + 5px

        Label player1Indicator = new Label("Current Player -> ");
        Label player2Indicator = new Label("Current Player -> ");
        player1Indicator.visibleProperty().bind(activePlayerProperty.isEqualTo(BoxOwner.PLAYER1));
        player2Indicator.visibleProperty().bind(activePlayerProperty.isEqualTo(BoxOwner.PLAYER2));

        Label player = new Label();
        player.textProperty().bind(new WinningPlayerBinding(player1Score, player2Score));
        player.setFont(new Font(20.0));

        Label staticWins = new Label("Wins!");
        staticWins.setFont(new Font(40.0));

        VBox gameOverBox = new VBox(player, staticWins);

        Label staticPlayer1Score = new Label("Player 1: ");
        Label staticPlayer2Score = new Label("Player 2: ");

        Label player1ScoreLabel = new Label("0");
        Label player2ScoreLabel = new Label("0");

        player1ScoreLabel.textProperty().bind(player1Score.asString()); // bind to PLAYER1 score
        player2ScoreLabel.textProperty().bind(player2Score.asString()); // bind to PLAYER2 score
        HBox scoreP1 = new HBox(5, player1Indicator, staticPlayer1Score, player1ScoreLabel);
        HBox scoreP2 = new HBox(5, player2Indicator, staticPlayer2Score, player2ScoreLabel);

        VBox scores = new VBox(5, scoreP1, scoreP2);

        VBox gameBoard = new VBox(10, new StackPane(pane), scoreP1, scoreP2, scores); // added StackPane(pane) to center elements of pane

        StackPane results = new StackPane(gameBoard, gameOverBox);

        scoreP1.setAlignment(Pos.CENTER);
        scoreP2.setAlignment(Pos.CENTER);
        gameOverBox.setAlignment(Pos.CENTER);

        // debug
//        pane.setStyle("-fx-border-color: red;");
//        scoreP1.setStyle("-fx-border-color: yellow");
//        scoreP2.setStyle("-fx-border-color: orange");
//        gameBoard.setStyle("-fx-border-color: blue;");
//        results.setStyle("-fx-border-color: magenta");
        gameBoard.visibleProperty().bind(gameOver.not()); // GameBoard VBox active while gameOver false
        gameOverBox.visibleProperty().bind(gameOver); // GameOver VBox not active while gameOver false

        gameBoard.setPadding(new Insets(30));
        return results;
    }

    // specs of each line created ( as Line )
    private Line createLine(GameLine gameLine) { // putting "Node" as the return type is an error since we want it to return Line.
        Line line = new Line();
        line.strokeProperty().bind(new LineColorBinding(gameLine.activated));
        line.setStrokeWidth(3);

        // activation event
        line.setOnMouseClicked((MouseEvent event) -> {
            gameLine.activated.set(true);
        });

        double x1;
        double x2;

        double y1;
        double y2;

        if (gameLine.type.equals(LineType.HORZ)) {

            x1 = gameLine.column * (lineLength + gap) + (gap / 2);
            x2 = x1 + lineLength;

            y1 = gameLine.row * (lineLength + gap);
            y2 = y1;

        } else {

            x1 = gameLine.column * (lineLength + gap);
            x2 = x1;

            y1 = gameLine.row * (lineLength + gap) + (gap / 2);
            y2 = y1 + lineLength;

        }

        // set x coordinate
        line.setStartX(x1);
        line.setEndX(x2);

        // set y coordinate
        line.setStartY(y1);
        line.setEndY(y2);

        return line;
    }

    // optional hitbox lines ( fat rectangles )
    private Node createRectangle(GameLine gameLine) {
        double x1;
        double y1;

        double width;
        double height;

        if (gameLine.type.equals(LineType.HORZ)) {
            x1 = gameLine.column * (lineLength + gap) + (gap / 2);
            y1 = gameLine.row * (lineLength + gap) - gap;

            width = lineLength;
            height = gap * 2;
        } else {
            x1 = gameLine.column * (lineLength + gap) - gap;
            y1 = gameLine.row * (lineLength + gap) + (gap / 2);

            width = gap * 2;
            height = lineLength;
        }

        Rectangle rectangle = new Rectangle(x1, y1, width, height);
        rectangle.setStroke(Color.web("F4F4F4")); // hide rectangle
        rectangle.setFill(Color.web("F4F4F4")); // hide rectangle
        rectangle.setStrokeWidth(1);
        rectangle.setOnMouseClicked((MouseEvent event) -> {
            gameLine.activated.set(true);
        });

        return rectangle;
    }

    // specs of each box created ( as Rectangle )
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

    // binds color of lines depending if a player clicked a line
    class LineColorBinding extends ObjectBinding {

        ObservableBooleanValue check;

        public LineColorBinding(ObservableBooleanValue check) {
            super.bind(check);
            this.check = check;
        }

        @Override
        protected Color computeValue() {
            if (check.get()) {
                return Color.BLACK;
            } else {
                return Color.LIGHTGREY;
            }
        }

    }

    // binds color of boxes depending who scored it
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
                return Color.web("F4F4F4");
            }
        }

    }

    // binds player text, depending who won
    class WinningPlayerBinding extends StringBinding {

        IntegerProperty p1Score;
        IntegerProperty p2Score;

        public WinningPlayerBinding(IntegerProperty p1Score, IntegerProperty p2Score) {
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
