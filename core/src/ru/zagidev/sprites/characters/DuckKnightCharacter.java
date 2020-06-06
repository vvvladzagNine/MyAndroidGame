package ru.zagidev.sprites.characters;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import ru.zagidev.levels.GameLevel;
import ru.zagidev.sprites.characters.behaviors.MeleeAttack;
import ru.zagidev.world.Team;

public class DuckKnightCharacter extends KnightCharacter {

    public DuckKnightCharacter(float x, float y, Team team, GameLevel level) {

        this.level=level;
        texture = new Texture("data/duck/knight/utya.png");
        deadTexture = new Texture("data/duck/knight/utyaDead.png");
        deadTexture2 = new Texture("data/duck/knight/utya_zdokh2.png");

        //WalkRight
        animationWalkRightTextures = new ArrayList<>();
        for (int i = 1; i < 12; i++)
            animationWalkRightTextures.add(new Texture("data/duck/knight/walkRight/utya_shag" + i + ".png"));


        // Walk Down
        animationWalkDownTextures = new ArrayList<>();
        for (int i = 1; i < 9; i++)
            animationWalkDownTextures.add(new Texture("data/duck/knight/walkDown/utya_vpered" + i + ".png"));


        // Walk Up
        animationWalkUpTextures = new ArrayList<>();

        for (int i = 1; i < 10; i++)
            animationWalkUpTextures.add(new Texture("data/duck/knight/walkUp/utya_nazad" + i + ".png"));
        animationFightTextures = new ArrayList<>();


        adjustClass(x, y, team,"data/duck/sound/quack.mp3",1,1700,120,80);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        attack();

    }

}
