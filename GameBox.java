package com.test.jfxgame;

import java.util.ArrayList;
import java.util.List;

public class GameBox {

    int column;
    int row;

    boolean completed;

    private final List<GameLine> sides = new ArrayList<>();

    private BoxOwner boxOwner;

    private GameLine line1, line2, line3, line4;

    private enum BoxOwner {
        PLAYER1, PLAYER2, NONE
    }

    public boolean isAtLocation(int column, int row) {
        return ((this.column == column) && (this.row == row));
    }

    GameBox(GameLine line1, GameLine line2, GameLine line3, GameLine line4, List<GameLine> sides) {
//        this.column = column;
//        this.row = row;
        this.sides = sides.addAll(line1, line2, line3, line4); // says I can't add stuff to final types which makes sense, so that's why I didn't make that list final.
        this.boxOwner = BoxOwner.NONE;
        this.completed = false;
    }

}
