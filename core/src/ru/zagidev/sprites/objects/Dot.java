package ru.zagidev.sprites.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import ru.zagidev.MyAndroidGame;
import ru.zagidev.sprites.AbstractObject;
import ru.zagidev.world.WorldMap;

public class Dot {
    public Sprite sp;
    static Texture texture = new Texture("data/dot2.png");

    public int dX;
    private int dY;

    public static final int STEP_X = WorldMap.CELL_WIDTH;
    public static final int STEP_Y = WorldMap.CELL_HEIGHT;

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    public void setdX(int a) {
        this.dX = a;

    }

    public Dot(int x, int y) {
        dX = 0;
        dY = 0;
        sp = new Sprite(texture, WIDTH, HEIGHT);
        sp.setPosition(STEP_X * x + (STEP_X - WIDTH) / 2, STEP_Y * y + (STEP_Y - HEIGHT) / 2);
    }


    public void draw(Batch batch, float parentAlpha) {
        sp.draw(batch);
    }


    public void act(float delta) {
        sp.setPosition(sp.getX() + dX, sp.getY());
    }
}
