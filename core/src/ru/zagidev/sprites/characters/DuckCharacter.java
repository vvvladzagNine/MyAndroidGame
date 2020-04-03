package ru.zagidev.sprites.characters;

import com.badlogic.gdx.graphics.Texture;

import ru.zagidev.world.Team;

public class DuckCharacter extends MeleeCharacter {
    static {
        texture = new Texture("data/utya.png");
    }

    public DuckCharacter(float x,float y) {

        init(x,y);
    }
    public DuckCharacter(float x, float y, Team team) {
        init(x,y,team);
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        attack();

    }



}

