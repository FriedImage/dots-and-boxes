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
        // Setup Game
        populateBoard(2, 2);

        activePlayer = BoxOwner.PLAYER1;
        playLine(LineType.VERT, 0, 0);
        playLine(LineType.HORZ, 0, 0);
        playLine(LineType.VERT, 1, 1);
        playLine(LineType.HORZ, 0, 2);
        playLine(LineType.VERT, 2, 0);
        playLine(LineType.HORZ, 1, 1);
        playLine(LineType.HORZ, 1, 2);
        playLine(LineType.VERT, 2, 1);
        playLine(LineType.HORZ, 1, 0);
        playLine(LineType.VERT, 1, 0);
        playLine(LineType.HORZ, 0, 1);
        playLine(LineType.VERT, 0, 1);

        for (GameBox gameBox : boxs) {
            System.out.println("Column: " + gameBox.getColumn() + " Row: " + gameBox.getRow() + " Completed: " + gameBox.isBoxComplete() + " Owner: " + gameBox.boxOwner);
        }

    }

    // GameLine method ( stream-iteration version )
//    GameLine findOrCreateGameLine(LineType type, int column, int row) {
//        return gameLines.stream()
//                .filter((GameLine gameLine) -> ((gameLine.type.equals(type)) && (gameLine.column == column) && (gameLine.row == row)))
//                .findAny()
//                .orElseGet(() -> {
//                    GameLine gameLine = new GameLine(column, row, type);
//                    gameLines.add(gameLine);
//                    return gameLine;
//                });
//    }
    // new GameLine method ( for-loop version )
    GameLine findOrCreateGameLine(LineType type, int column, int row) {
        for (GameLine line : gameLines) {
            if ((line.type.equals(type)) && (line.column == column) && (line.row == row)) {
                return line;
            }
        }
        GameLine newLine = new GameLine(column, row, type);
        gameLines.add(newLine);
        return newLine;
    }

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

    private void playLine(LineType lineType, int column, int row) {
        System.out.println(activePlayer + " Type: " + lineType + " Column: " + column + " Row: " + row);
        activateLine(lineType, column, row);
        System.out.println("Boxes completed: " + countCompletedBoxes());
    }

    void activateLine(LineType type, int column, int row) {
        long startingCompleted = countCompletedBoxes();

        GameLine gameLine = findOrCreateGameLine(type, column, row);
        gameLine.activated = true;

        if (countCompletedBoxes() == startingCompleted) {
            flipCurrentPlayer();
        }
    }

    private void assignCompletedBoxes() {
        for (GameBox gameBox : boxs) {
            if (gameBox.isBoxComplete() && (gameBox.boxOwner.equals(BoxOwner.NONE))) {
                gameBox.boxOwner = activePlayer;
            }
        }
    }

    private void flipCurrentPlayer() {
        if (this.activePlayer == BoxOwner.PLAYER1) {
            activePlayer = BoxOwner.PLAYER2;
        } else {
            activePlayer = BoxOwner.PLAYER1;
        }
    }

    // count completed boxes ( stream-iteration version )
    private long countCompletedBoxes() {
        return boxs.stream()
                .filter(GameBox::isBoxComplete).count();
    }

    // count completed boxes ( for-loop version - dont know if it works )
//    private long countCompletedBoxes() {
//        long cnt = 0;
//        for (GameBox gameBox : boxs) {
//            if (gameBox.completed) {
//                ++cnt;
//            }
//        }
//        return cnt;
//    }
}
