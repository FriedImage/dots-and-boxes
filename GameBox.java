package com.test.jfxgame;

import java.util.ArrayList;
import java.util.List;

public class GameBox {

    int column;
    int row;

    BoxOwner boxOwner;

    boolean completed;

    private final List<GameLine> sides = new ArrayList<>();

    // moved the enum to the BoxOwner class
//    private enum BoxOwner {
//        PLAYER1, PLAYER2, NONE
//    }
    public boolean isAtLocation(int column, int row) {
        return ((this.column == column) && (this.row == row));
    }

    public boolean isBoxComplete() {
//        return sides.containsAll(gameLine.activated); - doesn't work
        return sides.stream()
                .allMatch(gameLine -> gameLine.activated);
    }
    
    GameBox(GameLine line1, GameLine line2, GameLine line3, GameLine line4) {
        sides.addAll(List.of(line1, line2, line3, line4));
        this.boxOwner = BoxOwner.NONE;
        this.completed = false;
    }

}
