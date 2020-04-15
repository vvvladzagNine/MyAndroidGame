package ru.zagidev.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import ru.zagidev.world.blocks.Placeable;

public class Cell {
    public float centerX;
    public float centerY;
    public float x;
    public float y;
    public float width;
    public float height;
    public Placeable placeable;

    private boolean shooted;


    public Cell(float x, float y, float width, float height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        centerX = +width / 2;
        centerY = 0;
        shooted = true;


    }

    public boolean isShooted() {
        return shooted;
    }

    public boolean isPlaced() {
        return placeable != null;
    }

    public void drawCell(ShapeRenderer renderer) {
        if (!isPlaced())
            renderer.rect(x, y, width, height);

    }

    public void drawPlaceable(Batch batch) {
        if (isPlaced())
            placeable.getSprite().draw(batch);

    }

    public void place(Placeable p) {
        p.getSprite().setRegion(0, 0, width, height);
        p.getSprite().setColor(1, 1, 1, 1);
        p.getSprite().setSize(Math.abs(width), Math.abs(height));
        p.getSprite().setOrigin(width / 2, height / 2);
        p.getSprite().setPosition(x, y);
        placeable = p;
    }

    public void justPlace(Placeable p) {
        placeable = p;
    }

    public void justPlace(Placeable p,boolean shooted) {
        this.shooted=shooted;
        placeable = p;
    }

    public void act(float delta) {

    }
}
