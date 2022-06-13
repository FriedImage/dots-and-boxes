package com.test.jfxgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
//import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Builder;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // create instance
        Region root = createContent(stage); // added stage
        Scene scene = new Scene(root);
        stage.setTitle("Dots and Boxes");
//        stage.setResizable(false);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    private Region createContent(Stage stage) { // has to require scene
//        BorderPane results = new BorderPane();
        VBox results = new VBox();

        Spinner<Integer> columnSpinner = new Spinner<>(2, 10, 3);
        Spinner<Integer> rowSpinner = new Spinner<>(2, 10, 3);

        Button startButton = new Button("Start Game");

        HBox bottomBox = new HBox(6, new Label("# of Columns: "), columnSpinner, new Label("# of Rows: "), rowSpinner, startButton);

        bottomBox.setPadding(new Insets(8));
        bottomBox.setAlignment(Pos.CENTER_LEFT);

        Label titleStatic = new Label("Dots and Boxes");
        HBox topBox = new HBox(titleStatic);
        titleStatic.setFont(new Font(30.0)); // title size
        topBox.setAlignment(Pos.CENTER);

        VBox dummyBox = new VBox();
        dummyBox.setMinHeight(200);
        VBox gameBox = new VBox(dummyBox);
        
        results.getChildren().addAll(topBox, gameBox, bottomBox);
        results.setMinHeight(600);
        stage.minHeightProperty().bind(gameBox.heightProperty().add(150)); // min self adjustable height
        stage.maxHeightProperty().bind(gameBox.heightProperty().add(150)); // max self adjustable height
        
        startButton.setOnAction(event -> {
            GameLogic gameLogic = new GameLogic();
            gameLogic.populateBoard(columnSpinner.getValue(), rowSpinner.getValue());
            Builder<Region> boardBuilder = new GameBoardBuilder(gameLogic.gameData);
            gameBox.getChildren().setAll(boardBuilder.build());
        });

        return results;
    }

    public static void main(String[] args) {
        launch(args);
//        new GameLogic().runGame(); - testing
    }
}
