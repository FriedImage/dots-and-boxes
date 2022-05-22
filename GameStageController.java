package com.test.jfxgame;

import com.test.jfxgame.GameLine.LineType;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Box;
import javafx.scene.shape.Line;

public class GameController implements Initializable {

    @FXML
    Line line1;

    @FXML
    Line line2;

    @FXML
    Line line3;

    @FXML
    Line line4;

    
    private List<Box> boxs = new ArrayList<>();
    private List<GameLine> gameLines = new ArrayList<>();
    
    GameLine findOrCreateGameLine(GameLine.LineType type, int column, int row) {
        gameLines.forEach(gameLine -> {
            if ()(gameLine.type == type) && (gameLine.column == column) && (gameLine.row == row) {
                return gameLine;
            }
        });
        GameLine gameLine = new GameLine(type, column, row);
        gameLines.add(gameLine);
        return gameLine;
    }

    void populateBoard(int maxColumns, int maxRows) {
        for (int column = 0; column < maxColumns; column++) {
            for (int row = 0; row < maxRows; row++) {
                GameLine topLine = findOrCreateGameLine(LineType.HORZ, column, row);
                GameLine bottomLine = findOrCreateGameLine(LineType.HORZ, column, row + 1);
                GameLine leftLine = findOrCreateGameLine(LineType.VERT, column, row);
                GameLine rightLine = findOrCreateGameLine(LineType.VERT, column + 1, row);
                boxs.add(new Box(column, row, topLine, bottomLine, leftLine, rightLine));
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
