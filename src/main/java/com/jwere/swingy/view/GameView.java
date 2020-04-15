package com.jwere.swingy.view;

import com.jwere.swingy.model.Game;
import com.jwere.swingy.model.Point;

public interface GameView {

    void start();

    void printMap(boolean[][] map, Point heroCoord);

    void update(Game game);

    void gameFinished();

    void showMessage(String message);

    void getVillainCollisionInput();

    boolean replaceArtifact(String replaceMessage);

    void switchView();
}
