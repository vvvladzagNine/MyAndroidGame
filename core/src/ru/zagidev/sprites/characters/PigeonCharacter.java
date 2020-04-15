package ru.zagidev.sprites.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import ru.zagidev.world.Team;

public class PigeonCharacter extends MeleeCharacter {
    static {


    }

    public PigeonCharacter(float x, float y) {
        texture = new Texture("data/pigeon/fister/ugolub.png");
        deadTexture = new Texture("data/pigeon/fister/golub_umer1.png");
        deadTexture2 = new Texture("data/pigeon/fister/golub_umer2.png");

        animationWalkRightTextures =new ArrayList<>();
        animationFightTextures=new ArrayList<>();
        sound = Gdx.audio.newSound(Gdx.files.internal("data/pigeon/pigeon.mp3"));

        init(x,y);
        speed=2;
    }


    public PigeonCharacter(float x, float y, Team team) {
        texture = new Texture("data/pigeon/fister/ugolub.png");
        deadTexture = new Texture("data/pigeon/fister/golub_umer1.png");
        deadTexture2 = new Texture("data/pigeon/fister/golub_umer2.png");


        // Walk Down
        animationWalkDownTextures = new ArrayList<>();
        animationWalkDownTextures.add(new Texture("data/pigeon/fister/walkDown/golub_vpered1.png"));
        animationWalkDownTextures.add(new Texture("data/pigeon/fister/walkDown/golub_vpered2.png"));
        animationWalkDownTextures.add(new Texture("data/pigeon/fister/walkDown/golub_vpered3.png"));
        animationWalkDownTextures.add(new Texture("data/pigeon/fister/walkDown/golub_vpered4.png"));
        animationWalkDownTextures.add(new Texture("data/pigeon/fister/walkDown/golub_vpered5.png"));
        animationWalkDownTextures.add(new Texture("data/pigeon/fister/walkDown/golub_vpered6.png"));
        animationWalkDownTextures.add(new Texture("data/pigeon/fister/walkDown/golub_vpered7.png"));
        animationWalkDownTextures.add(new Texture("data/pigeon/fister/walkDown/golub_vpered8.png"));

        //Walk Right
        animationWalkRightTextures = new ArrayList<>();
        animationWalkRightTextures.add(new Texture("data/pigeon/fister/walkRight/golub_shag1.png"));
        animationWalkRightTextures.add(new Texture("data/pigeon/fister/walkRight/golub_shag2.png"));
        animationWalkRightTextures.add(new Texture("data/pigeon/fister/walkRight/golub_shag3.png"));
        animationWalkRightTextures.add(new Texture("data/pigeon/fister/walkRight/golub_shag4.png"));
        animationWalkRightTextures.add(new Texture("data/pigeon/fister/walkRight/golub_shag5.png"));
        animationWalkRightTextures.add(new Texture("data/pigeon/fister/walkRight/golub_shag6.png"));
        animationWalkRightTextures.add(new Texture("data/pigeon/fister/walkRight/golub_shag7.png"));
        animationWalkRightTextures.add(new Texture("data/pigeon/fister/walkRight/golub_shag8.png"));
        animationWalkRightTextures.add(new Texture("data/pigeon/fister/walkRight/golub_shag9.png"));
        animationWalkRightTextures.add(new Texture("data/pigeon/fister/walkRight/golub_shag10.png"));

        // Walk Up
        animationWalkUpTextures = new ArrayList<>();
        animationWalkUpTextures.add(new Texture("data/pigeon/fister/walkUp/golub_nazad1.png"));
        animationWalkUpTextures.add(new Texture("data/pigeon/fister/walkUp/golub_nazad2.png"));
        animationWalkUpTextures.add(new Texture("data/pigeon/fister/walkUp/golub_nazad3.png"));
        animationWalkUpTextures.add(new Texture("data/pigeon/fister/walkUp/golub_nazad4.png"));
        animationWalkUpTextures.add(new Texture("data/pigeon/fister/walkUp/golub_nazad5.png"));
        animationWalkUpTextures.add(new Texture("data/pigeon/fister/walkUp/golub_nazad6.png"));
        animationWalkUpTextures.add(new Texture("data/pigeon/fister/walkUp/golub_nazad7.png"));
        animationWalkUpTextures.add(new Texture("data/pigeon/fister/walkUp/golub_nazad8.png"));

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
