package ru.zagidev.sprites.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import ru.zagidev.world.Team;

public class PigeonCharacter extends MeleeCharacter {
    static {


    }

    public PigeonCharacter(float x, float y) {
        texture = new Texture("data/ugolub.png");
        deadTexture = new Texture("data/ugolubDead.png");
        animationWalkTextures=new ArrayList<>();
        animationFightTextures=new ArrayList<>();
        sound = Gdx.audio.newSound(Gdx.files.internal("data/pigeon/pigeon.mp3"));

        init(x,y);
        speed=2;
    }


    public PigeonCharacter(float x, float y, Team team) {
        texture = new Texture("data/ugolub.png");
        deadTexture = new Texture("data/ugolubDead.png");
        animationWalkTextures=new ArrayList<>();
        animationFightTextures=new ArrayList<>();
        sound = Gdx.audio.newSound(Gdx.files.internal("data/pigeon/pigeon.mp3"));
        init(x,y,team);
        speed=2;
        damage=100;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        attack();

    }



}
