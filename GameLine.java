package com.test.jfxgame;

public class GameLine {

    int column;
    int row;

//    enum LineType {
//        HORZ, VERT
//    }
    LineType type;

    boolean activated;

    GameLine(int column, int row, LineType type, boolean activated) {
        this.column = column;
        this.row = row;
        this.type = type;
        this.activated = false;
    }
}
