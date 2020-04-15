package com.jwere.swingy.controller;

import com.jwere.swingy.exception.GameException;
import com.jwere.swingy.model.Game;
import com.jwere.swingy.model.character.Hero;
import com.jwere.swingy.database.Database;
import com.jwere.swingy.view.SelectHeroView;

import java.util.ArrayList;

public class SelectHeroController {

    private SelectHeroView view;
    private Game game;

    public SelectHeroController(SelectHeroView view) {
        this.view = view;
        game = Game.getInstance();
    }

    public void onListElementSelected(int id) {
        Hero hero = Database.selectHeroById(id + 1);
        view.updateInfo(hero.toString());
    }

    public String[] getListData() {
        ArrayList<String> list = Database.selectAll();
        String[] listArr = new String[list.size()];
        listArr = list.toArray(listArr);
        return listArr;
    }

    public void onSelectButtonPressed(int idx) {
        Hero hero;
        try {
            hero = Database.selectHeroById(idx + 1);
            hero.validateHero();
        } catch (GameException e) {
            view.showErrorMessage(e.getMessage());
            return;
        }

        game.initGame(hero);
        view.openGame();
    }

    public void onCreateButtonPressed() {
        view.openCreateHero();
    }
}
