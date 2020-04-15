package ru.zagidev.sprites.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import ru.zagidev.world.Team;

public class GunnerDuckCharacter extends RangeCharacter {
    static {
    }

    public GunnerDuckCharacter(float x, float y) {
        texture = new Texture("data/duck/fister/utya.png");
        deadTexture = new Texture("data/duck/fister/utyaDead.png");
        deadTexture2 = new Texture("data/duck/fister/utya_zdokh2.png");
        animationFightTextures = new ArrayList<>();

        sound = Gdx.audio.newSound(Gdx.files.internal("data/duck/sound/quack.mp3"));
        init(x, y);
    }

    public GunnerDuckCharacter(float x, float y, Team team) {
        texture = new Texture("data/duck/gunner/utya_gun.png");
        deadTexture = new Texture("data/duck/fister/utyaDead.png");
        deadTexture2 = new Texture("data/duck/fister/utya_zdokh2.png");
        animationFightTextures = new ArrayList<>();

        attackReloading=20;
        shotSound =Gdx.audio.newSound(Gdx.files.internal("data/duck/gunner/gunshot.mp3"));;
        bulletSpeed=30;
        sound = Gdx.audio.newSound(Gdx.files.internal("data/duck/sound/quack.mp3"));
        init(x, y, team);
        speed = 2;
        damage = 100;
        currentHealth=600;
        MAX_HEALTH=600;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        attack();

    }
}
