package gr.csihu.ntolias;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author George
 */
public class SettingsStageController implements Initializable {

    @FXML
    Label sizeLabel;

    @FXML
    Slider sizeSlider;

    @FXML
    TextField p1TextField;

    @FXML
    TextField p2TextField;

    private int size;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String p1, p2;

    public void startGame(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameStage.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // ti ston poutso
        scene = new Scene(root);
        stage.setTitle("DnB: " + Integer.toString(size) + "x" + Integer.toString(size) + " Game");
        stage.setScene(scene);
        stage.show();

        addNames();
        GameStageController gameStageController = loader.getController();
        gameStageController.showPane(size);
        gameStageController.displayNames(p1, p2);
    }

    public void addNames() {
        p1 = p1TextField.getText();
        p2 = p2TextField.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        size = (int) sizeSlider.getValue();
        sizeLabel.setText(Integer.toString(size) + "x" + Integer.toString(size) + " boxes");

        sizeSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {

                size = (int) sizeSlider.getValue();
                sizeLabel.setText(Integer.toString(size) + "x" + Integer.toString(size) + " boxes");

            }

        });
    }
}

//    Getters / Setters
//    public String getP1() {
//        return p1;
//    }
//    
//    public String getP2() {
//        return p2;
//    }
//    
//    public Integer getSize() {
//        return size;
//    }
//    
//    public void setP1(String name) {
//        this.p1 = name;
//    }
