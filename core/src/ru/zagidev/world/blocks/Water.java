package ru.zagidev.world.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.zagidev.world.Cell;

public class Water implements Placeable {
    public Sprite sp;
    public static Texture texture = new Texture("data/water.jpg");
    public Cell cell;

    public int dX;
    private int dY;

    public void setdX(int a) {
        this.dX = a;
    }

    public Water(Cell cell) {
        sp = new Sprite(texture,(int)cell.width,(int)cell.height);
        sp.setPosition(cell.x,cell.y);
    }

    public Water() {
        sp = new Sprite(texture);

    }

    @Override
    public Sprite getSprite() {
        return sp;
    }
}
