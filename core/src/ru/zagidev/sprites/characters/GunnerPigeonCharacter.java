package ru.zagidev.sprites.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import ru.zagidev.levels.GameLevel;
import ru.zagidev.sprites.characters.behaviors.RangeAttack;
import ru.zagidev.world.Team;

public class GunnerPigeonCharacter extends RangeCharacter {

    public GunnerPigeonCharacter(float x, float y, Team team, GameLevel level) {
        this.level=level;
        texture = new Texture("data/pigeon/gunner/golub_gun.png");
        deadTexture = new Texture("data/pigeon/fister/golub_umer1.png");
        deadTexture2 = new Texture("data/pigeon/fister/golub_umer2.png");
        animationFightTextures = new ArrayList<>();


        //WalkRight
        animationWalkRightTextures = new ArrayList<>();
        for (int i = 1; i < 8; i++)
            animationWalkRightTextures.add(new Texture("data/pigeon/gunner/walkRight/strelok_shag"+i+".png"));

        //Walk DOwn
        animationWalkDownTextures = new ArrayList<>();
        for (int i = 1; i < 9; i++)
            animationWalkDownTextures.add(new Texture("data/pigeon/gunner/walkDown/strelok_vpered"+i+".png"));

        //Walk Up
        animationWalkUpTextures = new ArrayList<>();
        for (int i = 1; i < 9; i++)
            animationWalkUpTextures.add(new Texture("data/pigeon/gunner/walkUp/strelok_nazad"+i+".png"));




        adjustClass(x, y, team,"data/pigeon/pigeon.mp3",2,600,100,85);
        attackBehavior.attackSound=AbstractCharacter.shot2;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        attack();

    }
}
