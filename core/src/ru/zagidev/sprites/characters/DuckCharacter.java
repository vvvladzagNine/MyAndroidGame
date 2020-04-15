package ru.zagidev.sprites.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import ru.zagidev.world.Team;

public class DuckCharacter extends MeleeCharacter {
    static {
    }

    public DuckCharacter(float x, float y) {
        texture = new Texture("data/duck/fister/utya.png");
        deadTexture = new Texture("data/duck/fister/utyaDead.png");
        deadTexture2 = new Texture("data/duck/fister/utya_zdokh2.png");
        animationFightTextures = new ArrayList<>();

        //Walk Right
        animationWalkRightTextures = new ArrayList<>();
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag1.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag2.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag3.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag4.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag5.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag6.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag7.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag8.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag9.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag10.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag11.png"));

        // Walk Down
        animationWalkDownTextures = new ArrayList<>();
        animationWalkDownTextures.add(new Texture("data/duck/fister/walkDown/utya_vpered1.png"));
        animationWalkDownTextures.add(new Texture("data/duck/fister/walkDown/utya_vpered2.png"));
        animationWalkDownTextures.add(new Texture("data/duck/fister/walkDown/utya_vpered3.png"));
        animationWalkDownTextures.add(new Texture("data/duck/fister/walkDown/utya_vpered4.png"));
        animationWalkDownTextures.add(new Texture("data/duck/fister/walkDown/utya_vpered5.png"));
        animationWalkDownTextures.add(new Texture("data/duck/fister/walkDown/utya_vpered6.png"));
        animationWalkDownTextures.add(new Texture("data/duck/fister/walkDown/utya_vpered7.png"));
        animationWalkDownTextures.add(new Texture("data/duck/fister/walkDown/utya_vpered8.png"));

        sound = Gdx.audio.newSound(Gdx.files.internal("data/duck/sound/quack.mp3"));
        init(x, y);
    }

    public DuckCharacter(float x, float y, Team team) {
        texture = new Texture("data/duck/fister/utya.png");
        deadTexture = new Texture("data/duck/fister/utyaDead.png");
        deadTexture2 = new Texture("data/duck/fister/utya_zdokh2.png");

        // Walk Down
        animationWalkDownTextures = new ArrayList<>();
        animationWalkDownTextures.add(new Texture("data/duck/fister/walkDown/utya_vpered1.png"));
        animationWalkDownTextures.add(new Texture("data/duck/fister/walkDown/utya_vpered2.png"));
        animationWalkDownTextures.add(new Texture("data/duck/fister/walkDown/utya_vpered3.png"));
        animationWalkDownTextures.add(new Texture("data/duck/fister/walkDown/utya_vpered4.png"));
        animationWalkDownTextures.add(new Texture("data/duck/fister/walkDown/utya_vpered5.png"));
        animationWalkDownTextures.add(new Texture("data/duck/fister/walkDown/utya_vpered6.png"));
        animationWalkDownTextures.add(new Texture("data/duck/fister/walkDown/utya_vpered7.png"));
        animationWalkDownTextures.add(new Texture("data/duck/fister/walkDown/utya_vpered8.png"));



        // Walk Up
        animationWalkUpTextures = new ArrayList<>();
        animationWalkUpTextures.add(new Texture("data/duck/fister/walkUp/utya_nazad1.png"));
        animationWalkUpTextures.add(new Texture("data/duck/fister/walkUp/utya_nazad2.png"));
        animationWalkUpTextures.add(new Texture("data/duck/fister/walkUp/utya_nazad3.png"));
        animationWalkUpTextures.add(new Texture("data/duck/fister/walkUp/utya_nazad4.png"));
        animationWalkUpTextures.add(new Texture("data/duck/fister/walkUp/utya_nazad5.png"));
        animationWalkUpTextures.add(new Texture("data/duck/fister/walkUp/utya_nazad6.png"));
        animationWalkUpTextures.add(new Texture("data/duck/fister/walkUp/utya_nazad7.png"));
        animationWalkUpTextures.add(new Texture("data/duck/fister/walkUp/utya_nazad8.png"));
        animationWalkUpTextures.add(new Texture("data/duck/fister/walkUp/utya_nazad9.png"));

        // Walk Right
        animationWalkRightTextures = new ArrayList<>();
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag1.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag2.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag3.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag4.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag5.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag6.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag7.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag8.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag9.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag10.png"));
        animationWalkRightTextures.add(new Texture("data/duck/fister/walkRight/utya_shag11.png"));


        animationFightTextures = new ArrayList<>();


        sound = Gdx.audio.newSound(Gdx.files.internal("data/duck/sound/quack.mp3"));
        init(x, y, team);
        speed = 2;
        damage = 100;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        attack();

    }


}

