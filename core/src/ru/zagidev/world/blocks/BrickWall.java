package ru.zagidev.world.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.zagidev.world.Cell;

public class BrickWall implements Placeable {
    public Sprite sp;
    public static Texture texture = new Texture("data/stena.jpg");
    public Cell cell;

    public int dX;
    private int dY;

    public void setdX(int a) {
        this.dX = a;
    }

    public BrickWall(Cell cell) {
        sp = new Sprite(texture,(int)cell.width,(int)cell.height);
        sp.setPosition(cell.x,cell.y);
    }

    public BrickWall() {
        sp = new Sprite(texture);
    }

    @Override
    public Sprite getSprite() {
        return sp;
    }

}
