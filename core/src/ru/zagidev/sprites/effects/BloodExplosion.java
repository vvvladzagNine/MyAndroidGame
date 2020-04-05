package ru.zagidev.sprites.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

public class BloodExplosion  {



    public void draw(Batch batch) {
        if(isDraw()){
            sprite.draw(batch);
        }

    }
    public void act() {
        currentTime++;
    }

    public static Texture t = new Texture("data/punch.png");

    Sprite sprite;
    float x;
    float y;
    int lifetime;
    int currentTime;
    int longOfLine;


    public boolean isDraw(){
        return currentTime<lifetime;
    }


    public BloodExplosion(float x, float y) {
        sprite=new Sprite(t);
        lifetime=15;
        currentTime=0;
        longOfLine=60;
        sprite.setPosition(x,y);
        this.x=x;
        this.y=y;


    }
}
