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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.util.Builder;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // create instance
        Region root = testingRegion();
        Scene scene = new Scene(root);
        stage.setTitle("game fx");
        stage.setScene(scene);
        stage.show();
    }

    final double maxFontSize = 30.0;

    private Region testingRegion() {
        BorderPane results = new BorderPane();
        
        Spinner<Integer> columnSpinner = new Spinner<>(2, 10, 3);
        Spinner<Integer> rowSpinner = new Spinner<>(2, 10, 3);
        
        Button startButton = new Button("Start Game");
        startButton.setOnAction(event -> {
            GameLogic gameLogic = new GameLogic();
            gameLogic.populateBoard(columnSpinner.getValue(), rowSpinner.getValue());
            Builder<Region> boardBuilder = new GameBoardBuilder(gameLogic.gameData.gameLines, gameLogic.gameData.boxes, gameLogic.gameData.activePlayer);
            results.setCenter(boardBuilder.build());
        });

        HBox bottomBox = new HBox(6, new Label("# of Columns: "), columnSpinner, new Label("# of Rows: "), rowSpinner, startButton);

        bottomBox.setPadding(new Insets(8));
        bottomBox.setAlignment(Pos.CENTER_LEFT);

        Label titleStatic = new Label("Dots and Boxes");
        HBox topBox = new HBox(titleStatic);
        titleStatic.setFont(new Font(maxFontSize));
        topBox.setAlignment(Pos.CENTER);

        results.setTop(topBox);
        results.setBottom(bottomBox);
        results.setMinHeight(600);

        return results;
    }

    public static void main(String[] args) {
        launch(args);
//        new GameLogic().runGame(); - testing
    }
}
