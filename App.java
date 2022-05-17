 package gr.projects.dotsandboxes;

// Imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage menuStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menuStage.fxml"));
        Scene scene = new Scene(root);

        // Stage settings (before loadfxml)
        menuStage.setResizable(false);
        Image icon = new Image("icon.png");
        menuStage.getIcons().add(icon);
        menuStage.setTitle("DnB: Main Menu");
        menuStage.setScene(scene);
        menuStage.show();
    }

    protected static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
