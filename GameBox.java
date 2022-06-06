package com.test.jfxgame;

import java.util.ArrayList;
import java.util.List;

public class GameBox {

    int column;
    int row;

    BoxOwner boxOwner;

    boolean completed;

    private final List<GameLine> sides = new ArrayList<>();

    public int getColumn() {
        for (GameLine line : sides) {
            if (line.type.equals(LineType.HORZ)) {
                return line.column;
            }
        }
        return 0;
    }
    
    public int getRow() {
        for (GameLine line : sides) {
            if (line.type.equals(LineType.VERT)) {
                return line.row;
            }
        }
        return 0;
    }

    public boolean isAtLocation(int column, int row) {
        return ((this.column == column) && (this.row == row));
    }

    // Box completion check ( stream-iteration version )
//    public boolean isBoxComplete() {
////        return sides.containsAll(gameLine.activated); - doesn't work
//        return sides.stream()
//                .allMatch(gameLine -> gameLine.activated);
//    }
    // Box completion check ( for-loop version )
    public boolean isBoxComplete() {
        for (GameLine line : sides) {
            if (!line.activated) {
                return false;
            }
        }
        return true;
    }

    GameBox(GameLine line1, GameLine line2, GameLine line3, GameLine line4) {
        sides.addAll(List.of(line1, line2, line3, line4));
        this.boxOwner = BoxOwner.NONE;
        this.completed = false;
    }

}
