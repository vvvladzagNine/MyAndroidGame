package ru.zagidev.sprites.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

import ru.zagidev.world.Team;

public class DuckCharacter extends MeleeCharacter {
    static {
    }

    public DuckCharacter(float x,float y) {
        texture = new Texture("data/duck/fister/utya.png");
        deadTexture = new Texture("data/duck/fister/utyaDead.png");
        animationWalkTextures=new ArrayList<>();
        animationFightTextures=new ArrayList<>();
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag1.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag2.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag3.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag4.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag5.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag6.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag7.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag8.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag9.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag10.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag11.png"));
        sound = Gdx.audio.newSound(Gdx.files.internal("data/duck/sound/quack.mp3"));
        init(x,y);
    }
    public DuckCharacter(float x, float y, Team team) {
        texture = new Texture("data/duck/fister/utya.png");
        deadTexture = new Texture("data/duck/fister/utyaDead.png");
        animationWalkTextures=new ArrayList<>();
        animationFightTextures=new ArrayList<>();
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag1.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag2.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag3.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag4.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag5.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag6.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag7.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag8.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag9.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag10.png"));
        animationWalkTextures.add(new Texture("data/duck/fister/walkRight/utya_shag11.png"));
        sound = Gdx.audio.newSound(Gdx.files.internal("data/duck/sound/quack.mp3"));
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

