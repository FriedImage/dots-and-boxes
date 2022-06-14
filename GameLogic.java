package com.test.jfxgame;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    private final List<GameLine> gameBoxes = new ArrayList<>();

    // init GameData
    GameData gameData = new GameData();

    long startingCompleted = 0;

    // testing run method ( console )
    void runGame() {
        // Setup Game ( testing )
        populateBoard(2, 2);

        gameData.activePlayer.set(BoxOwner.PLAYER1);
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

        for (GameBox gameBox : gameData.boxes) {
            System.out.println("Column: " + gameBox.getColumn() + " Row: " + gameBox.getRow() + " Completed: " + gameBox.isBoxComplete() + " Owner: " + gameBox.boxOwner);
        }

    }

    // GameLine method ( for-loop version )
    GameLine findOrCreateGameLine(LineType type, int column, int row) {
        for (GameLine line : gameData.gameLines) {
            if ((line.type.equals(type)) && (line.column == column) && (line.row == row)) {
                return line;
            }
        }
        GameLine newLine = new GameLine(column, row, type);
        newLine.activated.addListener(ob -> {
            System.out.println("activated? " + newLine.activated.getValue()); // testing purposes
            newLine.activated.get();
            handleActivatedLine();
        });
        gameData.gameLines.add(newLine);
        return newLine;
    }

    // populate board ( size values: maxColumns, maxRows )
    void populateBoard(int maxColumns, int maxRows) {
        for (int column = 0; column < maxColumns; column++) {
            for (int row = 0; row < maxRows; row++) {
                GameLine topLine = findOrCreateGameLine(LineType.HORZ, column, row);
                GameLine bottomLine = findOrCreateGameLine(LineType.HORZ, column, row + 1);
                GameLine leftLine = findOrCreateGameLine(LineType.VERT, column, row);
                GameLine rightLine = findOrCreateGameLine(LineType.VERT, column + 1, row);

                gameData.boxes.add(new GameBox(topLine, bottomLine, leftLine, rightLine));
            }
        }
    }

    // testing purpose
    private void playLine(LineType lineType, int column, int row) {
        System.out.println(gameData.activePlayer + " Type: " + lineType + " Column: " + column + " Row: " + row);
        System.out.println("Boxes completed: " + countCompletedBoxes2());
    }

    void handleActivatedLine() {
        assignCompletedBoxes();
        gameData.gameOver.set(countBoxes(BoxOwner.NONE) == 0); // sets if the game is over.
        gameData.player1Score.set(countBoxes(BoxOwner.PLAYER1));
        gameData.player2Score.set(countBoxes(BoxOwner.PLAYER2));

        if (countCompletedBoxes2() == startingCompleted) {
            flipCurrentPlayer();
        }

        startingCompleted = countCompletedBoxes2();
    }

    // assign completed boxes to the player
    private void assignCompletedBoxes() {
        for (GameBox gameBox : gameData.boxes) {
            if (gameBox.isBoxComplete() && (gameBox.boxOwner.get().equals(BoxOwner.NONE))) {
                gameBox.boxOwner.set(gameData.activePlayer.get());
            }
        }
    }

    // flip current turn
    private void flipCurrentPlayer() {
        if (gameData.activePlayer.get().equals(BoxOwner.PLAYER1)) {
            gameData.activePlayer.set(BoxOwner.PLAYER2);
        } else {
            gameData.activePlayer.set(BoxOwner.PLAYER1);
        }
    }

    private int countBoxes(BoxOwner typeToCount) {
        int results = 0;

        for (GameBox gameBox : gameData.boxes) {
            if (gameBox.boxOwner.get().equals(typeToCount)) {
                results++;
            }
        }
        return results;
    }

    // count current completed boxes ( stream-iteration version )
    private long countCompletedBoxes() {
        return gameData.boxes.stream()
                .filter(GameBox::isBoxComplete).count();
    }

    // count current completed boxes ( for loop )
    private long countCompletedBoxes2() {
        long count = 0;

        for (GameBox gameBox : gameData.boxes) {
            if (gameBox.isBoxComplete()) {
                count++;
//                System.out.println(cnt);
            }
        }

        return count;
    }

}
