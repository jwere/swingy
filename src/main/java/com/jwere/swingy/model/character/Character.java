package com.jwere.swingy.model.character;

import java.util.concurrent.ThreadLocalRandom;

import javax.validation.constraints.*;

public abstract class Character{
    @NotNull(message="name can't be null")
    @Size(min = 2, max = 16, message = "Name length must be greater than 2 snd less than 16")
    protected String name;
    @Min(value = 0, message = "Attack should not be less than 0")
    protected int attack;
    @Min(value = 0, message = "Defense should not be less than 0")
    protected int defense;
    @Min(value = 1, message = "Hit points should not be less than 1")
    protected int hitPoints;

    public Character(String name, int attack, int defense, int hitPoints) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
    }

    private void attack(Character opponent) {
        if (this.attack > opponent.defense) {
            opponent.setHitPoints(opponent.getHitPoints() - (this.attack - opponent.defense));
        } 
        // try luck
        // Returns a pseudorandom, uniformly distributed integer value between 0 and 10
        else if (ThreadLocalRandom.current().nextInt(0, 10) <= 2)
        {
            opponent.setHitPoints(opponent.getHitPoints() - this.attack);
        }
    }

    public boolean fight(Character opponent) {
        while (opponent.getHitPoints() > 0 && this.getHitPoints() > 0) {
            this.attack(opponent);
            opponent.attack(this);
        }
        return this.getHitPoints() > 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}