package gr.projects.dotsandboxes;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToHowToPlay(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("howToPlayStage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // ti ston poutso
        scene = new Scene(root);
        stage.setTitle("DnB: How to Play");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSettings(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("settingsStage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // ti ston poutso
        scene = new Scene(root);
        stage.setTitle("DnB: Settings");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("menuStage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // ti ston poutso
        scene = new Scene(root);
        stage.setTitle("DnB: Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("gameStage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // ti ston poutso
        scene = new Scene(root);
        stage.setTitle("DnB: Game");

        stage.setScene(scene);
        stage.show();
    }
}
