package ru.zagidev.save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

import javax.swing.Renderer;

import ru.zagidev.Point;
import ru.zagidev.RunningGame;

public class GreenZone  {
    public int x;
    public int y;
    private ShapeRenderer renderer;

    public GreenZone(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GreenZone() {
    }


//    @Override
//    public void draw(Batch batch, float parentAlpha) {
//        batch.end();
//
//        batch.begin();
//    }
//
//    @Override
//    public void act(float delta) {
//        super.act(delta);
//    }


}
