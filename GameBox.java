package com.test.jfxgame;

public class GameBox {

    int column;
    int row;

    boolean completed;

    BoxOwner boxOwner;

    enum BoxOwner {
        PLAYER1, PLAYER2, NONE
    }

//    GameBox(int column, int row, BoxOwner boxOwner, boolean completed) {
//        this.column = column;
//        this.row = row;
//        this.boxOwner = boxOwner;
//        this.completed = completed;
//    }
}
