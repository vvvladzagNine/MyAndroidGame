package ru.zagidev.sprites.characters;

import com.badlogic.gdx.graphics.Texture;

public class DuckGirlCharacter extends AbstractCharacter {
    static {
        texture = new Texture("data/duckGirl.png");
    }

    public DuckGirlCharacter(float x,float y) {
        init(x,y);
        speed=3;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        followTarget();

    }



}
