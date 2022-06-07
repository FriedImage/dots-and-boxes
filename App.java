package com.test.jfxgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // run game
        new GameLogic().runGame();
        // setup stage
        Parent root = FXMLLoader.load(getClass().getResource("Game.fxml")); // fxml file for SceneBuilder
        scene = new Scene(root);
        stage.setTitle("game fx");
        stage.setScene(scene);
        stage.show(); // vbox already initialized in SceneBuilder (Game.fxml)
    }

    public static void main(String[] args) {
        launch(args);
//        new GameLogic().runGame(); - testing
    }

}
