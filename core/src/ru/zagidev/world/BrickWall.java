package ru.zagidev.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.zagidev.MyAndroidGame;

public class BrickWall implements Placeable {
    public Sprite sp;
    static Texture texture = new Texture("data/stena.jpg");
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

    @Override
    public Sprite getSprite() {
        return sp;
    }
}
