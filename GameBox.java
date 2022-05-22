package com.test.jfxgame;

import java.util.ArrayList;
import java.util.List;

public class GameBox {

    int column;
    int row;

    boolean completed;

    private List<GameLine> linesOfBox = new ArrayList<>();

    BoxOwner boxOwner;

    GameLine line1, line2, line3, line4;

//    enum BoxOwner {
//        PLAYER1, PLAYER2, NONE
//    }
    GameBox(int column, int row, BoxOwner boxOwner, boolean completed, List<GameLine> linesOfBox) {
        this.linesOfBox = linesOfBox.add(line1, line2, line3, line4);
        this.column = column;
        this.row = row;
        this.boxOwner = (BoxOwner) BoxOwner.NONE;
        this.completed = false;
    }
}
