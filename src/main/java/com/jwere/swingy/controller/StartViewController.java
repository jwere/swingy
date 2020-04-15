package com.jwere.swingy.controller;

import com.jwere.swingy.view.StartView;

public class StartViewController {

    private StartView view;

    public StartViewController(StartView view) {
        this.view = view;
    }

    public void onCreateHeroButtonPressed() {
        view.openCreateHero();
    }

    public void onSwitchButtonPressed() {
        view.switchView();
    }

    public void onSelectHeroButtonPressed() {
        view.openSelectHero();
    }
}