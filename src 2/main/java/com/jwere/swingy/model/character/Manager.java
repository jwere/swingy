package com.jwere.swingy.model.character;

public class Manager{

    public static HeroBuilder buildNew(String name){
        HeroBuilder builder = new HeroBuilder();
        builder.setName(name);
        builder.setLevel(0);
        builder.setExperience(0);
        return builder;
    }

    public static Hero buildWarrior(String name){
        HeroBuilder builder = buildNew(name);
        builder.setAttack(40);
        builder.setDefense(35);
        builder.setHitPoints(100);
        builder.setHeroClass("Warrior");
        return builder.getHero();
    }

    public static Hero buildPriest(String name){
        HeroBuilder builder = buildNew(name);
        builder.setHeroClass("Priest");
        return builder.getHero();
    }

    public static Hero buildSuperman(String name){
        HeroBuilder builder = buildNew(name);
        builder.setHeroClass("Superman");
        return builder.getHero();
    }
}