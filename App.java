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
        Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        scene = new Scene(root);
        stage.setTitle("game fx");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        launch(args);
        new GameLogic().runGame();
    }

}
