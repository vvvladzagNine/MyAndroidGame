package ru.zagidev.sprites.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import ru.zagidev.world.Team;

public class GunnerPigeonCharacter extends RangeCharacter {
    static {
    }

    public GunnerPigeonCharacter(float x, float y) {
        texture = new Texture("data/pigeon/gunner/ugolub_gun.png");
        deadTexture = new Texture("data/pigeon/fister/golub_umer1.png");
        deadTexture2 = new Texture("data/pigeon/fister/golub_umer2.png");
        animationFightTextures = new ArrayList<>();

        sound = Gdx.audio.newSound(Gdx.files.internal("data/pigeon/pigeon.mp3"));
        init(x, y);
    }

    public GunnerPigeonCharacter(float x, float y, Team team) {
        texture = new Texture("data/pigeon/gunner/ugolub_gun.png");
        deadTexture = new Texture("data/pigeon/fister/golub_umer1.png");
        deadTexture2 = new Texture("data/pigeon/fister/golub_umer2.png");
        animationFightTextures = new ArrayList<>();

        attackReloading=20;
        shotSound =Gdx.audio.newSound(Gdx.files.internal("data/pigeon/gunner/gunshot2.mp3"));;
        bulletSpeed=30;
        sound = Gdx.audio.newSound(Gdx.files.internal("data/pigeon/pigeon.mp3"));
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
