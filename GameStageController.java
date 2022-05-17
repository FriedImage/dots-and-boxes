package gr.csihu.ntolias;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * FXML Controller class
 *
 * @author George
 */
public class GameStageController implements Initializable {

    @FXML
    AnchorPane twoXtwoPane, threeXthreePane, fourXfourPane, fiveXfivePane;

    @FXML
    Label p1Label, p2Label;

    @FXML
    Line line1, line2, line3, line4;

    boolean activatedProperty;

    int tempSize;
    String tempP1;
    String tempP2;

    @FXML
    Line[] arr = {line1, line2, line3, line4};
    private Line line;

    
    public Line getLine(Line line) {
        return this.line;
    }
    
    public void checkLine() {
       getLine(this.line).setStroke(Color.RED);
    }

    public void showPane(int size) {
        tempSize = size;
        switch (size) {
            case 2:
                twoXtwoPane.setDisable(false);
                twoXtwoPane.setVisible(true);
                break;
            case 3:
                threeXthreePane.setDisable(false);
                threeXthreePane.setVisible(true);
                break;
            case 4:
                fourXfourPane.setDisable(false);
                fourXfourPane.setVisible(true);
                break;
            case 5:
                fiveXfivePane.setDisable(false);
                fiveXfivePane.setVisible(true);
                break;
            default:
                twoXtwoPane.setDisable(false);
                twoXtwoPane.setVisible(true);
                break;
        }
    }

    public void displayNames(String p1, String p2) {
        tempP1 = p1;
        tempP2 = p2;
        p1Label.setText(p1);
        p2Label.setText(p2);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        
    }

}
