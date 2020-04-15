package com.jwere.swingy.controller;
import com.jwere.swingy.exception.GameException;
import com.jwere.swingy.model.Game;
import com.jwere.swingy.model.character.Hero;
import com.jwere.swingy.model.character.HeroFactory;
import com.jwere.swingy.database.Database;
import com.jwere.swingy.view.CreateHeroView;

public class CreateHeroController {

    private CreateHeroView view;
    private Game game;

    public CreateHeroController(CreateHeroView view) {
        this.view = view;
        game = Game.getInstance();
    }

    public void onCreateButtonPressed(String name, String heroClass) {
        Hero hero;
        try {
            hero = HeroFactory.makeOrder(name, heroClass);
            hero.validateHero();
        } catch (IllegalArgumentException | GameException e) {
            view.showErrorMessage(e.getMessage());
            view.getUserInput();
            return;
        }
        //insert hero into database, set hero id to value returned by insert function.
        int id = Database.insert(hero.getName(), hero.getHeroClass(), hero.getLevel(), hero.getExperience(), hero.getAttack(), hero.getDefense(), hero.getHitPoints());
        hero.setId(id);
        game.initGame(hero);
        view.openGame();
    }
}