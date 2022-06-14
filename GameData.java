package com.test.jfxgame;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

// Every dynamic game data goes here ( moved from GameLogic )
public class GameData {

    List<GameBox> boxes = new ArrayList<>();
    final List<GameLine> gameLines = new ArrayList<>();

    final ObjectProperty<BoxOwner> activePlayer = new SimpleObjectProperty<>(BoxOwner.PLAYER1);
    final BooleanProperty gameOver = new SimpleBooleanProperty(false);
    IntegerProperty player1Score = new SimpleIntegerProperty(0);
    IntegerProperty player2Score = new SimpleIntegerProperty(0);

}
