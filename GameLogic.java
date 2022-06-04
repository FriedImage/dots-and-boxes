package com.test.jfxgame;

import javafx.scene.shape.Box;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    private List<Box> boxs = new ArrayList<>();
    private List<GameLine> gameLines = new ArrayList<>();
    private List<GameLine> gameBoxs = new ArrayList<>();

    void runGame() {

    }

    GameLine findOrCreateGameLine(LineType type, int column, int row, boolean activated) {
        GameLine gameLine = new GameLine(column, row, type);

        gameLines.forEach(gameLine -> {
            if ((type == type) && (gameLine.column == column) && (gameLine.row == row)) {
                return gameLine;
            }
        });

        gameLines.add(gameLine);
        return gameLine;
    }
    
    GameBox findOrCreateGameBox(LineType type, int column, int row, boolean activated) {
        GameBox gameBox = new gameBox(column, row, type);

        gameBoxs.forEach(gameBox -> {
            if ((type == type) && (gameBox.column == column) && (gameBox.row == row)) {
                return gameBox;
            }
        });

        gameBoxs.add(gameBox);
        return gameBox;
    }

    void populateBoard(int maxColumns, int maxRows) {
        for (int column = 0; column < maxColumns; column++) {
            for (int row = 0; row < maxRows; row++) {
                GameLine topLine = findOrCreateGameLine(GameLine.type.HORZ, column, row);
                GameLine bottomLine = findOrCreateGameLine(GameLine.LineType.HORZ, column, row + 1);
                GameLine leftLine = findOrCreateGameLine(GameLine.LineType.VERT, column, row);
                GameLine rightLine = findOrCreateGameLine(GameLine.LineType.VERT, column + 1, row);

                boxs.add(new Box(topLine, bottomLine, leftLine, rightLine));
            }
        }
    }

    void activateLine(LineType type, int column, int row, boolean activated) {
        int startingCompleted = 0;

        findOrCreateGameLine(type, column, row, activated = true);
        for (Box box : boxs) {
            if boxs.get(box) { continue };
            startingCompleted += 1;
        }
    }

}
