package ru.zagidev.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import ru.zagidev.MyAndroidGame;
import ru.zagidev.sprites.effects.BloodExplosion;

public class Effects extends Actor {

    public static ArrayList<BloodExplosion> explosions;
    private ShapeRenderer renderer;


    public Effects() {
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

    }

    static {
        explosions=new ArrayList<>();
    }


    void checkBlood(){
        for(int i=0;i<explosions.size();i++){
            if(!explosions.get(i).isDraw())explosions.remove(i);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for(int i=0;i<explosions.size();i++){
            if(explosions.get(i).isDraw())explosions.get(i).draw(batch);
        }

    }

    @Override
    public void act(float delta) {
        for(int i=0;i<explosions.size();i++){
            if(explosions.get(i).isDraw())explosions.get(i).act();
        }
        checkBlood();
    }
}
