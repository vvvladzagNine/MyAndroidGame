package ru.zagidev.sprites.characters;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import ru.zagidev.levels.GameLevel;
import ru.zagidev.world.Team;

public class DuckCharacter extends MeleeCharacter {
    static {
    }


    public DuckCharacter(float x, float y, Team team, GameLevel level) {

        this.level=level;
        texture = new Texture("data/duck/fister/utya.png");
        deadTexture = new Texture("data/duck/fister/utyaDead.png");
        deadTexture2 = new Texture("data/duck/fister/utya_zdokh2.png");

        //WalkRight
        animationWalkRightTextures = new ArrayList<>();
        for (int i = 1; i < 12; i++)
            animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag" + i + ".png"));


        // Walk Down
        animationWalkDownTextures = new ArrayList<>();
        for (int i = 1; i < 9; i++)
            animationWalkDownTextures.add(new Texture("data/duck/fister/walkDown/utya_vpered" + i + ".png"));


        // Walk Up
        animationWalkUpTextures = new ArrayList<>();

        for (int i = 1; i < 10; i++)
            animationWalkUpTextures.add(new Texture("data/duck/fister/walkUp/utya_nazad" + i + ".png"));
        animationFightTextures = new ArrayList<>();


        adjustClass(x, y, team,"data/duck/sound/quack.mp3",2,1000,100,50);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        attack();

    }


}

