package ru.zagidev.sprites.characters;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import ru.zagidev.levels.GameLevel;
import ru.zagidev.world.Team;

public class PigeonCharacter extends MeleeCharacter {
    static {


    }



    public PigeonCharacter(float x, float y, Team team, GameLevel level) {
        this.level=level;
        texture = new Texture("data/pigeon/fister/ugolub.png");
        deadTexture = new Texture("data/pigeon/fister/golub_umer1.png");
        deadTexture2 = new Texture("data/pigeon/fister/golub_umer2.png");


        // Walk Down
        animationWalkDownTextures = new ArrayList<>();
        for (int i = 1; i < 9; i++)
            animationWalkDownTextures.add(new Texture("data/pigeon/fister/walkDown/golub_vpered"+i+".png"));


        //Walk Right
        animationWalkRightTextures = new ArrayList<>();
        for (int i = 1; i < 11; i++)
            animationWalkRightTextures.add(new Texture("data/pigeon/fister/walkRight/golub_shag"+i+".png"));


        // Walk Up
        animationWalkUpTextures = new ArrayList<>();
        for (int i = 1; i < 9; i++)
            animationWalkUpTextures.add(new Texture("data/pigeon/fister/walkUp/golub_nazad"+i+".png"));


        animationFightTextures = new ArrayList<>();


        adjustClass(x, y, team,"data/pigeon/pigeon.mp3",2,1000,100);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        attack();

    }


}
