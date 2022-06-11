package com.test.jfxgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // create instance
        GameLogic gameLogic = new GameLogic();
        gameLogic.populateBoard(3, 3);
        Builder<Region> boardBuilder = new GameBoardBuilder(gameLogic.gameLines, gameLogic.boxs); // error

        Scene scene = new Scene(boardBuilder.build());
        stage.setTitle("game fx");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
//        new GameLogic().runGame(); - testing
    }

}
