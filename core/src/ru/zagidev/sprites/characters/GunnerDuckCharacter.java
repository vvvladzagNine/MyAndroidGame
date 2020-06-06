package ru.zagidev.sprites.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import ru.zagidev.levels.GameLevel;
import ru.zagidev.sprites.characters.behaviors.RangeAttack;
import ru.zagidev.world.Team;

public class GunnerDuckCharacter extends RangeCharacter {
    static {
    }



    public GunnerDuckCharacter(float x, float y, Team team, GameLevel level) {
        this.level=level;
        texture = new Texture("data/duck/gunner/utya_gun.png");
        deadTexture = new Texture("data/duck/fister/utyaDead.png");
        deadTexture2 = new Texture("data/duck/fister/utya_zdokh2.png");
        animationFightTextures = new ArrayList<>();

        animationWalkRightTextures = new ArrayList<>();
        for (int i = 1; i < 11; i++)
            animationWalkRightTextures.add(new Texture("data/duck/gunner/walkRight/Strelok_utka_sh"+i+".png"));

        //Walk DOwn
        animationWalkDownTextures = new ArrayList<>();
        for (int i = 1; i < 9; i++)
            animationWalkDownTextures.add(new Texture("data/duck/gunner/walkDown/Strelok_utya_vpered"+i+".png"));


        adjustClass(x, y, team,"data/duck/sound/quack.mp3",2,600,100,85);


    }

    @Override
    public void act(float delta) {
        super.act(delta);
        attack();

    }
}
