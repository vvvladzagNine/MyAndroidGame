package ru.zagidev.sprites.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

public class DotsPath extends Actor {
    public ArrayList<Dot> path;

    public DotsPath() {
        this.path = new ArrayList<>();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for(Dot d : path){
            d.draw(batch,parentAlpha);
        }
    }

    @Override
    public void act(float delta) {

    }


}
