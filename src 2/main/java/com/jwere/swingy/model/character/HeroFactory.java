package com.jwere.swingy.model.character;

public class HeroFactory {
    public static Hero makeOrder(String name, String heroClass){
        switch (heroClass.toUpperCase()){
            case "WARRIOR":
                return Director.createWarrior(name);
            case "PRIEST":
                return Director.createPriest(name);
            case "PALADIN":
                return Director.createPaladin(name);
            case "HUNTER":
                return Director.createHunter(name);
            case "SHAMAN":
                return Director.createShaman(name);
            default:
            throw new IllegalArgumentException("Invalid hero class: " + heroClass);
        }
    }
}