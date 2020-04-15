package com.jwere.swingy.model.character;

import com.jwere.swingy.model.artifact.Artifact;

public class Villain extends Character {

    private Artifact artifact;

    public Villain(String name, int attack, int defense, int hitPoints, Artifact artifact) {
        super(name, attack, defense, hitPoints);
        this.artifact = artifact;
    }

    public Artifact getArtifact() {
        return artifact;
    }
}