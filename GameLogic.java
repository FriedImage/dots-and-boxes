package com.test.jfxgame;

//import javafx.scene.shape.Box;
import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    private List<GameBox> boxs = new ArrayList<>();
    private final List<GameLine> gameLines = new ArrayList<>();
    private final List<GameLine> gameBoxs = new ArrayList<>();

    BoxOwner activePlayer;

    void runGame() {

    }

    // new GameLine method
    GameLine findOrCreateGameLine(LineType type, int column, int row) {
        return gameLines.stream()
                .filter((GameLine gameLine) -> ((gameLine.type.equals(type)) && (gameLine.column == column) && (gameLine.row == row)))
                .findAny()
                .orElseGet(() -> {
                    GameLine gameLine = new GameLine(column, row, type);
                    gameLines.add(gameLine);
                    return gameLine;
                });
    }

    // something i've tried to do instead of streams ( doesn't work sadly )
//    GameLine findOrCreateGameLine(LineType type, int column, int row) {
//        return for(GameLine line : gameLines) {
//            if ((line.type.equals(type)) && (line.column == column) && (line.row == row)) {
//                gameLines.add(line);
//            } else {
//                GameLine line2 = new GameLine(column, row, type);
//                gameLines.add(line2);
//                return line2;
//            }
//        }
//    }
    // old GameLine method
//        GameLine gameLine = new GameLine(column, row, type);
//
//        gameLines.forEach(gameLine -> {
//            if ((type == type) && (gameLine.column == column) && (gameLine.row == row)) {
//                return gameLine;
//            }
//        });
//
//        gameLines.add(gameLine);
//        return gameLine;
    void populateBoard(int maxColumns, int maxRows) {
        for (int column = 0; column < maxColumns; column++) {
            for (int row = 0; row < maxRows; row++) {
                GameLine topLine = findOrCreateGameLine(LineType.HORZ, column, row);
                GameLine bottomLine = findOrCreateGameLine(LineType.HORZ, column, row + 1);
                GameLine leftLine = findOrCreateGameLine(LineType.VERT, column, row);
                GameLine rightLine = findOrCreateGameLine(LineType.VERT, column + 1, row);

                boxs.add(new GameBox(topLine, bottomLine, leftLine, rightLine));
            }
        }
    }

    void activateLine(LineType type, int column, int row, boolean activated) {
        long startingCompleted = countCompletedBoxes();

        GameLine gameLine = findOrCreateGameLine(type, column, row);
        gameLine.activated = true;

        if (countCompletedBoxes() == startingCompleted) {
            flipCurrentPlayer();
        }
    }

    private void flipCurrentPlayer() {
        if (this.activePlayer == BoxOwner.PLAYER1) {
            activePlayer = BoxOwner.PLAYER2;
        } else {
            activePlayer = BoxOwner.PLAYER1;
        }
    }

    private long countCompletedBoxes() {
        return boxs.stream()
                .filter(GameBox::isBoxComplete).count();
    }

}
