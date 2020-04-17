package ru.zagidev.sprites.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import ru.zagidev.levels.GameLevel;
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

        attackReloading=20;
        shotSound =Gdx.audio.newSound(Gdx.files.internal("data/duck/gunner/gunshot.mp3"));;
        bulletSpeed=30;
        rayColor= Color.RED;
        adjustClass(x, y, team,"data/duck/sound/quack.mp3",2,600,100);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        attack();

    }
}
