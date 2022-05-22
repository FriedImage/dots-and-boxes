package com.test.jfxgame;

import javafx.scene.shape.Box;

import java.util.ArrayList;
import java.util.List;
public class GameLogic {
    
    private List<Box> boxs = new ArrayList<>();
    private List<GameLine> gameLines = new ArrayList<>();
    
    void runGame() {
            
    }
    
    GameLine findOrCreateGameLine(GameLine.LineType type, int column, int row) {
        gameLines.forEach(gameLine -> {
            if ((LineType.type == type) && (gameLine.column == column) && (gameLine.row == row)) {
                return gameLine;
            }
        });
        GameLine gameLine = new GameLine(column, row, type);
        gameLines.add(gameLine);
        return gameLine;
    }

    void populateBoard(int maxColumns, int maxRows) {
        for (int column = 0; column < maxColumns; column++) {
            for (int row = 0; row < maxRows; row++) {
                GameLine topLine = findOrCreateGameLine(GameLine.LineType.HORZ, column, row);
                GameLine bottomLine = findOrCreateGameLine(GameLine.LineType.HORZ, column, row + 1);
                GameLine leftLine = findOrCreateGameLine(GameLine.LineType.VERT, column, row);
                GameLine rightLine = findOrCreateGameLine(GameLine.LineType.VERT, column + 1, row);
                boxs.add(new Box(column, row, topLine, bottomLine, leftLine, rightLine));
            }
        }
    }
    
    void activateLine(enum type, int column, int row){
        
    }
    
}
