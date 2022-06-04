package com.test.jfxgame;

public class GameLine {

    int column;
    int row;

    LineType type;

    enum LineType {
        HORZ, VERT
    }

    boolean activated;

    GameLine(int column, int row, LineType type, boolean activated) {
        this.column = column;
        this.row = row;
        this.type = type;
        this.activated = false;
    }

    public boolean getActivatedProperty() {
        return activated;
    }

    public void setActivatedProperty(boolean newActivated) {
        this.activated = newActivated;
    }
}
