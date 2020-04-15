package com.jwere.swingy.view;


public interface CreateHeroView {

    void start();

    void getUserInput();

    void showErrorMessage(String message);

    void openGame();
}