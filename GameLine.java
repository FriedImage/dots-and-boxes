package com.test.jfxgame;

public class GameLine {

    int column;
    int row;

    LineType type;

    boolean activated;

    public boolean getActivatedProperty() {
        return activated;
    }

    public void setActivatedProperty(boolean newActivated) {
        this.activated = newActivated;
    }

    GameLine(int column, int row, LineType type) {
        this.column = column;
        this.row = row;
        this.type = type;
        this.activated = false;
    }

}
