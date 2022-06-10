package com.test.jfxgame;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class GameLine {

    int column;
    int row;

    LineType type;

    final BooleanProperty activated = new SimpleBooleanProperty(false);

    public BooleanProperty getActivatedProperty(GameLine line) {
        if (line.activated.get()) {
            return line.activated;
        }
        return null;
    }

    public void setActivatedProperty(boolean newActivated) {
        this.activated.set(newActivated);
    }

    GameLine(int column, int row, LineType type) {
        this.column = column;
        this.row = row;
        this.type = type;
        this.activated.set(false);
    }

}
