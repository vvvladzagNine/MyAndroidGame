package ru.zagidev.sprites.characters;

import com.badlogic.gdx.graphics.Texture;

import ru.zagidev.world.Team;

public class DuckGirlCharacter extends MeleeCharacter {
    static {
        texture = new Texture("data/ugolub.png");
    }

    public DuckGirlCharacter(float x,float y) {
        init(x,y);
        speed=4;
    }


    public DuckGirlCharacter(float x, float y, Team team) {

        init(x,y,team);
        speed=4;
        currentHealth=600;
        damage=40;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        attack();

    }



}
