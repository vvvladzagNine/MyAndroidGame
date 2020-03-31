package ru.zagidev.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import ru.zagidev.MyAndroidGame;

public class Cell {
    public float centerX;
    public float centerY;
    public float x;
    public float y;
    public float width;
    public float height;
    public Placeable placeable;


    public Cell(float x, float y, float width, float height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        centerX = +width / 2;
        centerY = 0;


    }

    public boolean isPlaced() {
        return placeable != null;
    }

    public void draw(ShapeRenderer renderer) {
        renderer.rect(x, y, width, height);



    }

    public void act(float delta) {

    }
}
